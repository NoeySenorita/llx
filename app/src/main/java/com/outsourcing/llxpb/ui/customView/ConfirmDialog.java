package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.view.View;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.customView.CustomDialog.CustomDialog;
import com.outsourcing.llxpb.util.ui.UIUtils;


/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class ConfirmDialog implements View.OnClickListener {
    private Context mContext;
    private OnActBtnListener mListener;
    private  CustomDialog mDialog;
    private final CustomDialog.Builder mBuilder;

    public ConfirmDialog(Context context, OnActBtnListener listener) {
        this.mContext = context;
        this.mListener = listener;
        mBuilder = new CustomDialog.Builder(mContext)
                .setContentView(R.layout.view_confirm_dialog)
                .setCancelable(false)
                .setWidthAndHeight(UIUtils.dip2Px(281), UIUtils.dip2Px(151));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (mListener != null)
                    mListener.onConfirm();
                break;
            case R.id.tv_cancel:
                if (mListener != null)
                    mListener.onCancel();
                break;
        }
        mDialog.dismiss();
    }

    public interface OnActBtnListener {
        void onCancel();
        void onConfirm();
    }
    public ConfirmDialog setCancelText(String text){
        mBuilder.setText(R.id.tv_cancel, text);
        return this;
    }
    public ConfirmDialog setConfirmText(String text){
        mBuilder.setText(R.id.tv_confirm, text);
        return this;
    }
    public ConfirmDialog setMessage(String text){
        mBuilder.setText(R.id.tv_home, text);
        return this;
    }
    public void show(){
        mDialog = mBuilder.show();
        mDialog.getView(R.id.tv_confirm).setOnClickListener(this);
        mDialog.getView(R.id.tv_cancel).setOnClickListener(this);
    }
}
