package com.outsourcing.llxpb.view.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.MonthlyCompletionBean;
import com.outsourcing.llxpb.Bean.WageDistributionTimeBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.MonthlyCompletionAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.OnItemClickListener;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.CreateMonthlyCDAActivity;
import com.outsourcing.llxpb.view.activity.MonthlyCompletionDeclarationMessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MonthlyCompletionDeclarationFragment extends BaseFragment {
    WageDistributionTimeBean mWageDistributionTimeBean;
    private TextView mAll_sb;
    private TextView mAll_sf;
    private TextView mAll_qf;
    private Intent mIntent;
    private String mTime;

    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_monthly_completion_declaratio, null, false);
        return view;
    }

    private TabLayout mTbLayout;
    private RecyclerLayout mRvlList;
    private List<Integer> titles = new ArrayList<>() ;
    private MonthlyCompletionAdapter mAdapter;
    private List<MonthlyCompletionBean.DataBean.MigrantWorkerStatBean> mList = new ArrayList<>();



    @Override
    protected void initContentView(View contentView) {
        getBarTool().hideTitleBar();
        mTbLayout = contentView.findViewById(R.id.tb_layout);
        mRvlList = contentView.findViewById(R.id.rvl_list);
        mRvlList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvlList.hideLoading();
        mRvlList.openLoadmore(false);
        mRvlList.openRefresh(false);
        mAll_sb = contentView.findViewById(R.id.all_sb);
        mAll_sf = contentView.findViewById(R.id.all_sf);
        mAll_qf = contentView.findViewById(R.id.all_qf);
        contentView.findViewById(R.id.createMoneyData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonthlyCompletionDeclarationFragment.this.getContext().startActivity(new Intent(MonthlyCompletionDeclarationFragment.this.getContext(),CreateMonthlyCDAActivity.class));
            }
        });

        EventBus.getDefault().register(this);
        initTime();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        initTime();;
        getData( -1);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    int mPostion = -1;
    public void initTime() {
        if(MonthlyCompletionDeclarationFragment.this.getContext() != null){
            OkGo.get(HttpHost.httpHost + "/subcontractor/output/getContractMonthList")
                    .headers("Content-Type", "application/json")
                    .headers("token", UserUtil.token)
                    .headers("uuid", UserUtil.uuid)
                    .headers("project_id", UserUtil.projectId)
                    .execute(new JsonCallBack<WageDistributionTimeBean>() {
                        @Override
                        public void onSuccess(WageDistributionTimeBean wageDistributionTimeBean, okhttp3.Call call, okhttp3.Response response) {
                            if (wageDistributionTimeBean != null && wageDistributionTimeBean.getCode()==0 && mTbLayout != null) {
                                if( titles != null && wageDistributionTimeBean.getData() != null&& wageDistributionTimeBean.getData().size() > 0 ){
                                    mWageDistributionTimeBean = wageDistributionTimeBean;
                                    titles.clear();
                                    titles.addAll(wageDistributionTimeBean.getData());
                                    mTbLayout.removeAllTabs();
                                    mTbLayout.addTab(mTbLayout.newTab().setText("全部"));
                                    for (int i = 0;i < wageDistributionTimeBean.getData().size();i++){
                                        mTbLayout.addTab(mTbLayout.newTab().setText(titles.get(i)+""));
                                    }
                                }else {
                                    titles.clear();
                                    mTbLayout.removeAllTabs();
                                    mTbLayout.addTab(mTbLayout.newTab().setText("全部"));
                                    T.showLong(MonthlyCompletionDeclarationFragment.this.getContext(),"暂无数据");
                                }

                            } else {
                                T.showLong(MonthlyCompletionDeclarationFragment.this.getContext(), wageDistributionTimeBean.getMessage());
                            }
                        }
                    });
        }


    }
    MonthlyCompletionBean mMonthlyCompletionBean;
    private void getData(int time) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/wage/getMigrantWorkerStat"+"?month="+time)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MonthlyCompletionBean>() {
                    @Override
                    public void onSuccess(MonthlyCompletionBean monthlyCompletionBean, okhttp3.Call call, okhttp3.Response response) {
                        MonthlyCompletionDeclarationFragment.this.mMonthlyCompletionBean = monthlyCompletionBean;
                        if (mMonthlyCompletionBean != null && mMonthlyCompletionBean.getCode()==0) {
                            mList.clear();
                            if(monthlyCompletionBean.getData() != null){
                                MonthlyCompletionBean .DataBean. MigrantWorkerStatBean migrantWorkerStatBean = new MonthlyCompletionBean .DataBean. MigrantWorkerStatBean();
                                migrantWorkerStatBean.setName("合计");
                                migrantWorkerStatBean.setApply_amount(mMonthlyCompletionBean.getData().getMigrant_worker_summary().getApply_total());
                                migrantWorkerStatBean.setPay_amount(mMonthlyCompletionBean.getData().getMigrant_worker_summary().getPay_total());
                                migrantWorkerStatBean.setUnpaid_amount(mMonthlyCompletionBean.getData().getMigrant_worker_summary().getUnpaid_total());

                                mList.add(migrantWorkerStatBean);
                                if(mMonthlyCompletionBean.getData().getMigrant_worker_stat()!= null){
                                    mList.addAll(mMonthlyCompletionBean.getData().getMigrant_worker_stat());
                                }
                            }
                            mRvlList.notifyDataSetChanged();

                        } else {
                            mList.clear();mRvlList.notifyDataSetChanged();
                            T.showLong(MonthlyCompletionDeclarationFragment.this.getContext(), mMonthlyCompletionBean.getMessage());
                        }
                    }
                });

    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter = new MonthlyCompletionAdapter(R.layout.view_item_common_sub_page_list_4row, mList);
        mRvlList.setAdapter(mAdapter);
        mAdapter.setmClickListener(new OnItemClickListener<MonthlyCompletionBean.DataBean.MigrantWorkerStatBean>() {
            @Override
            public void onItemClick(View view, MonthlyCompletionBean.DataBean.MigrantWorkerStatBean migrantWorkerStatBean, int position) {
                if(position == 0){
                    return;
                }
                mIntent = new Intent(MonthlyCompletionDeclarationFragment.this.getContext(),MonthlyCompletionDeclarationMessageActivity.class);
                if(mPostion == -1){
                    mTime = "-1";
                }else {
                    mTime = (titles.get(mPostion)+"").substring(0,4);
                }
                mIntent.putExtra("time", mTime);
                mIntent.putExtra("data",migrantWorkerStatBean);
                MonthlyCompletionDeclarationFragment.this.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();

        mTbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPostion = tab.getPosition()-1;
                if(mPostion == -1){
                    getData( -1);
                    mAll_sb.setText("累计申报");
                    mAll_sf.setText("累计实发");
                    mAll_qf.setText("累计欠发");
                }else
                {
                    mAll_sb.setText("本月申报");
                    mAll_sf.setText("本月实发");
                    mAll_qf.setText("本月欠发");
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

    public void createMoneyData(View view) {

    }
}
