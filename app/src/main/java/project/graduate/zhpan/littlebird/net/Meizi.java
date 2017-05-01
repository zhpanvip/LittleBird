package project.graduate.zhpan.littlebird.net;

import java.util.List;

/**
 * Created by zhpan on 2017/4/20.
 */

public class Meizi {

    /**
     * error : false
     * results : [{"_id":"58f8043e421aa9544ed88993","createdAt":"2017-04-20T08:43:42.333Z","desc":"4-20","publishedAt":"2017-04-20T14:03:06.490Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-20-17932350_1238028809599424_2089669844847820800_n.jpg","used":true,"who":"daimajia"},{"_id":"58f6baad421aa954511ebec3","createdAt":"2017-04-19T09:17:33.987Z","desc":"4-19","publishedAt":"2017-04-19T11:44:51.925Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-19-17881407_1845958195665029_1132383288824954880_n.jpg","used":true,"who":"daimajia"},{"_id":"58f5649b421aa9544825f89f","createdAt":"2017-04-18T08:58:03.511Z","desc":"4-18","publishedAt":"2017-04-18T11:34:29.203Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg","used":true,"who":"带马甲"},{"_id":"58f3980c421aa9544b773ff1","createdAt":"2017-04-17T00:13:00.136Z","desc":"4-17","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg","used":true,"who":"daimajia"},{"_id":"58f0438f421aa9544825f873","createdAt":"2017-04-14T11:35:43.995Z","desc":"4-14","publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-14-17881962_1329090457138411_8289893708619317248_n.jpg","used":true,"who":"代码家"},{"_id":"58eef016421aa9544b773fd0","createdAt":"2017-04-13T11:27:18.557Z","desc":"4-13","publishedAt":"2017-04-13T11:36:04.435Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-13-17882785_926451654163513_7725522121023029248_n.jpg","used":true,"who":"dmj"},{"_id":"58ed8242421aa9544b773fc1","createdAt":"2017-04-12T09:26:26.434Z","desc":"4-12","publishedAt":"2017-04-12T12:12:18.213Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-12-17662441_1675934806042139_7236493360834281472_n.jpg","used":true,"who":"daimajia"},{"_id":"58ec30e4421aa9544ed88919","createdAt":"2017-04-11T09:27:00.840Z","desc":"4-11","publishedAt":"2017-04-11T14:43:34.738Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-11-17881546_248332202297978_2420944671002853376_n.jpg","used":true,"who":"dmj"},{"_id":"58ea5f51421aa954511ebe51","createdAt":"2017-04-10T00:20:33.996Z","desc":"4-10","publishedAt":"2017-04-10T12:11:14.794Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-09-17586558_426275167734768_6312107349515436032_n.jpg","used":true,"who":"dmj"},{"_id":"58e6dec3421aa90d6f211e4a","createdAt":"2017-04-07T08:35:15.664Z","desc":"4-7","publishedAt":"2017-04-07T12:02:31.316Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-07-17817932_274034076387428_5240190736292380672_n.jpg","used":true,"who":"带马甲"}]
     */

    private List<ResultsBean> results;


    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 58f8043e421aa9544ed88993
         * createdAt : 2017-04-20T08:43:42.333Z
         * desc : 4-20
         * publishedAt : 2017-04-20T14:03:06.490Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-20-17932350_1238028809599424_2089669844847820800_n.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
