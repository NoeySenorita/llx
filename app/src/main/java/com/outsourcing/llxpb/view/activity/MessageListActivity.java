package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.MessageListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.MessageInfoAdapter;
import com.outsourcing.llxpb.adapter.common_recyler_adapter.OnItemClickListener;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends CommonActivity {
    private RecyclerLayout mLv_personnerl_report_out;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_message_list;
    }
    MessageListBean mPersonnelListBean;
    List<MessageListBean.DataBean.NoticeListBean> mPersonnelListBeans = new ArrayList<>();
    MessageInfoAdapter mPersonnelReportOutAdapter;
    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("通知");
        mLv_personnerl_report_out = findViewById(R.id.lv_personnerl_report_out);
        mLv_personnerl_report_out.setLayoutManager(new GridLayoutManager(MessageListActivity.this,1));
        mLv_personnerl_report_out.setOnRefreshListener(new RecyclerLayout.OnRefreshListener() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {//刷新数据

                refreshLayout.finishRefreshing();
                initServieData(1,999,true,refreshLayout,false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {//加载更多
                if(mPersonnelListBean != null && mPersonnelListBean.getData() != null ){
                    initServieData(mPersonnelListBean.getData().getPage()+1,mPersonnelListBean.getData().getPage_size(),false,refreshLayout,false);
                }
            }
        });
        mPersonnelReportOutAdapter = new MessageInfoAdapter(R.layout.item_messagelist, mPersonnelListBeans);
        mLv_personnerl_report_out.setAdapter(mPersonnelReportOutAdapter);
        mPersonnelReportOutAdapter.setmClickListener(new OnItemClickListener<MessageListBean.DataBean.NoticeListBean>() {
            @Override
            public void onItemClick(View view, MessageListBean.DataBean.NoticeListBean noticeListBean, int position) {
                Intent intent = new Intent(MessageListActivity.this,MessageInfoActivity.class);
                intent.putExtra("id",noticeListBean.getId());
                MessageListActivity.this.startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        initServieData(1,999,true,null,true);
    }

    private void initServieData(int page, int page_size, final boolean stateFre, final TwinklingRefreshLayout refreshLayout, final boolean state) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/notice/getHistoryNoticeList?page="+page+"&page_size="+page_size)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MessageListBean>() {
                    @Override
                    public void onSuccess(MessageListBean res, okhttp3.Call call, okhttp3.Response response) {
                        mPersonnelListBean = res;
                        if(mPersonnelListBean.getCode() == 0){
                            if(res.getData().getNotice_list() != null && res.getData().getNotice_list().size() > 0){
                                mPersonnelListBeans.clear();
                                mPersonnelListBeans.addAll(res.getData().getNotice_list());
                                if(refreshLayout != null)
                                    refreshLayout.finishRefreshing();
                                //  mLv_personnel.hideLoading();
                                mLv_personnerl_report_out.notifyDataSetChanged();
                            }else {
                                if(refreshLayout != null){
                                    if(stateFre){
                                        refreshLayout.finishRefreshing();
                                    }else {
                                        refreshLayout.finishLoadmore();
                                    }
                                }
                                if(state){
                                    T.showLong(MessageListActivity.this,"暂无人员");
                                }else {
                                    T.showLong(MessageListActivity.this,"已经到底啦");
                                }
                            }
                        }else {
                            T.showLong(MessageListActivity.this,res.getMessage());
                        }
                    }
                });
    }
}
