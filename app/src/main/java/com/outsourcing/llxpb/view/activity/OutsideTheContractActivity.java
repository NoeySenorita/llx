package com.outsourcing.llxpb.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.OutsideTheContractBean;
import com.outsourcing.llxpb.Bean.WageDistributionTimeBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.OutsideTheContractAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class OutsideTheContractActivity extends CommonActivity {

    private TabLayout mTbLayout;
    private RecyclerLayout mRvlList;
    private int mMonth;
    private List<Integer> titles = new ArrayList<>() ;
    WageDistributionTimeBean mWageDistributionTimeBean;
    OutsideTheContractAdapter mAdapter;
    @Override
    public int getMainLayoutId() {
        return R.layout.activity_outside_the_contract;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("合同外事项");
        mMonth = getIntent().getIntExtra("month",-1);
        mWageDistributionTimeBean = (WageDistributionTimeBean) getIntent().getSerializableExtra("data");
        mTbLayout = findViewById(R.id.tb_layout);
        mRvlList = findViewById(R.id.rvl_list);
        mRvlList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvlList.openLoadmore(false);
        mRvlList.openRefresh(false);
        mRvlList.hideLoading();
        mAdapter = new OutsideTheContractAdapter(R.layout.item_outside_the_contract, mList);
        mRvlList.setAdapter(mAdapter);
        initServerData();
        mTbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int mPostion = tab.getPosition()-1;
                if(mPostion == -1){
                    getData( mPostion);
                    return;
                }
                Integer time = titles.get(mPostion);
                getData( time);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    int position = 0;
    private void initServerData() {
        if (mWageDistributionTimeBean != null && mWageDistributionTimeBean.getCode()==0) {
            titles.clear();
            titles.addAll(mWageDistributionTimeBean.getData());
            mTbLayout.removeAllTabs();
            mTbLayout.addTab(mTbLayout.newTab().setText("全部"));
            for (int i = 0;i < mWageDistributionTimeBean.getData().size();i++){
                mTbLayout.addTab(mTbLayout.newTab().setText(titles.get(i)+""));
                if(mMonth != -1){
                    if(titles.get(i) == mMonth){
                        position = i;
                    }
                }else {
                    position = 0;
                }
            }
            mTbLayout.getTabAt(position).select();
        } else {
            T.showLong(OutsideTheContractActivity.this, mWageDistributionTimeBean.getMessage());
        }
        getData(mMonth);
    }
    List<OutsideTheContractBean.DataBean> mList = new ArrayList<>();
    private void getData(int month) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/output/getMatterList"+"?month="+month)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<OutsideTheContractBean>() {
                    @Override
                    public void onSuccess(OutsideTheContractBean outsideTheContractBean, okhttp3.Call call, okhttp3.Response response) {

                        if (outsideTheContractBean != null && outsideTheContractBean.getCode()==0 ) {
                            if(outsideTheContractBean.getData() !=null){
                                mList.clear();
                                mList.addAll(outsideTheContractBean.getData());
                                mRvlList.notifyDataSetChanged();
                            }else {
                                mList.clear();
                                mRvlList.notifyDataSetChanged();
                                T.showLong(OutsideTheContractActivity.this,"暂无数据");
                            }
                        } else {
                            T.showLong(OutsideTheContractActivity.this, outsideTheContractBean.getMessage());
                        }
                    }
                });
    }
}
