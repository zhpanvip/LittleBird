package project.graduate.zhpan.littlebird.app;

import com.airong.core.BaseApp;
import org.litepal.LitePal;

/**
 * Created by zhpan on 2016/12/31.
 */

public class MainApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
