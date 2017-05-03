package project.graduate.zhpan.littlebird.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import org.litepal.crud.DataSupport;
import org.litepal.util.LogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.Service.LocationService;
import project.graduate.zhpan.littlebird.bean.IntegralBean;
import project.graduate.zhpan.littlebird.bean.SignBean;
import project.graduate.zhpan.littlebird.bean.TaskBean;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.constants.Constatns;
import project.graduate.zhpan.littlebird.utils.CalculateDistanceUtils;
import project.graduate.zhpan.littlebird.utils.DateUtils;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;
import project.graduate.zhpan.littlebird.utils.UserInfoTools;

public class SignActivity extends BaseActivity {
    public static final int OUT_FOR_WORK = 1;  //  公出
    public static final int LEAVE = 2;   //  请假
    public static final int NO_SIGN = 0;    //  未签到
    public static final int SIGNED = 1;     //  已签到
    public static final int SIGN_OUT = 2;   //  已签退
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_sign_refresh)
    ImageView mIvRefresh;
    @BindView(R.id.tv_sign_describe)
    TextView mTvDescribe;
    @BindView(R.id.tv_sign_time)
    TextView mTvTime;
    @BindView(R.id.iv_sign_btn)
    ImageView mIvSign;
    @BindView(R.id.tv_sign_btn)
    TextView mTvSign;
    //  公司位置对应的经纬度
    private double companyLat;
    private double companyLng;
    //  当前位置的经纬度
    private double currentLat;
    private double currenLng;
    private LocationService locationService;

    //  刷新位置动画
    private Animation mAnimation;

    //  是否签退
    boolean isOutSign;
    private int signState = 0;
    //  签到时间戳
    private long signTime;
    //  当前时间戳
    private long currentTime;
    //  动画
    AnimationDrawable animationDrawable;

    private SignBean signSaveBean;

   /* final Handler handler = new Handler() {
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
    };*/
    private boolean late;
    private boolean canSignOut;
    private Disposable mDisposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void init() {
        initData();
        setData();
    }

    private void initData() {
        mTvTitle.setText("签到");

        getLatAndLng();
        initAnim();
        initSignState();
        getLocation();
    }

    //  获取公司经纬度
    public void getLatAndLng() {
        companyLat = SharedPreferencesUtils.getLat(this);
        companyLng = SharedPreferencesUtils.getLng(this);
    }

    //  初始化动画
    private void initAnim() {
        //  设置位置刷新动画
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        mAnimation.setInterpolator(linearInterpolator);
    }

    //  初始化签到状态
    private void initSignState() {
        List<SignBean> signBeen = DataSupport
                .where("signDate=? and email=?", DateUtils.getDate(), UserInfoTools.getEmail(this))
                .find(SignBean.class);
        if (signBeen.size() > 0) {     //  已签到
            signState = signBeen.get(0).getSignState();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    private void setData() {
        switch (signState) {
            case NO_SIGN:   //  未签到
                noSignState();
                break;
            case SIGNED:    //  已签到
                haveSigned();
                break;
            case SIGN_OUT: //  已签退
                haveSignOut();
                break;
        }
    }

    private void haveSignOut() {
        mTvSign.setText("已签退");
        mIvSign.setImageResource(R.drawable.sign_out_btn);
        mIvSign.setClickable(false);

        //  设置工作计时
        mTvDescribe.setText("工作计时");
        List<SignBean> signBeen = getSignBeen();
        //signBeans = DataSupport.where("signDate=?", DateUtils.getDate()).find(SignBean.class);
        long workedTime = signBeen.get(0).getSignOutTime() - signBeen.get(0).getSignTime();
        mTvTime.setText(DateUtils.timeFormat(workedTime / 1000));
    }

    //  已签到
    private void haveSigned() {
        mTvSign.setText("签退");
        mIvSign.setImageResource(R.drawable.sign_off_anim);
        animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
        animationDrawable.start();
        //  设置工作计时
        mTvDescribe.setText("工作计时");
        List<SignBean> signBeen = getSignBeen();
        long workTime = (System.currentTimeMillis() - signBeen.get(0).getSignTime())/ 1000;
        mTvTime.setText(DateUtils.timeFormat(workTime));
        setWorkTime(workTime );
    }

    //  未签到状态
    private void noSignState() {
        mTvSign.setText("签到");
        mIvSign.setImageResource(R.drawable.sign_anim);
        animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
        animationDrawable.start();

        calculateSingTime();
    }

    private void calculateSingTime() {
        //  获取当天9点的时间戳
        signTime = DateUtils.getSignTime(9);
        currentTime = System.currentTimeMillis() / 1000;

        //  距离签到的时间
        long time = signTime - currentTime;
        //  设置签到倒计时
        if (time < 0) {
            mTvDescribe.setText("已迟到");
        } else {
            mTvDescribe.setText("签到倒计时");
        }
        //  设置倒计时
        long signTime= this.signTime - System.currentTimeMillis() / 1000;
        mTvTime.setText(DateUtils.timeFormat(signTime));
        setTime(signTime);
    }

    //  设置倒计时
    private void setTime(long time) {
                interval().take(time>0?time+1:-(time-1))
                .observeOn(AndroidSchedulers.mainThread())
                .map((@NonNull Long aLong)-> {
                        return time>0?time+aLong:time-aLong;
                }).subscribe(getObserver());
    }
    //  工作计时
    private void setWorkTime(long time) {
        interval().take(time+1)
                .observeOn(AndroidSchedulers.mainThread())
                .map((@NonNull Long aLong)-> {
                    return time+aLong;
                }).subscribe(getObserver());
    }

    private Observable<Long> interval(){
        return Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
    }

    private Observer<Long> getObserver(){
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable=d;
                addRxDestroy(d);
            }

            @Override
            public void onNext(Long l) {
                mTvTime.setText(DateUtils.timeFormat(l));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }



    //  检测是否有未提交的任务
    private boolean isHaveUnCommitTask(List<TaskBean> taskBeans) {

        for (int i = 0; i < taskBeans.size(); i++) {
            int taskState = taskBeans.get(i).getTaskState();
            if (taskState == Constatns.WAIT_COMMIT || taskState == Constatns.WAIT_START) {
                return true;
            }
        }
        return false;
    }

    /***
     * Stop location service
     */
    @Override
    public void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        super.onStop();
    }


    @OnClick({R.id.iv_sign_refresh, R.id.iv_sign_btn, R.id.btn_out_for_work, R.id.btn_leave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sign_btn:
                if (signState == NO_SIGN) {   //  签到
                    sign();
                } else if (signState == SIGNED) {    // 签退
                    signedOut();
                }
                break;
            case R.id.btn_out_for_work: //  公出
                LeaveListActivity.start(this, OUT_FOR_WORK);
                break;
            case R.id.btn_leave:    //  请假
                LeaveListActivity.start(this, LEAVE);
                break;
            case R.id.iv_sign_refresh:  //  刷新位置
                //  获取位置
                getLocation();
                break;
        }
    }



    private void initSignedState() {
        signState = SIGNED;
        mTvSign.setText("签退");
        mTvDescribe.setText("工作计时");
        mIvSign.setImageResource(R.drawable.sign_anim);

        long currentStamp = System.currentTimeMillis();
        saveSignState(currentStamp);
        isLate(currentStamp);
    }

    //  保存签到状态到数据库
    private void saveSignState(long currentStamp) {
        signSaveBean = new SignBean();
        signSaveBean.setSignDate(DateUtils.getDate());
        signSaveBean.setEmail(UserInfoTools.getEmail(this));
        signSaveBean.setSignTime(currentStamp);
        signSaveBean.setSignState(SIGNED);
        signSaveBean.setEmail(UserInfoTools.getEmail(this));
        signSaveBean.save();
        //signSaveBean.updateAll("signDate=?", DateUtils.getDate());
    }

    //  签到
    private void sign() {

        if (getTasks().size() < 3) {
            Toast.makeText(this, "至少要添加三个任务哦！", Toast.LENGTH_SHORT).show();
            return;
        }
        double distance = CalculateDistanceUtils.GetShortDistance(currenLng, currentLat, companyLng, companyLat);
        Toast.makeText(this, "距离公司距离："+distance, Toast.LENGTH_SHORT).show();
        if (distance > 1000) {
            Toast.makeText(this, "请在公司附近签到", Toast.LENGTH_SHORT).show();
            return;
        }
        initSignedState();


        //  工作记时
        setWorkTime(0);

        mIvSign.setImageResource(R.drawable.sign_off_anim);
        animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
        animationDrawable.start();
    }

    //  签退
    private void signedOut() {
        long currentStamp = System.currentTimeMillis();

        if (isHaveUnCommitTask(getTasks())) {
            Toast.makeText(this, "有任务还没完成，请先提交任务", Toast.LENGTH_SHORT).show();
            return;
        }
        double distance = CalculateDistanceUtils.GetShortDistance(currenLng, currentLat, companyLng, companyLat);
        Toast.makeText(this, "距离公司距离："+distance, Toast.LENGTH_SHORT).show();
        if (distance > 1000) {
            Toast.makeText(this, "请在公司附近签退", Toast.LENGTH_SHORT).show();
            return;
        }

        mDisposable.dispose();

        List<SignBean> signBeen = getSignBeen();
        signBeen.get(0).setSignOutTime(currentStamp);
        signBeen.get(0).setSignState(SIGN_OUT);
        signBeen.get(0).updateAll("signDate=?", DateUtils.getDate());
        //signBeen.get(0).save();
        if (signBeen.get(0).save()) {
            //isOutSign = true;
            signState = SIGN_OUT;
            mTvSign.setText("已签退");
            mIvSign.setImageResource(R.drawable.sign_out_btn);
            mIvSign.setClickable(false);
        } else {
            Toast.makeText(this, "签退失败", Toast.LENGTH_SHORT).show();
        }
    }

    private List<TaskBean> getTasks() {
        //  获取今天的任务
        List<TaskBean> taskBeen = DataSupport
                .where("createDate=? and userEmail=?", DateUtils.getDate(), UserInfoTools.getEmail(this))
                .find(TaskBean.class);
        return taskBeen;
    }

    //  获取当前位置距离公司的距离
    public double getDistance() {
        double a, b, R;
        R = 6378137; // 地球半径
        currentLat = currentLat * Math.PI / 180.0;
        companyLat = companyLat * Math.PI / 180.0;
        a = currentLat - companyLat;
        b = (currenLng - companyLng) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(currentLat) * Math.cos(companyLat) * sb2 * sb2));
        Toast.makeText(this, d + "", Toast.LENGTH_SHORT);
        return d;
    }

    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    //获取经纬度
                    currentLat = location.getLatitude();
                    currenLng = location.getLongitude();

                    mTvAddress.setText(location.getCity() + location.getDistrict() + location.getStreet());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    mTvAddress.setText(location.getCity() + location.getDistrict() + location.getStreet());
                    //获取经纬度
                    currentLat = location.getLatitude();
                    currenLng = location.getLongitude();
                }
            }
            // Toast.makeText(SignActivity.this, "距离："+getDistance(), Toast.LENGTH_SHORT).show();
            mIvRefresh.clearAnimation();
            locationService.stop();
        }
    };

    //  获取当前位置
    public void getLocation() {
        /**
         * 获取locationservice实例，建议应用中只初始化1个location实例
         *  然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
         */
        locationService = new LocationService(getApplicationContext());
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        } else {
            locationService.setLocationOption(locationService.getOption());
        }
        mIvRefresh.startAnimation(mAnimation);
        mTvAddress.setText("正在获取位置...");
        locationService.start();// 定位SDK

    }


    public void isLate(long currentStamp) {
        if (currentStamp > DateUtils.getSignTime(9)) {  // 迟到
            signSaveBean.setLate(true);
        } else { //  没有迟到则增加2个积分并保存获得积分到数据库
            add2Integral();
        }
    }

    //  增加2个总积分
    private void add2Integral() {
        UserBean user = getUser();
        user.setIntegral(user.getIntegral()+2);
        if (user.save()) {  //  添加奖励到数据库
            addIntegralBean();
        }
    }
    //  添加积分奖励到数据库
    private void addIntegralBean() {
        IntegralBean integralBean = new IntegralBean();
        integralBean.setHowGet("签到奖励");
        integralBean.setIntegral(2);
        integralBean.setEmail(UserInfoTools.getEmail(this));
        integralBean.setDate(DateUtils.getDate());
        integralBean.save();
    }

    public UserBean getUser(){
        List<UserBean> userBeen = DataSupport
                .where("email=?", UserInfoTools.getEmail(this))
                .find(UserBean.class);
        return userBeen.get(0);
    }

    public List<SignBean> getSignBeen(){
        List<SignBean> signBeen = DataSupport
                .where("signDate=? and email=?", DateUtils.getDate(), UserInfoTools.getEmail(this))
                .find(SignBean.class);
        return signBeen;
    }

}
