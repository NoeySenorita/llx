package com.outsourcing.llxpb.ui.customView.CustomDialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

class ViewHelper {

    private View mContentView;
    private SparseArray<WeakReference<View>> mChildViews = new SparseArray<>();

    public ViewHelper(Context context, int layoutResId) {
        this.mContentView = LayoutInflater.from(context).inflate(layoutResId, null);
    }

    public ViewHelper() {

    }

    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    public void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
    }
    public void setHint(int viewId, CharSequence text) {
        EditText et = getView(viewId);
        et.setHint(text);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    public  <T extends View> T getView(int viewId) {
        View view = null;
        WeakReference<View> reference = mChildViews.get(viewId);
        if (reference != null) {
            view = reference.get();
        } else {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mChildViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    public View getContentView() {
        return mContentView;
    }

    public void bindCloseView(int mCloseViewId, final DialogController controller) {
        getView(mCloseViewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.getDialog().dismiss();
            }
        });
    }
}
