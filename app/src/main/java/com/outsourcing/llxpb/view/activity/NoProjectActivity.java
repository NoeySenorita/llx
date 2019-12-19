package com.outsourcing.llxpb.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.CommomDialogbig;
import com.outsourcing.llxpb.util.SPUtils;
import com.outsourcing.llxpb.util.UserUtil;

public class NoProjectActivity extends CommonActivity {


    @Override
    public int getMainLayoutId() {
        return R.layout.activity_no_project;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().hideTitleBar();
    }

    public void outLogin(View view) {
        new CommomDialogbig(this, R.style.dialog, "是否退出登录?", new CommomDialogbig.OnCloseListener() {

            @Override
            public void onClickDialog(Dialog dialog, boolean confirm) {
                if(confirm){
                    SPUtils.remove("phone");
                    SPUtils.remove("password");
                    SPUtils.remove("projectId");
                    NoProjectActivity.this.startActivity(new Intent(NoProjectActivity.this,LoginActivity.class));
                    finish();
                    if(UserUtil.mainActivity != null){
                        UserUtil.mainActivity.finish();
                        UserUtil.mainActivity = null;
                    }

                }
                dialog.dismiss();

            }
        }).setTitle("提示").setPositiveButton("确定").show();
    }
}
