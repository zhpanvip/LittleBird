package project.graduate.zhpan.littlebird.activity;

import android.content.Intent;
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
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.Service.LocationService;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import project.graduate.zhpan.littlebird.utils.DateUtils;

public class SignActivity extends BaseActivity implements View.OnClickListener {
    private static final int OUT_FOR_WORK = 1;  //  公出
    private static final int LEAVE = 2;   //  请假
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
    //  签到时间戳
    private long signTime;
    //  当前时间戳
    private long currentTime;
    //  动画
    AnimationDrawable animationDrawable;
    //  是否签退
    boolean isOutSign;
    //  time=signTime-currentTime
    long time;
    private double latitude;
    private double longitude;


    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    long time = (long) msg.obj;
                    if (time < 0) {
                        time = -time;
                    }
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
        animationDrawable.stop();
    }

    private void setData() {

        mTvTitle.setText("签到");
        //  获取当天9点的时间戳
        signTime = DateUtils.getSignTime(9);
        currentTime = System.currentTimeMillis() / 1000;
        time = signTime - currentTime;
        if (time < 0) {
            mTvDescribe.setText("已迟到");
        }
        //  设置倒计时
        //SignPresenter.setTime(time,handler,isCircle);
        setTime(time);

        mIvSign.setImageResource(R.drawable.sign_anim);
        animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
        animationDrawable.start();

    }


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
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_sign_btn:
                if (isSign) {
                    isOutSign = true;
                    mTvSign.setText("已签退");
                    mIvSign.setImageResource(R.drawable.sign_out_btn);
                    mIvSign.setClickable(false);

                } else {
                    isSign = true;
                    mTvSign.setText("签退");
                    mTvDescribe.setText("工作计时");
                    signTime = System.currentTimeMillis() / 1000;
                    setWorkTime(0);

                    mIvSign.setImageResource(R.drawable.sign_off_anim);
                    animationDrawable = (AnimationDrawable) mIvSign.getDrawable();
                    animationDrawable.start();
                }

                break;
            case R.id.btn_out_for_work:
                LeaveListActivity.start(this,OUT_FOR_WORK);

                break;

            case R.id.btn_leave:
                LeaveListActivity.start(this,LEAVE);

                break;
            case R.id.iv_sign_refresh:
                mIvRefresh.startAnimation(mAnimation);
                mTvAddress.setText("正在获取位置...");
                locationService.start();
                break;
        }

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
