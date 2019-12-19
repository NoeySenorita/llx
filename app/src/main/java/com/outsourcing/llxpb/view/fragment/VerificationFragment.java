package com.outsourcing.llxpb.view.fragment;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.CheckInProjectBean;
import com.outsourcing.llxpb.Bean.PeopleBean;
import com.outsourcing.llxpb.Bean.PersonnelListBean;
import com.outsourcing.llxpb.Bean.ProjectListBean;
import com.outsourcing.llxpb.MainActivity;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.PersonnerListAdpater;
import com.outsourcing.llxpb.adapter.SelectProjectAdapter;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.ui.base.BaseFragment;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.PersonnelReportOutActivity;
import com.outsourcing.llxpb.view.activity.PhotographPersonnerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VerificationFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerLayout mLv_personnel;
    private TextView mTv_num;
    private PersonnelListBean mPersonnelListBean;
    private View show;
    private List<PersonnelListBean.DataBean.MigrantWorkerListBean> mListBeans;
    @Override
    public View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_verification, null, false);
        mLv_personnel = view.findViewById(R.id.lv_personnel);
        mTv_num = view.findViewById(R.id.tv_num);
        show = view.findViewById(R.id.show);
        mLv_personnel.setLayoutManager(new GridLayoutManager(VerificationFragment.this.getContext(),1));
        mLv_personnel.setOnRefreshListener(new RecyclerLayout.OnRefreshListener() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {//刷新数据
                refreshLayout.finishRefreshing();
                initServieData(1,999,true,refreshLayout,true);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {//加载更多
                if(mPersonnelListBean != null && mPersonnelListBean.getData() != null ){
                    initServieData(mPersonnelListBean.getData().getPage()+1,mPersonnelListBean.getData().getPage_size(),false,refreshLayout,false);
                }
            }
        });
        mListBeans = new ArrayList<>();
        mLv_personnel.setAdapter(new PersonnerListAdpater(R.layout.item_personner_list,mListBeans));
        view.findViewById(R.id.rl_out).setOnClickListener(this);
        view.findViewById(R.id.rl_in).setOnClickListener(this);
       // initServieData(1,999,true,null,true);
        getBarTool().hideTitleBar();
        EventBus.getDefault().register(this);
        initServieData(1,999,true,null,true);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        initServieData(1,999,true,null,true);
    }

    public void initServieData(int page, int page_size, final boolean stateFre, final TwinklingRefreshLayout refreshLayout, final boolean state) {
        if(getBarTool()== null){
            return;
        }
        OkGo.get(HttpHost.httpHost + "/subcontractor/roster/search?status=-1&page="+page+"&page_size="+page_size)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<PersonnelListBean>() {
                    @Override
                    public void onSuccess(PersonnelListBean res, okhttp3.Call call, okhttp3.Response response) {
                        if(VerificationFragment.this.getContext() != null){
                            if(mListBeans!= null && mLv_personnel != null){
                                mPersonnelListBean = res;
                                if(res!= null && res.getCode()== 0 && mTv_num !=null&&mListBeans !=null&& mLv_personnel !=null){
                                    if(res.getData() != null){
                                        mTv_num.setText(res.getData().getIn_project_total()+"");
                                        if(res.getData().getMigrant_worker_list() != null && res.getData().getMigrant_worker_list().size() > 0){
                                            if(stateFre){
                                                mListBeans.clear();
                                                mListBeans.addAll(res.getData().getMigrant_worker_list());
                                                if(refreshLayout != null)
                                                    refreshLayout.finishRefreshing();
                                            }else {
                                                mListBeans.addAll(res.getData().getMigrant_worker_list());
                                                if(refreshLayout!= null)
                                                    refreshLayout.finishLoadmore();
                                            }
                                            //  mLv_personnel.hideLoading();
                                            mLv_personnel.notifyDataSetChanged();
                                        }else {
                                            if(refreshLayout != null){
                                                if(stateFre){
                                                    refreshLayout.finishRefreshing();
                                                }else {
                                                    refreshLayout.finishLoadmore();
                                                }
                                            }
                                            if(state){
                                                T.showLong(VerificationFragment.this.getContext(),"暂无人员");
                                            }else {
                                                T.showLong(VerificationFragment.this.getContext(),"已经到底啦");
                                            }
                                        }
                                    }else {
                                        mTv_num.setText("");
                                        mListBeans.clear();
                                        mLv_personnel.notifyDataSetChanged();
                                    }

                                }else {
                                    T.showLong(VerificationFragment.this.getContext(),res.getMessage());
                                }
                            }
                        }


                    }
                });
    }

    @Override
    protected void initContentView(View contentView) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    public  int dip2px( float dpValue) {
        final float scale = this.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    Intent mIntent;

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.rl_out){
            if(mListBeans.size() > 0){
                mIntent = new Intent(this.getContext(), PersonnelReportOutActivity.class);
                mIntent.putExtra("data", (Serializable) mPersonnelListBean.getData().getMigrant_worker_list());
                this.getContext().startActivity(mIntent);
            }else {
                T.showLong(VerificationFragment.this.getContext(),"暂无人员");
            }

        }else if(v.getId() == R.id.rl_in){
            /*mIntent = new Intent(this.getContext(), PersonnelReportInActivity.class);
            this.getContext().startActivity(mIntent);*/
            checkInProject();

        }
    }

    private void checkInProject() {
        long nowTime = System.currentTimeMillis();
        rl_pb.setVisibility(View.VISIBLE);
        if(UserUtil.city_id > 0 && nowTime < UserUtil.city_id_time && UserUtil.allow_execute_entry == 1){
            mIntent = new Intent(this.getContext(),PhotographPersonnerActivity.class);
            UserUtil.sendPerposesActivitys = new ArrayList<>();
            this.getContext().startActivity(mIntent);
        }else {
            OkGo.get(HttpHost.httpHost + "/subcontractor/roster/checkAllowExecuteEntry?"+"lat="+UserUtil.lat+"&lon="+UserUtil.lon+"&city_id="+UserUtil.city_id)
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .headers("token", UserUtil.token)
                    .headers("uuid", UserUtil.uuid)
                    .headers("project_id", UserUtil.projectId)
                    .execute(new JsonCallBack<CheckInProjectBean>() {
                        @Override
                        public void onSuccess(CheckInProjectBean projectListBean, okhttp3.Call call, okhttp3.Response response) {
                            rl_pb.setVisibility(View.GONE);
                            if (projectListBean != null && projectListBean.getCode()== 0 ) {
                                if( projectListBean.getData() !=null&&projectListBean.getData().getAllow_execute_entry() == 1){
                                    UserUtil.city_id = projectListBean.getData().getCity_id();
                                    UserUtil.city_id_time = System.currentTimeMillis()+1200000;
                                    UserUtil.allow_execute_entry = projectListBean.getData().getAllow_execute_entry();
                                    mIntent = new Intent(VerificationFragment.this.getContext(),PhotographPersonnerActivity.class);
                                    UserUtil.sendPerposesActivitys = new ArrayList<>();
                                    VerificationFragment. this.getContext().startActivity(mIntent);
                                }else {
                                    T.showLong(VerificationFragment.this.getContext(), projectListBean.getData().getCheck_msg());
                                }
                            } else {
                                T.showLong(VerificationFragment.this.getContext(), projectListBean.getMessage());
                            }
                        }
                    });
        }

    }
}
