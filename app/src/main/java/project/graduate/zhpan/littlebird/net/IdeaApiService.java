package project.graduate.zhpan.littlebird.net;

import io.reactivex.Observable;
import project.graduate.zhpan.littlebird.bean.BaiduLocation;
import project.graduate.zhpan.littlebird.constants.Constatns;
import project.graduate.zhpan.littlebird.constants.UrlConstant;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhpan on 2017/4/1.
 */

public interface IdeaApiService {
    /**
     * 网络请求超时时间毫秒
     */
    int DEFAULT_TIMEOUT = 20000;

    //String HOST = "http://api.map.baidu.com/telematics/v3/geocoding";
    String API_SERVER_URL = "http://api.map.baidu.com/telematics/v3/";

    //@GET("福利/10/1")
    //Observable<MeiziWrapper> getMeizi();
    @GET("geocoding")
    Observable<BasicResponse<BaiduLocation>> getLocation(@Query("cityName")String cityName,@Query("keyWord")String keyWord,@Query("ak")String ak,@Query("output")String output,@Query("mcode")String mcode);

}
