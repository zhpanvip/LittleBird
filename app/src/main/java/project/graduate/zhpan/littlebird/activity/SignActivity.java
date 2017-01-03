package project.graduate.zhpan.littlebird.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.Service.LocationService;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import org.litepal.crud.DataSupport;
import java.util.List;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.bean.SignBean;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.constants.Constatns;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class SignActivity extends BaseActivity implements View.OnClickListener {
    public static final int OUT_FOR_WORK = 1;  //  公出
    public static final int LEAVE = 2;   //  请假
    public static final int NO_SIGN = 0;
    public static final int SIGNED = 1;
    public static final int SIGN_OUT = 2;

    private TextView mTvTime;
    private TextView mTvDescribe;
    private TextView mTvSign;
    private ImageView mIvSign;
    private Button btnLeave;
    private Button btnForWork;
    private ImageView mIvRefresh;
    private LocationService locationService;
    private TextView mTvAddress;

    //  刷新位置动画
    private Animation mAnimation;
    //  是否签到
    private boolean isSign;
    //  是否签退
    boolean isOutSign;
    private int signState;
    //  签到时间戳
    private long signTime;
    //  当前时间戳
    private long currentTime;
    //  动画
    AnimationDrawable animationDrawable;

    private double latitude;
    private double longitude;
    private SignBean signSaveBean;

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    long time = (long) msg.obj;
                    mTvTime.setText(DateUtils.timeFormat(time));
                    break;
                case 2:
                    long workTime = (long) msg.obj;
                    mTvTime.setText(DateUtils.timeFormat(workTime));
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
        initData();
        setData();
        setListener();
    }

    private void initData() {
        //  设置位置刷新动画
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        mAnimation.setInterpolator(linearInterpolator);

        List<SignBean> signBeen = DataSupport.where("signDate=? and email=?", DateUtils.getDate(), UserInfoTools.getEmail(this)).find(SignBean.class);
        if(signBeen.size()<=0){ //  还没签到
            //  初始化数据库
            signSaveBean = new SignBean();
            signSaveBean.setSignDate(DateUtils.getDate());
            signSaveBean.setEmail(UserInfoTools.getEmail(this));
            signSaveBean.save();
        }
        List<SignBean> signBeans = DataSupport.findAll(SignBean.class);
        signState=signBeans.get(0).getSignState();

        //  获取位置
        getLocation();
    }

    private void setListener() {
        mIvSign.setOnClickListener(this);
        btnForWork.setOnClickListener(this);
        btnLeave.setOnClickListener(this);
        mIvRefresh.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(animationDrawable!=null){
            animationDrawable.stop();
        }
    }

    private void setData() {
        mTvTitle.setText("签到");
        List<SignBean> signBeans;
        //  设置签到状态
        switch (signState){
            case NO_SIGN:   //  未签到

                    mTvSign.setText("签到");
                    mIvSign.setImageResource(R.drawable.sign_anim);
                    animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
                    animationDrawable.start();
                    //  获取当天9点的时间戳
                    signTime = DateUtils.getSignTime(9);
                    currentTime = System.currentTimeMillis() / 1000;

                    //  距离签到的时间
                    long time = signTime - currentTime;
                    //  设置签到倒计时
                    if (time < 0) {
                        mTvDescribe.setText("已迟到");
                    }else {
                        mTvDescribe.setText("签到倒计时");
                    }
                    //  设置倒计时
                    setTime((System.currentTimeMillis()/1000-signTime));
                break;

            case SIGNED:    //  已签到
                mTvSign.setText("签退");
                mIvSign.setImageResource(R.drawable.sign_off_anim);
                animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
                animationDrawable.start();
                //  设置工作计时
                mTvDescribe.setText("工作计时");
                signBeans = DataSupport.where("signDate=?",DateUtils.getDate()).find(SignBean.class);
                long workTime=System.currentTimeMillis()-signBeans.get(0).getSignTime();

                setWorkTime(workTime/1000);

                break;

            case SIGN_OUT: //  签退
                    mTvSign.setText("已签退");
                    mIvSign.setImageResource(R.drawable.sign_out_btn);
                    mIvSign.setClickable(false);

                    //  设置工作计时
                    mTvDescribe.setText("工作计时");
                    signBeans = DataSupport.where("signDate=?",DateUtils.getDate()).find(SignBean.class);
                    long workedTime=signBeans.get(0).getSignOutTime()-signBeans.get(0).getSignTime();
                    mTvTime.setText(DateUtils.timeFormat(workedTime/1000));
                break;
        }
    }

    //  设置倒计时
    private void setTime(final long time) {
        new Thread() {
            @Override
            public void run() {
                long t = time;
                try {
                    while (!isSign) {
                        t--;
                        Message message = new Message();
                        message.what = 1;
                        message.obj = t;
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setWorkTime(final long time) {
        new Thread() {
            @Override
            public void run() {
                long t = time;
                try {
                    while (!isOutSign) {
                        t++;
                        Message message = new Message();
                        message.what = 2;
                        message.obj = t;
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initView() {
        mTvTime = (TextView) findViewById(R.id.tv_sign_time);
        mTvDescribe = (TextView) findViewById(R.id.tv_sign_describe);
        mTvSign = (TextView) findViewById(R.id.tv_sign_btn);
        mIvSign = (ImageView) findViewById(R.id.iv_sign_btn);
        btnForWork = (Button) findViewById(R.id.btn_out_for_work);
        btnLeave = (Button) findViewById(R.id.btn_leave);
        mIvRefresh= (ImageView) findViewById(R.id.iv_sign_refresh);
        mTvAddress= (TextView) findViewById(R.id.tv_address);
    }


    @Override
    public void onClick(View view) {
        //  获取今天的任务
        List<TaskBean> taskBeen = DataSupport.where("createDate=? and userEmail=?", DateUtils.getDate(),UserInfoTools.getEmail(this)).find(TaskBean.class);
        List<SignBean> signBeans = DataSupport.findAll(SignBean.class);
        signState=signBeans.get(0).getSignState();
        switch (view.getId()) {
            case R.id.iv_sign_btn:
                long currentStamp = System.currentTimeMillis();
                if (signState==SIGNED) {   //  签退
                    if(isHaveUnCommitTask(taskBeen)){
                        Toast.makeText(this, "有任务还没完成，请先提交任务", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    List<SignBean> signBeen = DataSupport.where("signDate=? and email=?", DateUtils.getDate(), UserInfoTools.getEmail(this)).find(SignBean.class);
                    signBeen.get(0).setSignOutTime(currentStamp);
                    signBeen.get(0).setSignState(SIGN_OUT);
                    signBeen.get(0).updateAll("signDate=?",DateUtils.getDate());
                    signBeen.get(0).save();
                    isOutSign = true;
                    mTvSign.setText("已签退");
                    mIvSign.setImageResource(R.drawable.sign_out_btn);
                    mIvSign.setClickable(false);
                } else if(signState==NO_SIGN) {    //签到
                    if(taskBeen.size()<3){
                        Toast.makeText(this, "至少要添加三个任务哦！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    isSign = true;
                    mTvSign.setText("签退");
                    mTvDescribe.setText("工作计时");
                    mIvSign.setImageResource(R.drawable.sign_anim);
                    //  保存签到时间到数据库
                    signSaveBean=new SignBean();
                    signSaveBean.setSignTime(currentStamp);
                    signSaveBean.setSignState(SIGNED);
                    signSaveBean.updateAll("signDate=?",DateUtils.getDate());
                    signSaveBean.setEmail(UserInfoTools.getEmail(this));
                    if(currentStamp>DateUtils.getSignTime(9)){  // 迟到
                        signSaveBean.setLate(true);
                    }else { //  没有迟到则增加2个积分并保存获得积分到数据库

                        //  增加2个总积分
                        List<UserBean> userBeen = DataSupport.where("email=?", UserInfoTools.getEmail(this)).find(UserBean.class);
                        int totalInt = userBeen.get(0).getIntegral();
                        totalInt=totalInt+2;
                        userBeen.get(0).setIntegral(totalInt);
                        userBeen.get(0).save();

                        //  添加积分奖励到数据库
                        IntegralBean integralBean=new IntegralBean();
                        integralBean.setHowGet("签到奖励");
                        integralBean.setIntegral(2);
                        integralBean.setEmail(UserInfoTools.getEmail(this));
                        integralBean.setDate(DateUtils.getDate());
                        integralBean.save();
                    }
                    signSaveBean.save();
                   // signTime = currentStamp / 1000;
                    //  工作记时
                    setWorkTime(0);

                    mIvSign.setImageResource(R.drawable.sign_off_anim);
                    animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
                    animationDrawable.start();
                }

                break;
            case R.id.btn_out_for_work: //  公出
                LeaveListActivity.start(this,OUT_FOR_WORK);

                break;

            case R.id.btn_leave:    //  请假
                LeaveListActivity.start(this,LEAVE);

                break;
            case R.id.iv_sign_refresh:  //  刷新位置
                mIvRefresh.startAnimation(mAnimation);
                mTvAddress.setText("正在获取位置...");
                locationService.start();
                break;
        }

    }

    //  检测是否有未提交的任务
    private boolean isHaveUnCommitTask(List<TaskBean> taskBeans ){

        for (int i=0;i<taskBeans.size();i++){
            int taskState = taskBeans.get(i).getTaskState();
            if(taskState== Constatns.WAIT_COMMIT||taskState==Constatns.WAIT_START){
                return true;
            }
        }
        return false;
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    //获取经纬度
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    mTvAddress.setText(location.getCity() + location.getDistrict() + location.getStreet());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    mTvAddress.setText(location.getCity() + location.getDistrict() + location.getStreet());
                } else {
                    mTvAddress.setText("获取位置失败，请检查网络");
                }
            }
            mIvRefresh.clearAnimation();
        }
    };

    public void getLocation() {
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService = new LocationService(getApplicationContext());
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }else {
            locationService.setLocationOption(locationService.getOption());
        }
        mIvRefresh.startAnimation(mAnimation);
        mTvAddress.setText("正在获取位置...");
        locationService.start();// 定位SDK

    }
}
