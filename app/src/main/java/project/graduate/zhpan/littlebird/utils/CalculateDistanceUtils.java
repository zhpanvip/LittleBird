package project.graduate.zhpan.littlebird.utils;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by SRain on 2015/10/16.
 * <p/>
 * 计算两点间距离
 * <p/>
 * 路线规划提供了获取路线距离的方法，见MKRoutePlan 类的 getDistance 方法。
 * 如果是计算任意两点的距离，有两种方法：
 * 一种利用勾股定理计算，适用于两点距离很近的情况；
 * 一种按标准的球面大圆劣弧长度计算，适用于距离较远的情况。
 */
public class CalculateDistanceUtils {
    static double DEF_PI = 3.14159265359; // PI
    static double DEF_2PI = 6.28318530712; // 2*PI
    static double DEF_PI180 = 0.01745329252; // PI/180.0
    static double DEF_R = 6370693.5; // radius of earth

    /**
     * 利用勾股定理计算，适用于两点距离很近的情况；
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    /**
     * 按标准的球面大圆劣弧长度计算，适用于距离较远的情况。
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public static double GetLongDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }

    /**
     * 按标准的球面大圆劣弧长度计算，适用于距离较远的情况。
     *
     * @param atLatLng    当前位置
     * @param guideLatLng 景点位置
     * @return
     */
    public static double GetLongDistance(LatLng atLatLng, LatLng guideLatLng) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = atLatLng.longitude * DEF_PI180;
        ns1 = atLatLng.latitude * DEF_PI180;
        ew2 = guideLatLng.longitude * DEF_PI180;
        ns2 = guideLatLng.latitude * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }

    /**
     * 按标准的球面大圆劣弧长度计算，适用于距离较远的情况。
     *
     * @param atLatLng  当前位置
     * @param longitude 景点位置
     * @param latitude  景点位置
     * @return
     */
    public static double GetLongDistance(LatLng atLatLng, double longitude, double latitude) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = atLatLng.longitude * DEF_PI180;
        ns1 = atLatLng.latitude * DEF_PI180;
        ew2 = longitude * DEF_PI180;
        ns2 = latitude * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);

        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;

        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);

//        Log.e("distance", "atLatLng" + atLatLng.latitude + "   " + atLatLng.longitude + "\n" + "longitude:" + longitude + "latitude" + latitude + "\n dis:" + distance);
        return distance;
    }

    /**
     * 比较景点信息
     * 当前位置是否在景区中
     *
     * @param atLatLng  当前位置
     * @param longitude 景点位置
     * @param latitude  景点位置
     * @param radius    景点范围半径
     * @return
     */
    public static boolean isAtRange(LatLng atLatLng, double longitude, double latitude, double radius) {
        boolean b = false;
        // 获取当前位置和景区位置的距离
        double distance = GetLongDistance(atLatLng, longitude, latitude);

        if (radius > distance) {
            b = true;
        }
        Log.e("-----distance-----", "\n distance:" + distance + "\n radius:" + radius + "\n b:" + b);
        return b;
    }
}
