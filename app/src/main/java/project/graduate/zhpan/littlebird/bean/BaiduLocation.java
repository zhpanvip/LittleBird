package project.graduate.zhpan.littlebird.bean;

/**
 * Created by zhpan on 2017/5/2.
 */

public class BaiduLocation {

    /**
     * location : {"lng":116.3017193083,"lat":40.050743859593}
     * precise : 1
     */

    private LocationBean location;
    private int precise;

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }

    public static class LocationBean {
        /**
         * lng : 116.3017193083
         * lat : 40.050743859593
         */

        private double lng;
        private double lat;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
