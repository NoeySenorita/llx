package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.WorkerListResult;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.CommonRecyclerAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.OnItemClickListener;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.RecyclerViewHolder;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.util.ui.UIUtils;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLServerSocket;

import okhttp3.Call;
import okhttp3.Response;

public class ContractRosterActivity extends CommonActivity implements RecyclerLayout.OnRefreshListener {
    private RecyclerLayout mRvlList;
    private int subcontractor_id;
    private int mPageSize;
    private int mPage;
    private String mToken;
    private String mUuid;
    private int mProjectId;
    private List<WorkerListResult.DataBean.MigrantWorkerListBean> mList;
    private WorkerAdapter mAdapter;
    private boolean hasMoreData;
    EditText checkData;
    private List<WorkerListResult.DataBean.MigrantWorkerListBean> mMigrant_worker_list;
    private List<WorkerListResult.DataBean.MigrantWorkerListBean> mMigrant_worker_list_check = new ArrayList<>();

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_contract_roster;
    }

    @Override
    protected void findViews(View mRootView) {
        //String companyName = getIntent().getStringExtra(Constants.PageParam.SUBCONTRACTOR_NAME);
        getBarTool().setTitle("花名册");
        mRvlList = findViewById(R.id.rvl_list);
        mRvlList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvlList.openRefresh(false);
        mRvlList.setOnRefreshListener(this);
        checkData = findViewById(R.id.checkData);
        checkData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String msg = s.toString();
                if(TextUtils.isEmpty(msg)){
                    mAdapter = new WorkerAdapter(
                            R.layout.view_item_worker_list,mList
                    );
                    mRvlList.setAdapter(mAdapter);
                    mAdapter.setmClickListener(new OnItemClickListener<WorkerListResult.DataBean.MigrantWorkerListBean>() {
                        @Override
                        public void onItemClick(View view, WorkerListResult.DataBean.MigrantWorkerListBean bean, int position) {
                            Intent intent = new Intent(mActivity, WorkerDetailActivity.class);
                            intent.putExtra("migrant_worker_id", bean.getId());
                            startActivity(intent);
                        }
                    });
                    return;
                }
                if(mList != null){
                    String checkMsg  = "";
                    mMigrant_worker_list_check.clear();
                    for (int i = 0; i < mList.size(); i ++){

                        if(mList.get(i).getName().contains(msg)){
                            mMigrant_worker_list_check.add(mList.get(i));
                        }
                    }
                    mAdapter = new WorkerAdapter(
                            R.layout.view_item_worker_list,mMigrant_worker_list_check
                    );
                    mRvlList.setAdapter(mAdapter);
                    mAdapter.setmClickListener(new OnItemClickListener<WorkerListResult.DataBean.MigrantWorkerListBean>() {
                        @Override
                        public void onItemClick(View view, WorkerListResult.DataBean.MigrantWorkerListBean bean, int position) {
                            Intent intent = new Intent(mActivity, WorkerDetailActivity.class);
                            intent.putExtra("migrant_worker_id", bean.getId());
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        mPageSize = 10;
        mPage = 1;
        hasMoreData = true;
        mList = new ArrayList<>();
        fetchData(null);
    }

    private void fetchData(final TwinklingRefreshLayout refreshLayout) {
        if(!hasMoreData){
            if(refreshLayout!=null){
                refreshLayout.finishLoadmore();
            }
            UIUtils.showToast("没有更多数据!");
            return;
        }
        OkGo.get(HttpHost.httpHost + "/subcontractor/user/getRoster?")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params("page", mPage)
                .params("page_size", mPageSize)
                .execute(new JsonCallBack<WorkerListResult>() {
                    @Override
                    public void onSuccess(WorkerListResult result, Call call, Response response) {
                        if (result.getCode() == 0 && result.getData() !=null) {
                            WorkerListResult.DataBean data = result.getData();
                            mMigrant_worker_list = data.getMigrant_worker_list();
                            if(mMigrant_worker_list.size()<mPageSize){
                                hasMoreData = false;
                            }else {
                                hasMoreData = true;
                            }
                            mList.addAll(mMigrant_worker_list);
                            if(refreshLayout==null){
                                mAdapter = new WorkerAdapter(
                                        R.layout.view_item_worker_list,mList
                                );
                                mRvlList.setAdapter(mAdapter);
                                mAdapter.setmClickListener(new OnItemClickListener<WorkerListResult.DataBean.MigrantWorkerListBean>() {
                                    @Override
                                    public void onItemClick(View view, WorkerListResult.DataBean.MigrantWorkerListBean bean, int position) {
                                        Intent intent = new Intent(mActivity, WorkerDetailActivity.class);
                                        intent.putExtra("migrant_worker_id", bean.getId());
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                mAdapter = new WorkerAdapter(
                                        R.layout.view_item_worker_list,mList
                                );
                                mRvlList.setAdapter(mAdapter);
                                mAdapter.setmClickListener(new OnItemClickListener<WorkerListResult.DataBean.MigrantWorkerListBean>() {
                                    @Override
                                    public void onItemClick(View view, WorkerListResult.DataBean.MigrantWorkerListBean bean, int position) {
                                        Intent intent = new Intent(mActivity, WorkerDetailActivity.class);
                                        intent.putExtra("migrant_worker_id", bean.getId());
                                        startActivity(intent);
                                    }
                                });
                                refreshLayout.finishLoadmore();
                            }

                        } else {
                            UIUtils.showToast(result.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onRefresh(TwinklingRefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
        mPage++;
        fetchData(refreshLayout);
    }

    public void notCheck(View view) {
        checkData.setText("");
    }

    public class WorkerAdapter extends CommonRecyclerAdapter<WorkerListResult.DataBean.MigrantWorkerListBean> {
        public WorkerAdapter(int itemResourceId, List<WorkerListResult.DataBean.MigrantWorkerListBean> list) {
            super(itemResourceId, list);
        }

        @Override
        protected void convert(RecyclerViewHolder holder,
                               WorkerListResult.DataBean.MigrantWorkerListBean bean,
                               int position) {
            String sex = bean.getSex()==1?"男":"女";
            /*holder.setText(R.id.tv_worker_name,bean.getName())
                    .setText(R.id.tv_worker_order,bean.getId()+"")
                    .setText(R.id.tv_worker_sex,sex)
                    .setText(R.id.tv_worker_nation,bean.getNative_place())
                    .setText(R.id.tv_worker_type,bean.getWork_type_name())
                    .setText(R.id.tv_worker_age,bean.getAge()+"");*/
            TextView tv_worker_name = holder.getView(R.id.tv_worker_name);
            TextView tv_worker_order = holder.getView(R.id.tv_worker_order);
            TextView tv_worker_sex = holder.getView(R.id.tv_worker_sex);
            TextView tv_worker_nation = holder.getView(R.id.tv_worker_nation);
            TextView tv_worker_type = holder.getView(R.id.tv_worker_type);
            TextView tv_worker_age = holder.getView(R.id.tv_worker_age);
            tv_worker_name.setText(bean.getName());
            tv_worker_order.setText(bean.getId()+"");
            tv_worker_sex.setText(sex);
            tv_worker_nation.setText(bean.getNative_place());
            tv_worker_type.setText(bean.getWork_type_name());
            tv_worker_age.setText(bean.getAge()+"");
            if(bean.getProject_status() == 0){
                tv_worker_name.setTextColor(0xffcccccc);
                tv_worker_order.setTextColor(0xffcccccc);
                tv_worker_sex.setTextColor(0xffcccccc);
                tv_worker_nation.setTextColor(0xffcccccc);
                tv_worker_type.setTextColor(0xffcccccc);
                tv_worker_age.setTextColor(0xffcccccc);
            }else {
                tv_worker_name.setTextColor(0xff333333);
                tv_worker_order.setTextColor(0xff333333);
                tv_worker_sex.setTextColor(0xff333333);
                tv_worker_nation.setTextColor(0xff333333);
                tv_worker_type.setTextColor(0xff333333);
                tv_worker_age.setTextColor(0xff333333);
            }

        }
    }
}
