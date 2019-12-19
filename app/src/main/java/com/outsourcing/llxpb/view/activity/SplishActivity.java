package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.outsourcing.llxpb.MainActivity;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.manager.IHttpCallBack;
import com.outsourcing.llxpb.manager.UserLoginManager;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.SPUtils;
import com.outsourcing.llxpb.util.UserUtil;

public class SplishActivity extends CommonActivity {


    private long m30Day;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_splish;
    }

    @Override
    protected void findViews(View mRootView) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginData();
            }
        }, 2000);

        getBarTool().hideTitleBar();
    }

    private void LoginData() {
        String userNmae = (String) SPUtils.get("phone", "");
        String passwordae = (String) SPUtils.get("password", "");
        if (TextUtils.isEmpty(userNmae)) {
            SplishActivity.this.startActivity(new Intent(SplishActivity.this, LoginActivity.class));
            finish();
            return;
        }
        /*long s = System.currentTimeMillis();
        long e = get30Day();
        if ( e <= s) {
            finish();//超过三十天退出
            return;
        }*/
        UserLoginManager.loginUser(userNmae, passwordae, new IHttpCallBack() {
            @Override
            public void onSuccess() {
                if (UserUtil.project_id == 0 && UserUtil.project_count == 0) {
                    SplishActivity.this.startActivity(new Intent(SplishActivity.this, NoProjectActivity.class));
                    SPUtils.remove("phone");
                    SPUtils.remove("password");
                    SPUtils.remove("projectId");
                    finish();
                } else {
                    SplishActivity.this.startActivity(new Intent(SplishActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onError(String message) {
                SplishActivity.this.startActivity(new Intent(SplishActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public long get30Day() {
        long day30 = new Long("1576915861000");
        return day30;
    }
}
