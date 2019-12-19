package com.outsourcing.llxpb.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.WageDistributionBean;
import com.outsourcing.llxpb.Bean.WageDistributionTimeBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.WageDistributionAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.OnItemClickListener;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.CreateDeclareActivity;
import com.outsourcing.llxpb.view.activity.OutsideTheContractActivity;
import com.outsourcing.llxpb.view.activity.WageDistributionMessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class WageDistributionDeclarationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wage_distribution_declaratio, null, false);
        mTbLayout = view.findViewById(R.id.tb_layout);
        mRvlList = view.findViewById(R.id.rvl_list);
        mRvlList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvlList.openLoadmore(false);
        mRvlList.openRefresh(false);
        mRvlList.hideLoading();
        view.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WageDistributionDeclarationFragment.this.getContext(),CreateDeclareActivity.class);
                intent.putExtra("month",mPostion == -1 ? -1:titles.get(mPostion));
                WageDistributionDeclarationFragment.this.getContext().startActivity(intent);
            }
        });
        initListener();

        EventBus.getDefault().register(this);
        initTime();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        initTime();;
        getData(-1);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    private TabLayout mTbLayout;
    private RecyclerLayout mRvlList;
    private List<Integer> titles = new ArrayList<>() ;
    private WageDistributionAdapter mAdapter;
    private List<WageDistributionBean.DataBean.ContractStatBean> mList = new ArrayList<>();
    WageDistributionBean mWageDistributionBean;
    WageDistributionTimeBean mWageDistributionTimeBean;


    public void initTime() {
        if(WageDistributionDeclarationFragment.this.getContext() !=null){
            OkGo.get(HttpHost.httpHost + "/subcontractor/output/getContractMonthList")
                    .headers("Content-Type", "application/json")
                    .headers("token", UserUtil.token)
                    .headers("uuid", UserUtil.uuid)
                    .headers("project_id", UserUtil.projectId)
                    .execute(new JsonCallBack<WageDistributionTimeBean>() {
                        @Override
                        public void onSuccess(WageDistributionTimeBean wageDistributionTimeBean, okhttp3.Call call, okhttp3.Response response) {
                            if (wageDistributionTimeBean != null && wageDistributionTimeBean.getCode()==0 && titles != null&& wageDistributionTimeBean.getData()!= null && mTbLayout != null) {
                                mWageDistributionTimeBean = wageDistributionTimeBean;
                                titles.clear();
                                titles.addAll(wageDistributionTimeBean.getData());
                                mTbLayout.removeAllTabs();
                                mTbLayout.addTab(mTbLayout.newTab().setText("全部"));
                                for (int i = 0;i < wageDistributionTimeBean.getData().size();i++){
                                    mTbLayout.addTab(mTbLayout.newTab().setText(titles.get(i)+""));
                                }
                            } else {
                                T.showLong(WageDistributionDeclarationFragment.this.getContext(), wageDistributionTimeBean.getMessage());
                            }
                        }
                    });
            getData(-1);
        }

    }


    private void getData(int time) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/output/getContractStat"+"?month="+time)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<WageDistributionBean>() {
                    @Override
                    public void onSuccess(WageDistributionBean wageDistributionBean, okhttp3.Call call, okhttp3.Response response) {
                        mWageDistributionBean = wageDistributionBean;
                        if(mRvlList!= null){
                            if (wageDistributionBean != null && wageDistributionBean.getCode()==0&&wageDistributionBean.getData() != null) {
                                mList.clear();
                                WageDistributionBean .DataBean.ContractStatBean contractStatBean1 = new WageDistributionBean.DataBean.ContractStatBean();
                                contractStatBean1.setCode("合计");
                                contractStatBean1.setApply_amount(wageDistributionBean.getData().getContract_summary().getApply_total()+"");
                                contractStatBean1.setAccept_amount(wageDistributionBean.getData().getContract_summary().getAccept_total()+"");
                                WageDistributionBean .DataBean.ContractStatBean contractStatBean2 = new WageDistributionBean.DataBean.ContractStatBean();
                                contractStatBean2.setCode("合同外事项");
                                contractStatBean2.setApply_amount(wageDistributionBean.getData().getMatter_stat().getMatter_count()+"");
                                contractStatBean2.setAccept_amount("");
                                mList.addAll(wageDistributionBean.getData().getContract_stat());
                                mList.add(contractStatBean1);
                                mList.add(contractStatBean2);
                                mRvlList.notifyDataSetChanged();
                            } else {
                                T.showLong(WageDistributionDeclarationFragment.this.getContext(), wageDistributionBean.getMessage());
                            }
                        }

                    }
                });

    }
    int mPostion = -1;
    private void initListener() {
        mAdapter = new WageDistributionAdapter(R.layout.view_item_common_sub_page_list_3row, mList);
        mRvlList.setAdapter(mAdapter);
        mAdapter.setmClickListener(new OnItemClickListener<WageDistributionBean.DataBean.ContractStatBean>() {
            @Override
            public void onItemClick(View view, WageDistributionBean.DataBean.ContractStatBean contractStatBean, int position) {
                if(position != mList.size()-2){
                    Intent intent;
                    if(position != mList.size()-1){ // 其他正常点击
                        intent = new Intent(WageDistributionDeclarationFragment.this.getContext(),WageDistributionMessageActivity.class);
                        intent.putExtra("contract_id",contractStatBean.getId());
                        intent.putExtra("code",contractStatBean.getCode());
                        intent.putExtra("month",mPostion == -1 ? -1:titles.get(mPostion));
                        WageDistributionDeclarationFragment.this.getContext().startActivity(intent);
                    }else {
                        //点击合同外事项
                        if(mWageDistributionBean != null&&mWageDistributionBean.getData().getMatter_stat()!= null){
                            intent = new Intent(WageDistributionDeclarationFragment.this.getContext(),OutsideTheContractActivity.class);
                            intent.putExtra("month",mPostion == -1 ? -1:titles.get(mPostion));
                            intent.putExtra("data",mWageDistributionTimeBean);
                            WageDistributionDeclarationFragment.this.getContext().startActivity(intent);
                        }
                    }
                }
            }
        });
        mTbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPostion = tab.getPosition()-1;
                if(mPostion == -1){
                    getData( -1);
                }else
                {
                    Integer time = titles.get(mPostion);
                    getData( time);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
