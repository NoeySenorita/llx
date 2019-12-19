package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import org.jetbrains.annotations.Nullable;

public class RecyclerLayout extends FrameLayout {
    private RecyclerView mRvList;
    private ProgressBar mPgBar;
    private TextView mTvLoading;
    private TwinklingRefreshLayout mTwlRefresh;
    private RecyclerView.Adapter mAdapter;
    private ImageView mIvEmpty;
    private LinearLayout mLlLoading;
    private OnRefreshListener onRefreshListener;
    public void openRefresh(boolean refresh){
        mTwlRefresh.setEnableRefresh(refresh);
    }
    public void openLoadmore(boolean loadmore){
        mTwlRefresh.setEnableLoadmore(loadmore);
    }
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public RecyclerLayout(Context context) {
        this(context, null);
    }

    public RecyclerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RecyclerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_recycler_layout, null, false);
        init(view,context);
        addView(view);
    }

    private void init(View view,Context context) {
        mRvList = view.findViewById(R.id.rv_list);
        mPgBar = view.findViewById(R.id.pg_bar);
        mTvLoading = view.findViewById(R.id.tv_loading);
        mTwlRefresh = view.findViewById(R.id.twl_refresh);
        mIvEmpty = view.findViewById(R.id.iv_empty);
        mLlLoading = view.findViewById(R.id.ll_loading);

        SinaRefreshView headerView = new SinaRefreshView(context);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mTwlRefresh.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(context);
        mTwlRefresh.setBottomView(loadingView);

        mTwlRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                if(onRefreshListener!=null){
                    onRefreshListener.onRefresh(refreshLayout);
                }
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                if(onRefreshListener!=null){
                    onRefreshListener.onLoadMore(refreshLayout);
                }
            }
        });

    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager manager) {
        mRvList.setLayoutManager(manager);
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mRvList.setAdapter(adapter);
        hideLoading();
        int itemCount = mAdapter.getItemCount();
        if (itemCount > 0) {
            mIvEmpty.setVisibility(GONE);
        } else {
            mIvEmpty.setVisibility(VISIBLE);
        }
    }

    public void notifyDataSetChanged() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
            hideLoading();
            int itemCount = mAdapter.getItemCount();
            if (itemCount > 0) {
                mIvEmpty.setVisibility(GONE);
            } else {
                mIvEmpty.setVisibility(VISIBLE);
            }
        }
    }

    public void showLoading(){
        mLlLoading.setVisibility(VISIBLE);
    }
    public void hideLoading(){
        mLlLoading.setVisibility(GONE);
    }

    public interface OnRefreshListener {
        void onRefresh(TwinklingRefreshLayout refreshLayout);
        void onLoadMore(TwinklingRefreshLayout refreshLayout);
    }
}
