package project.graduate.zhpan.littlebird.net;

import java.util.List;

/**
 * Created by zhpan on 2017/4/20.
 */

public class MeiziWrapper extends BasicResponse {
    private List<Meizi.ResultsBean> results;

    public List<Meizi.ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<Meizi.ResultsBean> results) {
        this.results = results;
    }
}
