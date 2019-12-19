package com.outsourcing.llxpb.ui.customView;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.customView.CustomDialog.CustomDialog;


/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class PicSelectDialog implements View.OnClickListener {
    private Context mContext;
    private OnItemSelectListener mListener;
    private final CustomDialog mDialog;

    public PicSelectDialog(Context context , OnItemSelectListener listener) {
        this.mContext = context;
        this.mListener = listener;
        mDialog = new CustomDialog.Builder(mContext)
                .setContentView(R.layout.view_action_sheet_dialog)
                .fromBottom(true)
                .fullWidth()
                .show();
        mDialog.getView(R.id.tv_pic).setOnClickListener(this);
        mDialog.getView(R.id.tv_take_photo).setOnClickListener(this);
        mDialog.getView(R.id.tv_cancel).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_pic:
                Log.i("PicSelectDialog","onSelectPic()");
                mListener.onSelectPic();
                break;
            case R.id.tv_take_photo:
                Log.i("PicSelectDialog","onTakePhoto()");
                mListener.onTakePhoto();
                break;
        }
        mDialog.dismiss();
    }

    public interface OnItemSelectListener{
        void onSelectPic();
        void onTakePhoto();
    }
}
