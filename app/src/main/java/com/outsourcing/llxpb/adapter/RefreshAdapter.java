package com.outsourcing.llxpb.adapter;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public class RefreshAdapter extends RefreshListenerAdapter {
    private OnRefreshListener onRefreshListener;
    public RefreshAdapter(OnRefreshListener listener) {
        super();
        this.onRefreshListener = listener;
    }

    @Override
    public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
        super.onPullingDown(refreshLayout, fraction);
    }

    @Override
    public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
        super.onPullingUp(refreshLayout, fraction);
    }

    @Override
    public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
        super.onPullDownReleasing(refreshLayout, fraction);
    }

    @Override
    public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
        super.onPullUpReleasing(refreshLayout, fraction);
    }

    @Override
    public void onRefresh(TwinklingRefreshLayout refreshLayout) {
        super.onRefresh(refreshLayout);
        onRefreshListener.onRefresh(refreshLayout);
    }

    @Override
    public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
        super.onLoadMore(refreshLayout);
        onRefreshListener.onLoadMore(refreshLayout);
    }

    @Override
    public void onFinishRefresh() {
        super.onFinishRefresh();
    }

    @Override
    public void onFinishLoadMore() {
        super.onFinishLoadMore();
    }

    @Override
    public void onRefreshCanceled() {
        super.onRefreshCanceled();
    }

    @Override
    public void onLoadmoreCanceled() {
        super.onLoadmoreCanceled();
    }

    public interface OnRefreshListener{
        void onRefresh(TwinklingRefreshLayout refreshLayout);
        void onLoadMore(TwinklingRefreshLayout refreshLayout);
    }
}
