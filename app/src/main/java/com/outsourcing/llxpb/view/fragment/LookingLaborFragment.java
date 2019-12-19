package com.outsourcing.llxpb.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.IReleaseBean;
import com.outsourcing.llxpb.Bean.LookingLaborBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.LookingLaborAdapter;
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
 * Created by DELL on 2019/9/23.
 */

public class LookingLaborFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mPg;
    private RelativeLayout mMbg;
    private RelativeLayout mGjg;
    private RelativeLayout mHltg;
    private RelativeLayout mJzg;
    private RelativeLayout mDhg;
    private RelativeLayout mQzg;
    private View mV_pg;
    private View mV_mbg;
    private View mV_gjg;
    private View mV_hltg;
    private View mV_jzg;
    private View mV_dhg;
    private View mV_qzg;
    private TextView mT_pg;
    private TextView mT_mbg;
    private TextView mT_gjg;
    private TextView mT_hltg;
    private TextView mT_jzg;
    private TextView mT_dhg;
    private TextView mT_qzg;
    private RecyclerView mRl_data;
    private LookingLaborAdapter mAdapter;

    @Override
    public View getContentView() {
        return View.inflate(mActivity, R.layout.fragment_look_labor, null);
    }

    @Override
    protected void initContentView(View contentView) {
        getBarTool().hideTitleBar();

        mPg = contentView.findViewById(R.id.pg);
        mMbg = contentView.findViewById(R.id.mbg);
        mGjg = contentView.findViewById(R.id.gjg);
        mHltg = contentView.findViewById(R.id.hltg);
        mJzg = contentView.findViewById(R.id.jzg);
        mDhg = contentView.findViewById(R.id.dhg);
        mQzg = contentView.findViewById(R.id.qzg);
        mPg.setOnClickListener(this);
        mMbg.setOnClickListener(this);
        mGjg.setOnClickListener(this);
        mHltg.setOnClickListener(this);
        mJzg.setOnClickListener(this);
        mDhg.setOnClickListener(this);
        mQzg.setOnClickListener(this);
        mV_pg = contentView.findViewById(R.id.v_pg);
        mV_mbg = contentView.findViewById(R.id.v_mbg);
        mV_gjg = contentView.findViewById(R.id.v_gjg);
        mV_hltg = contentView.findViewById(R.id.v_hltg);
        mV_jzg = contentView.findViewById(R.id.v_jzg);
        mV_dhg = contentView.findViewById(R.id.v_dhg);
        mV_qzg = contentView.findViewById(R.id.v_qzg);

        mT_pg = contentView.findViewById(R.id.t_pg);
        mT_mbg = contentView.findViewById(R.id.t_mbg);
        mT_gjg = contentView.findViewById(R.id.t_gjg);
        mT_hltg = contentView.findViewById(R.id.t_hltg);
        mT_jzg = contentView.findViewById(R.id.t_jzg);
        mT_dhg = contentView.findViewById(R.id.t_dhg);
        mT_qzg = contentView.findViewById(R.id.t_qzg);

        mRl_data = contentView.findViewById(R.id.rl_data);
        mRl_data.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new LookingLaborAdapter(this.getContext(), datas);
        mRl_data.setAdapter(mAdapter);
        EventBus.getDefault().register(this);
        getSevletData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        getSevletData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    IReleaseBean mIReleaseBean;

    public void getSevletData() {
        if(LookingLaborFragment.this.getContext() != null){
            OkGo.get(HttpHost.httpHost + "/subcontractor/labour/getWorkTypes")
                    .headers("Content-Type", "application/json")
                    .headers("token", UserUtil.token)
                    .headers("uuid", UserUtil.uuid)
                    .headers("project_id", UserUtil.projectId)
                    .execute(new JsonCallBack<IReleaseBean>() {
                        @Override
                        public void onSuccess(IReleaseBean iReleaseBean, okhttp3.Call call, okhttp3.Response response) {
                            if (iReleaseBean != null && iReleaseBean.getCode() == 0 && iReleaseBean.getData() != null) {
                                //  mWageDistributionTimeBean = wageDistributionTimeBean;
                                mIReleaseBean = iReleaseBean;
                            } else {
                                mIReleaseBean= null;
                                T.showLong(LookingLaborFragment.this.getContext(), iReleaseBean.getMessage());
                            }
                        }
                    });

            getList(1);
        }

    }

    List<LookingLaborBean.DataBean> datas = new ArrayList<>();

    private void getList(int id) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/labour/search?work_type_id=" + id)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<LookingLaborBean>() {
                    @Override
                    public void onSuccess(LookingLaborBean lookingLaborBean, okhttp3.Call call, okhttp3.Response response) {
                        if (lookingLaborBean != null && lookingLaborBean.getCode() == 0) {
                            if (datas != null && lookingLaborBean.getData() != null && mAdapter != null) {
                                datas.clear();
                                datas.addAll(lookingLaborBean.getData());
                                mRl_data.setVisibility(View.VISIBLE);
                                if (lookingLaborBean.getData().size() == 0) {
                                    T.showLong(LookingLaborFragment.this.getContext(), "暂无数据");
                                    mRl_data.setVisibility(View.GONE);
                                }

                                mAdapter.notifyDataSetChanged();
                            }

                        } else {
                            T.showLong(LookingLaborFragment.this.getContext(), lookingLaborBean.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pg:
                mPg.setBackgroundColor(0xffffffff);
                mMbg.setBackgroundColor(0xffEEEEEE);
                mGjg.setBackgroundColor(0xffEEEEEE);
                mHltg.setBackgroundColor(0xffEEEEEE);
                mJzg.setBackgroundColor(0xffEEEEEE);
                mDhg.setBackgroundColor(0xffEEEEEE);
                mQzg.setBackgroundColor(0xffEEEEEE);

                mT_pg.setTextColor(0xffE88110);
                mT_mbg.setTextColor(0xff333333);
                mT_gjg.setTextColor(0xff333333);
                mT_hltg.setTextColor(0xff333333);
                mT_jzg.setTextColor(0xff333333);
                mT_dhg.setTextColor(0xff333333);
                mT_qzg.setTextColor(0xff333333);

                mV_pg.setVisibility(View.VISIBLE);
                mV_mbg.setVisibility(View.GONE);
                mV_gjg.setVisibility(View.GONE);
                mV_hltg.setVisibility(View.GONE);
                mV_jzg.setVisibility(View.GONE);
                mV_dhg.setVisibility(View.GONE);
                mV_qzg.setVisibility(View.GONE);
                getList(1);
                break;
            case R.id.mbg:
                mMbg.setBackgroundColor(0xffffffff);
                mPg.setBackgroundColor(0xffEEEEEE);
                mGjg.setBackgroundColor(0xffEEEEEE);
                mHltg.setBackgroundColor(0xffEEEEEE);
                mJzg.setBackgroundColor(0xffEEEEEE);
                mDhg.setBackgroundColor(0xffEEEEEE);
                mQzg.setBackgroundColor(0xffEEEEEE);

                mT_mbg.setTextColor(0xffE88110);
                mT_pg.setTextColor(0xff333333);
                mT_gjg.setTextColor(0xff333333);
                mT_hltg.setTextColor(0xff333333);
                mT_jzg.setTextColor(0xff333333);
                mT_dhg.setTextColor(0xff333333);
                mT_qzg.setTextColor(0xff333333);

                mV_mbg.setVisibility(View.VISIBLE);
                mV_pg.setVisibility(View.GONE);
                mV_gjg.setVisibility(View.GONE);
                mV_hltg.setVisibility(View.GONE);
                mV_jzg.setVisibility(View.GONE);
                mV_dhg.setVisibility(View.GONE);
                mV_qzg.setVisibility(View.GONE);

                getList(4);
                break;
            case R.id.gjg:
                getList(2);
                mGjg.setBackgroundColor(0xffffffff);
                mPg.setBackgroundColor(0xffEEEEEE);
                mMbg.setBackgroundColor(0xffEEEEEE);
                mHltg.setBackgroundColor(0xffEEEEEE);
                mJzg.setBackgroundColor(0xffEEEEEE);
                mDhg.setBackgroundColor(0xffEEEEEE);
                mQzg.setBackgroundColor(0xffEEEEEE);

                mT_gjg.setTextColor(0xffE88110);
                mT_pg.setTextColor(0xff333333);
                mT_mbg.setTextColor(0xff333333);
                mT_hltg.setTextColor(0xff333333);
                mT_jzg.setTextColor(0xff333333);
                mT_dhg.setTextColor(0xff333333);
                mT_qzg.setTextColor(0xff333333);

                mV_gjg.setVisibility(View.VISIBLE);
                mV_pg.setVisibility(View.GONE);
                mV_mbg.setVisibility(View.GONE);
                mV_hltg.setVisibility(View.GONE);
                mV_jzg.setVisibility(View.GONE);
                mV_dhg.setVisibility(View.GONE);
                mV_qzg.setVisibility(View.GONE);
                break;
            case R.id.hltg:
                getList(3);
                mHltg.setBackgroundColor(0xffffffff);
                mPg.setBackgroundColor(0xffEEEEEE);
                mMbg.setBackgroundColor(0xffEEEEEE);
                mGjg.setBackgroundColor(0xffEEEEEE);
                mJzg.setBackgroundColor(0xffEEEEEE);
                mDhg.setBackgroundColor(0xffEEEEEE);
                mQzg.setBackgroundColor(0xffEEEEEE);

                mT_hltg.setTextColor(0xffE88110);
                mT_pg.setTextColor(0xff333333);
                mT_mbg.setTextColor(0xff333333);
                mT_gjg.setTextColor(0xff333333);
                mT_jzg.setTextColor(0xff333333);
                mT_dhg.setTextColor(0xff333333);
                mT_qzg.setTextColor(0xff333333);

                mV_hltg.setVisibility(View.VISIBLE);
                mV_pg.setVisibility(View.GONE);
                mV_mbg.setVisibility(View.GONE);
                mV_gjg.setVisibility(View.GONE);
                mV_jzg.setVisibility(View.GONE);
                mV_dhg.setVisibility(View.GONE);
                mV_qzg.setVisibility(View.GONE);
                break;
            case R.id.jzg:
                getList(5);
                mJzg.setBackgroundColor(0xffffffff);
                mPg.setBackgroundColor(0xffEEEEEE);
                mMbg.setBackgroundColor(0xffEEEEEE);
                mGjg.setBackgroundColor(0xffEEEEEE);
                mHltg.setBackgroundColor(0xffEEEEEE);
                mDhg.setBackgroundColor(0xffEEEEEE);
                mQzg.setBackgroundColor(0xffEEEEEE);

                mT_jzg.setTextColor(0xffE88110);
                mT_pg.setTextColor(0xff333333);
                mT_mbg.setTextColor(0xff333333);
                mT_gjg.setTextColor(0xff333333);
                mT_hltg.setTextColor(0xff333333);
                mT_dhg.setTextColor(0xff333333);
                mT_qzg.setTextColor(0xff333333);

                mV_jzg.setVisibility(View.VISIBLE);
                mV_pg.setVisibility(View.GONE);
                mV_mbg.setVisibility(View.GONE);
                mV_gjg.setVisibility(View.GONE);
                mV_hltg.setVisibility(View.GONE);
                mV_dhg.setVisibility(View.GONE);
                mV_qzg.setVisibility(View.GONE);
                break;
            case R.id.dhg:
                getList(6);
                mDhg.setBackgroundColor(0xffffffff);
                mPg.setBackgroundColor(0xffEEEEEE);
                mMbg.setBackgroundColor(0xffEEEEEE);
                mGjg.setBackgroundColor(0xffEEEEEE);
                mHltg.setBackgroundColor(0xffEEEEEE);
                mJzg.setBackgroundColor(0xffEEEEEE);
                mQzg.setBackgroundColor(0xffEEEEEE);

                mT_dhg.setTextColor(0xffE88110);
                mT_pg.setTextColor(0xff333333);
                mT_mbg.setTextColor(0xff333333);
                mT_gjg.setTextColor(0xff333333);
                mT_hltg.setTextColor(0xff333333);
                mT_jzg.setTextColor(0xff333333);
                mT_qzg.setTextColor(0xff333333);

                mV_dhg.setVisibility(View.VISIBLE);
                mV_pg.setVisibility(View.GONE);
                mV_mbg.setVisibility(View.GONE);
                mV_gjg.setVisibility(View.GONE);
                mV_hltg.setVisibility(View.GONE);
                mV_qzg.setVisibility(View.GONE);
                mV_jzg.setVisibility(View.GONE);
                break;
            case R.id.qzg:
                getList(7);
                mQzg.setBackgroundColor(0xffffffff);
                mPg.setBackgroundColor(0xffEEEEEE);
                mMbg.setBackgroundColor(0xffEEEEEE);
                mGjg.setBackgroundColor(0xffEEEEEE);
                mHltg.setBackgroundColor(0xffEEEEEE);
                mJzg.setBackgroundColor(0xffEEEEEE);
                mDhg.setBackgroundColor(0xffEEEEEE);

                mT_qzg.setTextColor(0xffE88110);
                mT_pg.setTextColor(0xff333333);
                mT_mbg.setTextColor(0xff333333);
                mT_gjg.setTextColor(0xff333333);
                mT_hltg.setTextColor(0xff333333);
                mT_jzg.setTextColor(0xff333333);
                mT_dhg.setTextColor(0xff333333);

                mV_qzg.setVisibility(View.VISIBLE);
                mV_pg.setVisibility(View.GONE);
                mV_mbg.setVisibility(View.GONE);
                mV_gjg.setVisibility(View.GONE);
                mV_hltg.setVisibility(View.GONE);
                mV_dhg.setVisibility(View.GONE);
                mV_jzg.setVisibility(View.GONE);
                break;
        }
    }
}
