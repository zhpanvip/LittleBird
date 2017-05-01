package project.graduate.zhpan.littlebird.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.Unbinder;
import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.activity.UserInfoActivity;
import project.graduate.zhpan.littlebird.adapter.ColleagueAdapter;
import project.graduate.zhpan.littlebird.bean.UserBean;
import project.graduate.zhpan.littlebird.utils.JsonFileReader;
import project.graduate.zhpan.littlebird.utils.SideBar;

/**
 * Created by zhpan on 2016/10/15.
 */

public class ColleagueFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.toolbar_title)
    TextView mTvTitle;
    @BindView(R.id.lv_colleague_name)
    ListView mListView;
    @BindView(R.id.sidrbar)
    SideBar mSideBar;
    private ColleagueAdapter adapter;
    private List<UserBean> mList;

    private void setListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                UserBean user = (UserBean) (adapter.getmList().get(position));
                String title = user.getRealName();
                UserInfoActivity.start(getActivity(), title, user);
            }
        });
    }

    private void setData() {
        mTvTitle.setText("LittleBird");
        mIvBack.setVisibility(View.GONE);
        adapter = new ColleagueAdapter(getContext());
        mList = new ArrayList<>();
        //  获取json数据
        String colleagueData = JsonFileReader.getJson(getContext(), "Colleague.json");
        Gson gson = new Gson();
        //ColleagueBean colleague = gson.fromJson(colleagueData, ColleagueBean.class);
        mList = DataSupport.findAll(UserBean.class);
        //mList = colleague.getEmployeeList();
        adapter.setList(mList);
        mListView.setAdapter(adapter);
        /*float f= (float) 0.5;
        mSideBar.setAlpha(f);*/

    }

/*    private void initView() {
        mTvTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mIvBack = (ImageView) mView.findViewById(R.id.iv_back);
        mListView = (ListView) mView.findViewById(R.id.lv_colleague_name);
        mSideBar = (SideBar) mView.findViewById(R.id.sidrbar);
    }*/

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_colleague;
    }

    @Override
    protected void init() {
       // initView();
        setData();
        setListener();
    }
}
