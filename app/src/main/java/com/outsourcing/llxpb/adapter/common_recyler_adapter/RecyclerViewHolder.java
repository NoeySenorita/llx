package com.outsourcing.llxpb.adapter.common_recyler_adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mItemView;

    public View getItemView() {
        return mItemView;
    }

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
        this.mItemView = itemView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, view);
            } else {
                throw new IllegalArgumentException("请检查传入的viewId是否正确!");
            }
        }
        return (T) view;
    }

    public RecyclerViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public RecyclerViewHolder setText(int viewId, CharSequence text, int color) {
        TextView tv = getView(viewId);
        tv.setText(text);
        if (color != Color.TRANSPARENT) {
            tv.setTextColor(color);
        }
        return this;
    }

    public RecyclerViewHolder setText(int viewId, CharSequence text, int color, int bgColor) {
        TextView tv = getView(viewId);
        tv.setText(text);
        if (color != Color.TRANSPARENT) {
            tv.setTextColor(color);
        }
        Log.i("bgColor",bgColor+"");
        if (bgColor != Color.TRANSPARENT) {
            tv.setBackgroundColor(bgColor);
        }
        return this;
    }

    public RecyclerViewHolder setTextColor(int viewId,int color){
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }
    public void setEditText(int viewId,CharSequence sequence){
        EditText ev = getView(viewId);
        ev.setText(sequence);
        ev.setSelection(sequence.length());
    }

}
