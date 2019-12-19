package com.outsourcing.llxpb.ui.customView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.customView.CustomDialog.CustomDialog;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class LookPhotoDialog implements View.OnClickListener {
    private Context mContext;
    private CustomDialog mDialog;
    private final CustomDialog.Builder mBuilder;
    private int triggerViewId;
    private String imagePath;

    public LookPhotoDialog(Context context) {
        this.mContext = context;
        mBuilder = new CustomDialog.Builder(mContext)
                .setContentView(R.layout.view_look_photo_dialog)
                .setCancelable(false)
                .fullScreen();
    }


    @Override
    public void onClick(View v) {
        mDialog.dismiss();
    }

    public LookPhotoDialog setImagePath(String path){
        this.imagePath = path;
        return this;
    }
    public void show(){
        mDialog = mBuilder.show();
        mDialog.getView(R.id.iv_close).setOnClickListener(this);
        if(imagePath!=null){
            ImageView iv = mDialog.getView(R.id.iv_viewer);
            Glide.with(mContext).load(imagePath).into(iv);
        }
    }
}
