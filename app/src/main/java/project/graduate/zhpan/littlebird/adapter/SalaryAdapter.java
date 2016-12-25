package project.graduate.zhpan.littlebird.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import project.graduate.zhpan.littlebird.R;
import project.graduate.zhpan.littlebird.bean.SalaryBean;

/**
 * Created by zhpan on 2016/12/24.
 */

public class SalaryAdapter implements ExpandableListAdapter {
    private Context mContext;
    private List<SalaryBean> mGroupList;
    private List<List<SalaryBean>> mChileList;

    public SalaryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<SalaryBean> getmGroupList() {
        return mGroupList;
    }

    public void setmGroupList(List<SalaryBean> mGroupList) {
        this.mGroupList = mGroupList;
    }

    public List<List<SalaryBean>> getmChileList() {
        return mChileList;
    }

    public void setmChileList(List<List<SalaryBean>> mChileList) {
        this.mChileList = mChileList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return 20;
    }

    @Override
    public int getChildrenCount(int i) {
        return 4;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChileList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_salary, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_salary_child, null);
            holder=new GroupViewHolder(convertView);
            convertView.setTag(holder);
            if(childPosition%4==2){
                holder.mTvTextSalary.setText("餐费补助：");
                holder.mTvSalary.setText("+300");
            }else if(childPosition%4==1){
                holder.mTvTextSalary.setText("住房补助：");
                holder.mTvSalary.setText("+1000");
            }else if(childPosition%4==3){
                holder.mTvTextSalary.setText("迟到扣除：");
                holder.mTvSalary.setText("-300");
            }else if(childPosition%4==0){
                holder.mTvTextSalary.setText("基本工资：");
                holder.mTvSalary.setText("+19000");
            }
        }else {
            holder= (GroupViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }

    public static class GroupViewHolder {
        public View rootView;
        public TextView mTvTime;
        public TextView mTvSalary;
        public TextView mTvTextSalary;

        public GroupViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvTime = (TextView) rootView.findViewById(R.id.tv_time);
            this.mTvSalary = (TextView) rootView.findViewById(R.id.tv_salary);
            this.mTvTextSalary= (TextView) rootView.findViewById(R.id.tv_text_salary);
        }

    }
}
