package com.outsourcing.llxpb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.ProjectListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.PhotographPersonnerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/7/7.
 */

public class PersonnelReportInAdapter extends BaseAdapter {
    private Context mContext;
    private List<ProjectListBean.DataBean> mProjectListBeans;

    public PersonnelReportInAdapter(Context context, List<ProjectListBean.DataBean> project_list) {
        mContext = context;
        mProjectListBeans = project_list;
    }

    @Override
    public int getCount() {
        if (mProjectListBeans == null) {
            return 0;
        }
        return mProjectListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PersonnelReportInHolder personnelReportInHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_personnel_report_in, null);
            personnelReportInHolder = new PersonnelReportInHolder();
            personnelReportInHolder.projectName = convertView.findViewById(R.id.projectName);
            personnelReportInHolder.mView = convertView;
            convertView.setTag(personnelReportInHolder);
        } else personnelReportInHolder = (PersonnelReportInHolder) convertView.getTag();
        personnelReportInHolder.projectName.setText(mProjectListBeans.get(position).getName());
        personnelReportInHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotographPersonnerActivity.class);
                UserUtil.sendPerposesActivitys = new ArrayList<>();
                intent.putExtra("projectId", mProjectListBeans.get(position).getId() + "");
                intent.putExtra("projectName", mProjectListBeans.get(position).getName() + "");

                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class PersonnelReportInHolder {
        private TextView projectName;
        private View mView;
    }
}
