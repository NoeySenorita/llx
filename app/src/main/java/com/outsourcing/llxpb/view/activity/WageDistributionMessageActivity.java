package com.outsourcing.llxpb.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.WageDIstributionMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.WageDIstributionMessageAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

public class WageDistributionMessageActivity extends CommonActivity {
    private RecyclerLayout mRvlList;
    private int mContract_id;
    private int mMonth;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_wage_distribution_message;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle(getIntent().getStringExtra("name"));
        getBarTool().setTitle(getIntent().getStringExtra("code"));
        mContract_id = getIntent().getIntExtra("contract_id",-1);
        mMonth = getIntent().getIntExtra("month",-1);
        mRvlList = findViewById(R.id.rvl_list);
        mRvlList.setLayoutManager(new LinearLayoutManager(this));
        mRvlList.openLoadmore(false);
        mRvlList.openRefresh(false);
        mRvlList.hideLoading();
        ((TextView)findViewById(R.id.time)).setText(mMonth == -1? "开累申报总计：":mMonth+"月度申报总计：");
        getData();
    }

    private void getData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/output/getContractItemStat"+"?month="+mMonth+"&contract_id="+mContract_id)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<WageDIstributionMessageBean>() {
                    @Override
                    public void onSuccess(WageDIstributionMessageBean wageDIstributionMessageBean, okhttp3.Call call, okhttp3.Response response) {
                        if(wageDIstributionMessageBean != null&&wageDIstributionMessageBean.getCode() == 0 && wageDIstributionMessageBean.getData() !=null){
                            mRvlList.setAdapter(new WageDIstributionMessageAdapter(R.layout.view_item_common_sub_page_list_4row,wageDIstributionMessageBean.getData().getItem_stat()));
                            ((TextView)findViewById(R.id.money)).setText(wageDIstributionMessageBean.getData().getAmount()+"");
                        }else {
                            T.showShort(WageDistributionMessageActivity.this,wageDIstributionMessageBean.getMessage());
                        }

                    }
                });
    }


}
