package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.MeHtBean;
import com.outsourcing.llxpb.Bean.WageDistributionTimeBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.MeHtAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.OnItemClickListener;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class MeHtActivity extends CommonActivity {
    private RecyclerLayout mRvlList;
    private MeHtAdapter mAdapter;
    private List<MeHtBean.DataBean.ContractStatBean> mList = new ArrayList<>();
    MeHtBean mWageDistributionBean;
    private String mName;


    @Override
    public int getMainLayoutId() {
        return R.layout.activity_me_ht;
    }

    @Override
    protected void findViews(View mRootView) {
        mName = getIntent().getStringExtra("name");
        getBarTool().setTitle(mName);
        mRvlList = this.findViewById(R.id.rvl_list);
        mRvlList.setLayoutManager(new LinearLayoutManager(this));
        mRvlList.openLoadmore(false);
        mRvlList.openRefresh(false);
        mRvlList.hideLoading();
        mAdapter = new MeHtAdapter(R.layout.view_item_common_sub_page_list_3row, mList);
        mRvlList.setAdapter(mAdapter);
        mAdapter.setmClickListener(new OnItemClickListener<MeHtBean.DataBean.ContractStatBean>() {
            @Override
            public void onItemClick(View view, MeHtBean.DataBean.ContractStatBean contractStatBean, int position) {
                if(position != mList.size()-2){
                    Intent intent;
                    if(position != mList.size()-1){ // 其他正常点击
                        intent = new Intent(MeHtActivity.this,WageDistributionMessageActivity.class);
                        intent.putExtra("contract_id",contractStatBean.getId());
                        intent.putExtra("code",contractStatBean.getCode());
                        intent.putExtra("month",mList.get(position).getAccept_amount());
                        intent.putExtra("name",mName);
                        MeHtActivity.this.startActivity(intent);
                    }else {
                        //点击合同外事项
                        if(mWageDistributionBean != null&&mWageDistributionBean.getData().getMatter_stat()!= null){
                            intent = new Intent(MeHtActivity.this,OutsideTheContractActivity.class);
                            intent.putExtra("month",-1);
                            intent.putExtra("data",mWageDistributionTimeBean);
                            MeHtActivity.this.startActivity(intent);
                        }
                    }
                }
            }
        });
        initTime();
    }

    WageDistributionTimeBean mWageDistributionTimeBean;
    public void initTime() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/output/getContractMonthList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<WageDistributionTimeBean>() {
                    @Override
                    public void onSuccess(WageDistributionTimeBean wageDistributionTimeBean, okhttp3.Call call, okhttp3.Response response) {
                        if (wageDistributionTimeBean != null && wageDistributionTimeBean.getCode()==0&&wageDistributionTimeBean.getData() !=null) {
                            mWageDistributionTimeBean = wageDistributionTimeBean;
                        } else {
                            T.showLong(MeHtActivity.this, wageDistributionTimeBean.getMessage());
                        }
                    }
                });
        getData(-1);
    }
    private void getData(int time) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/user/getContractList")
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MeHtBean>() {
                    @Override
                    public void onSuccess(MeHtBean wageDistributionBean, okhttp3.Call call, okhttp3.Response response) {
                        mWageDistributionBean = wageDistributionBean;
                        if (wageDistributionBean != null && wageDistributionBean.getCode()==0&&wageDistributionBean.getData() !=null) {
                            mList.clear();
                            MeHtBean .DataBean.ContractStatBean contractStatBean1 = new MeHtBean.DataBean.ContractStatBean();
                            contractStatBean1.setCode("合计");
                           // contractStatBean1.setApply_amount(wageDistributionBean.getData().getContract_summary().getApply_total()+"");
                            contractStatBean1.setApply_amount(wageDistributionBean.getData().getContract_summary().getApply_total()+"");
                           // contractStatBean1.setAccept_amount(wageDistributionBean.getData().getContract_summary().getAccept_total()+"");
                            contractStatBean1.setAccept_amount(wageDistributionBean.getData().getContract_summary().getAccept_total()+"");
                            MeHtBean .DataBean.ContractStatBean contractStatBean2 = new MeHtBean.DataBean.ContractStatBean();
                            contractStatBean2.setCode("合同外事项");
                            contractStatBean2.setApply_amount(wageDistributionBean.getData().getMatter_stat().getMatter_count()+"");
                            contractStatBean2.setAccept_amount("");
                            mList.addAll(wageDistributionBean.getData().getContract_stat());
                            mList.add(contractStatBean1);
                            mList.add(contractStatBean2);
                            mRvlList.notifyDataSetChanged();
                        } else {
                            T.showLong(MeHtActivity.this, wageDistributionBean.getMessage());
                        }
                    }
                });

    }
    int mPostion = -1;
}
