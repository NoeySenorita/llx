package com.outsourcing.llxpb.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.IReleaseBean;
import com.outsourcing.llxpb.Bean.IReleaseMyBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.IReleaseAdapter;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.SelectTypeWorkActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/9/23.
 */

public class IReleaseFragment extends BaseFragment {

    private RecyclerView mRl_data;
    private LinearLayoutManager mLayout;
    private List<IReleaseMyBean.DataBean> titles = new ArrayList<>();
    private IReleaseAdapter mAdapter;

    @Override
    public View getContentView() {
        return View.inflate(mActivity, R.layout.fragment_i_release,null);
    }

    @Override
    protected void initContentView(View contentView) {
        getBarTool().hideTitleBar();
        mRl_data = contentView.findViewById(R.id.rl_data);
        mLayout = new LinearLayoutManager(getContext());
        mRl_data.setLayoutManager(mLayout);
        mAdapter = new IReleaseAdapter(getContext(), titles);
        mRl_data.setAdapter(mAdapter);
        contentView.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IReleaseFragment.this.getContext().startActivity(new Intent( IReleaseFragment.this.getContext(), SelectTypeWorkActivity.class));
            }
        });

        EventBus.getDefault().register(this);
        getServletData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        getServletData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getServletData();
    }

    IReleaseBean mIReleaseBean;
    public void getServletData() {

        OkGo.get(HttpHost.httpHost + "/subcontractor/labour/myPubList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<IReleaseMyBean>() {
                    @Override
                    public void onSuccess(IReleaseMyBean iReleaseBean, okhttp3.Call call, okhttp3.Response response) {
                        if(IReleaseFragment.this.getContext() != null){

                            if (iReleaseBean != null && iReleaseBean.getCode()==0) {
                                //  mWageDistributionTimeBean = wageDistributionTimeBean;
                                if(titles != null&&mAdapter != null){
                                    if(iReleaseBean.getData() !=null&&iReleaseBean.getData().size() > 0 ){
                                        titles.clear();
                                        titles.addAll(iReleaseBean.getData());
                                        mAdapter.notifyDataSetChanged();
                                    }else {
                                        titles.clear();
                                        mAdapter.notifyDataSetChanged();
                                        T.showLong(IReleaseFragment.this.getContext(), "暂无数据");
                                    }
                                }

                            } else {
                                T.showLong(IReleaseFragment.this.getContext(), iReleaseBean.getMessage());
                            }
                        }
                    }
                });
    }
}
