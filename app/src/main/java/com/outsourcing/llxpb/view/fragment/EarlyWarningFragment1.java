package com.outsourcing.llxpb.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.EarlyWBean1;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.EarlyWarningAdapter1;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWarningFragment1 extends BaseFragment {

    private RecyclerView mRl1;
    List<EarlyWBean1.DataBean> mDataBeans;
    private EarlyWarningAdapter1 mEarlyWarningAdapter1;

    @Override
    public View getContentView() {
        return View.inflate(getContext(), R.layout.fragment_early_warning1,null);
    }

    @Override
    protected void initContentView(View contentView) {
        getBarTool().hideIvTitleBar();
        getBarTool().hideTitleBar();
        mRl1 = contentView.findViewById(R.id.rl1);
        mDataBeans = new ArrayList<>();
        mRl1.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mEarlyWarningAdapter1 = new EarlyWarningAdapter1(this.getContext(), mDataBeans);
        mRl1.setAdapter(mEarlyWarningAdapter1);
        EventBus.getDefault().register(this);
        getData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        getData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    public void getData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/warning/verify")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<EarlyWBean1>() {
                    @Override
                    public void onSuccess(EarlyWBean1 earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(EarlyWarningFragment1.this.getContext() != null){

                            if (mEarlyWarningAdapter1 != null&&earlyWBean1 != null && earlyWBean1.getCode()==0 ) {
                                if(earlyWBean1.getData() !=null&& earlyWBean1.getData().size() != 0){
                                    if(mDataBeans != null){
                                        mDataBeans.clear();
                                    }else {
                                        mDataBeans = new ArrayList<>();
                                    }
                                    mRl1.setVisibility(View.VISIBLE);
                                    mDataBeans.clear();
                                    mDataBeans.addAll(earlyWBean1.getData());
                                    mEarlyWarningAdapter1.notifyDataSetChanged();
                                }else {
                                    mDataBeans.clear();
                                    mEarlyWarningAdapter1.notifyDataSetChanged();
                                }

                            } else {
                                if(EarlyWarningFragment1.this.getContext() != null){
                                    if(mRl1 != null)
                                        mRl1.setVisibility(View.GONE);
                                    if(mEarlyWarningAdapter1 != null || mDataBeans != null){
                                        mDataBeans.clear();
                                        mEarlyWarningAdapter1.notifyDataSetChanged();
                                    }
                                    T.showLong(EarlyWarningFragment1.this.getContext(), earlyWBean1.getMessage());
                                }

                            }
                        }
                    }
                });
    }
}
