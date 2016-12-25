package project.graduate.zhpan.littlebird.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.graduate.zhpan.littlebird.R;

/**
 * Created by zhpan on 2016/12/17.
 */

public class TextFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_topic,null);
        return mView;

    }
}
