package com.outsourcing.llxpb.adapter.common_recyler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonRecyclerAdapter<DATA> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected List<DATA> mDataSet;
    private int mItemResourceId;
    private LayoutInflater mLayoutInflater;
    protected MultiItemTypeSupport<DATA> mTypeSupport;
    private OnItemClickListener<DATA> mClickListener;
    private OnItemLongClickListener<DATA> mLongClickListener;
    protected Context mContext;


    public CommonRecyclerAdapter(int itemResourceId, List<DATA> list) {
        this.mDataSet = list;
        this.mItemResourceId = itemResourceId;
    }

    public CommonRecyclerAdapter(List<DATA> list, MultiItemTypeSupport typeSupport) {
        this(-1, list);
        this.mTypeSupport = typeSupport;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        if (mItemResourceId == -1) {
            mItemResourceId = viewType;
        }
        View view = mLayoutInflater.inflate(mItemResourceId, parent, false);
        if (mTypeSupport != null) {
            mItemResourceId = -1;
        }
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        convert(holder, mDataSet.get(position), position);
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v,mDataSet.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mLongClickListener.onItemLongClick(v,mDataSet.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypeSupport != null) {
            //有多条目时用布局id作为类型标识
            return mTypeSupport.getItemViewLayoutId(mDataSet.get(position));
        }
        //默认情况下，返回0作为类型标识
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return null!=mDataSet?mDataSet.size():0;
    }

    protected abstract void convert(RecyclerViewHolder holder, DATA data, int position);

    public void setmLongClickListener(OnItemLongClickListener<DATA> mLongClickListener) {
        this.mLongClickListener = mLongClickListener;
    }

    public void setmClickListener(OnItemClickListener<DATA> mClickListener) {
        this.mClickListener = mClickListener;
    }
}
