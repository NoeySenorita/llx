package com.outsourcing.llxpb.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.outsourcing.llxpb.Bean.ProjectListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.PersonnelReportInAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class PersonnelReportInActivity extends CommonActivity implements View.OnClickListener {

    private SearchView mSearchView;
    private  List<ProjectListBean.DataBean> project_list = new ArrayList<>();
    private ListView mLv_projectList;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_personnel_report_in;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("民工进场");
        mSearchView = findViewById(R.id.searchView);

        int searchPlateId = mSearchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlate = mSearchView.findViewById(searchPlateId);
        if (searchPlate != null) {
            int searchTextId = searchPlate.getContext().getResources()
                    .getIdentifier("android:id/search_src_text", null, null);
            //文字颜色
            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
            if (searchText != null) {
                searchText.setTextColor(0xff9C9C9D);
                searchText.setHintTextColor(0xff9C9C9D);
            }

            //光标颜色
        }


        mLv_projectList = findViewById(R.id.lv_projectList);
        if(UserUtil.projectList !=null && UserUtil.projectList.size() <= 0){
            T.showLong(this,"暂无项目");
            return;
        }
        mLv_projectList.setAdapter(new PersonnelReportInAdapter(PersonnelReportInActivity.this,UserUtil.projectList));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(TextUtils.isEmpty(query.toString())){
                    mLv_projectList.setAdapter(new PersonnelReportInAdapter(PersonnelReportInActivity.this,UserUtil.projectList));
                    return false;
                }
                project_list.clear();
                for (int i = 0 ; i < UserUtil.projectList.size();i++){
                    if(UserUtil.projectList.get(i).getName().contains(query)){
                        project_list.add(UserUtil.projectList.get(i));
                    }
                }
                mLv_projectList.setAdapter(new PersonnelReportInAdapter(PersonnelReportInActivity.this,project_list));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText.toString())){
                    mLv_projectList.setAdapter(new PersonnelReportInAdapter(PersonnelReportInActivity.this,UserUtil.projectList));
                    return false;
                }
                project_list.clear();
                for (int i = 0 ; i < UserUtil.projectList.size();i++){
                    if(UserUtil.projectList.get(i).getName().contains(newText)){
                        project_list.add(UserUtil.projectList.get(i));
                    }
                }
                mLv_projectList.setAdapter(new PersonnelReportInAdapter(PersonnelReportInActivity.this,project_list));
                return false;
            }
        });

        mLv_projectList.setAdapter(new PersonnelReportInAdapter(PersonnelReportInActivity.this,UserUtil.projectList));

    }


    @Override
    public void onClick(View v) {

    }
}
