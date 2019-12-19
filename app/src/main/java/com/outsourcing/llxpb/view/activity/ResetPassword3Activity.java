package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.view.View;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.UserUtil;

public class ResetPassword3Activity extends CommonActivity {


    @Override
    public int getMainLayoutId() {
        return R.layout.activity_reset_password3;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("重置密码");
    }

    public void goLogin(View view) {
        if( UserUtil.sActivities.size() > 0){
            for (int i  = 0 ; i < UserUtil.sActivities.size();i++){
                UserUtil.sActivities.get(i).finish();
            }
            UserUtil.sActivities = null;
        }
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
