package com.outsourcing.llxpb.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.MonthlyCompletionBean;
import com.outsourcing.llxpb.Bean.MonthlyCompletionDeclarationMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.MonthlyCDMAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class MonthlyCompletionDeclarationMessageActivity extends CommonActivity {
    MonthlyCompletionBean.DataBean.MigrantWorkerStatBean migrantWorkerStatBean;
    private int mTiem;
    private TextView mMoney1;
    private TextView mMoney2;
    private RecyclerView mRl_message;
    private MonthlyCDMAdapter mMonthlyCDMAdapter;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_monthly_completion_declaration_message;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().hideTitleBar();
        TextView tv_title_time = findViewById(R.id.tv_title_time);
        TextView tv_title = findViewById(R.id.tv_title_message);
        mMoney1 = findViewById(R.id.money1);
        mMoney2 = findViewById(R.id.money2);
        mRl_message = findViewById(R.id.rl_message);
        mRl_message.setLayoutManager(new LinearLayoutManager(this));
        mMonthlyCDMAdapter = new MonthlyCDMAdapter(this, mList);
        mRl_message.setAdapter(mMonthlyCDMAdapter);
        findViewById(R.id.rl_back_My).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
        String time = getIntent().getStringExtra("time");
        migrantWorkerStatBean = (MonthlyCompletionBean.DataBean.MigrantWorkerStatBean) getIntent().getSerializableExtra("data");
        mTiem = Integer.parseInt(time);
        tv_title.setText(migrantWorkerStatBean.getName()+"工资明细");
        if(mTiem == -1){
            tv_title_time.setText("全部");
        }else {
            tv_title_time.setText(time);
        }
        getData(mTiem);
    }
    MonthlyCompletionDeclarationMessageBean mMonthlyCompletionDeclarationMessageBean;
    private List<MonthlyCompletionDeclarationMessageBean.DataBean.WageApplyPayListBean> mList = new ArrayList<>();
    private void getData(int time) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/wage/getMigrantWorkerDetailList"+"?year="+time+"&migrant_worker_id="+migrantWorkerStatBean.getId())
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MonthlyCompletionDeclarationMessageBean>() {
                    @Override
                    public void onSuccess(MonthlyCompletionDeclarationMessageBean monthlyCompletionDeclarationMessageBean, okhttp3.Call call, okhttp3.Response response) {
                        MonthlyCompletionDeclarationMessageActivity.this.mMonthlyCompletionDeclarationMessageBean = monthlyCompletionDeclarationMessageBean;
                        if (mMonthlyCompletionDeclarationMessageBean != null && mMonthlyCompletionDeclarationMessageBean.getCode()==0) {
                            if(monthlyCompletionDeclarationMessageBean.getData().getWage_apply_pay_list()== null){
                                T.showLong(MonthlyCompletionDeclarationMessageActivity.this, "暂无数据");
                                return;
                            }
                            if(mMonthlyCompletionDeclarationMessageBean.getData().getWage_apply_summary()!=null){
                                mMoney1.setText(mMonthlyCompletionDeclarationMessageBean.getData().getWage_apply_summary().getApply_total()+"");
                                mMoney2.setText(mMonthlyCompletionDeclarationMessageBean.getData().getWage_apply_summary().getPay_total()+"");
                            }else {
                                mMoney1.setText("");
                                mMoney2.setText("");
                            }
                            mList.clear();
                            if(mMonthlyCompletionDeclarationMessageBean.getData()!= null&&mMonthlyCompletionDeclarationMessageBean.getData().getWage_apply_pay_list()!= null){}
                            mList.addAll(mMonthlyCompletionDeclarationMessageBean.getData().getWage_apply_pay_list());
                            mMonthlyCDMAdapter.notifyDataSetChanged();
                        } else {
                            T.showLong(MonthlyCompletionDeclarationMessageActivity.this, mMonthlyCompletionDeclarationMessageBean.getMessage());
                        }
                    }
                });

    }
}
