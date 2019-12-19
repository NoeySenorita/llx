package com.outsourcing.llxpb.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.EarlyWBean4;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.EarlyWarningAdapter4;
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

public class EarlyWarningFragment4 extends BaseFragment {
    private RecyclerView mRl1;
    List<EarlyWBean4.DataBean.SubcontractorStatBean> mDataBeans;
    private EarlyWarningAdapter4 mEarlyWarningAdapter2;
    private TextView mAll1;
    private TextView mAll3;
    private TextView mAll2;
    private TextView mWeigth;
    LinearLayout llSatte;

    @Override
    public View getContentView() {
        return View.inflate(getContext(), R.layout.fragment_early_warning4,null);
    }

    @Override
    protected void initContentView(View contentView) {
        getBarTool().hideIvTitleBar();
        getBarTool().hideTitleBar();
        mRl1 = contentView.findViewById(R.id.rl2);
        llSatte = contentView.findViewById(R.id.llSatte);
        mDataBeans = new ArrayList<>();
        mRl1.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mEarlyWarningAdapter2 = new EarlyWarningAdapter4(this.getContext(), mDataBeans);
        mRl1.setAdapter(mEarlyWarningAdapter2);
        mAll1 = contentView.findViewById(R.id.all1);
        mAll2 = contentView.findViewById(R.id.all2);
        mAll3 = contentView.findViewById(R.id.all3);
        mWeigth = contentView.findViewById(R.id.weigth);
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
        OkGo.get(HttpHost.httpHost + "/subcontractor/warning/potentialLoss")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<EarlyWBean4>() {
                    @Override
                    public void onSuccess(EarlyWBean4 earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(EarlyWarningFragment4.this.getContext() != null){

                            if (mEarlyWarningAdapter2 != null&&earlyWBean1 != null && earlyWBean1.getCode()==0  ) {
                                if(earlyWBean1.getData() != null&& earlyWBean1.getData().getSubcontractor_stat()!= null&&earlyWBean1.getData().getSubcontractor_stat().size() != 0){
                                    if(mDataBeans != null){
                                        mDataBeans.clear();
                                    }else {
                                        mDataBeans = new ArrayList<>();
                                    }
                                    mRl1.setVisibility(View.VISIBLE);
                                    llSatte.setVisibility(View.VISIBLE);
                                    if(earlyWBean1.getData().getSubcontractor_stat() != null)
                                        mDataBeans.addAll(earlyWBean1.getData().getSubcontractor_stat());
                                    mEarlyWarningAdapter2.notifyDataSetChanged();
                                    if(earlyWBean1.getData().getSummary_stat()!=null){
                                        mAll1.setText(earlyWBean1.getData().getSummary_stat().getOutput_apply_amount()/10000+"W");
                                        mAll2.setText(earlyWBean1.getData().getSummary_stat().getWage_apply_amount()/10000+"W");
                                        mAll3.setText(earlyWBean1.getData().getSummary_stat().getPotential_loss()/10000+"W");
                                        if((earlyWBean1.getData().getSummary_stat().getPotential_loss_percent()+"").length() > 3){
                                            mWeigth.setText((earlyWBean1.getData().getSummary_stat().getPotential_loss_percent()+"").substring(0,4)+"%");
                                        }else {
                                            mWeigth.setText(earlyWBean1.getData().getSummary_stat().getPotential_loss_percent()+"%");
                                        }
                                    }
                                }else {
                                    if(mRl1 != null)
                                        mRl1.setVisibility(View.GONE);
                                    if(llSatte != null){
                                        llSatte.setVisibility(View.GONE);
                                    }
                                    if(mEarlyWarningAdapter2 != null || mDataBeans != null){
                                        mDataBeans.clear();
                                        mEarlyWarningAdapter2.notifyDataSetChanged();
                                    }
                                }


                            } else {
                                if(EarlyWarningFragment4.this.getContext() !=null){
                                    T.showLong(EarlyWarningFragment4.this.getContext(), earlyWBean1.getMessage());
                                    if(mRl1 != null)
                                        mRl1.setVisibility(View.GONE);
                                    if(llSatte != null){
                                        llSatte.setVisibility(View.GONE);
                                    }
                                    if(mEarlyWarningAdapter2 != null || mDataBeans != null){
                                        mDataBeans.clear();
                                        mEarlyWarningAdapter2.notifyDataSetChanged();
                                    }
                                }

                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
