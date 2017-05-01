package project.graduate.zhpan.littlebird.app;

import android.app.Application;
import android.widget.Toast;

import com.airong.core.BaseApp;

import org.litepal.LitePal;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import project.graduate.zhpan.littlebird.bean.BaiduLocation;
import project.graduate.zhpan.littlebird.constants.Constatns;
import project.graduate.zhpan.littlebird.net.BasicResponse;
import project.graduate.zhpan.littlebird.net.DefaultObserver;
import project.graduate.zhpan.littlebird.net.IdeaApi;
import project.graduate.zhpan.littlebird.utils.SharedPreferencesUtils;

/**
 * Created by zhpan on 2016/12/31.
 */

public class MainApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

        IdeaApi.getApiService()
                .getLocation("上海","上海市浦东新区张江镇玉兰香苑一期", Constatns.AK,"json",Constatns.MCODE_DEBUG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse<BaiduLocation>>(this) {
                    @Override
                    public void onSuccess(BasicResponse<BaiduLocation> response) {
                        BaiduLocation results = response.getResults();
                        BaiduLocation.LocationBean location = results.getLocation();
                        Toast.makeText(MainApplication.this, "Lat:"+location.getLat()+"  Lng"+location.getLng(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
