package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.outsourcing.llxpb.MainActivity;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.manager.IHttpCallBack;
import com.outsourcing.llxpb.manager.UserLoginManager;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.SPUtils;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.util.ui.UIUtils;

public class LoginActivity extends CommonActivity {

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().hideTitleBar();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
      //  HttpAgent.obtain().post(, HttpCallback<>);
    }
    //登录
    public void loginServer(View view) {
        final String phone = ((EditText)findViewById(R.id.et_phone)).getText().toString().trim();
        final String ps = ((EditText)findViewById(R.id.et_ps)).getText().toString().trim();
        if(UIUtils.isPhone(phone)){
            if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(ps)){
                T.showLong(LoginActivity.this,"请输入手机号和密码");
                return;
            }



            UserLoginManager.loginUser(phone, ps, new IHttpCallBack() {
                @Override
                public void onSuccess() {
                    SPUtils.put("phone",phone);
                    SPUtils.put("password",ps);
                    if(UserUtil.project_id == 0 && UserUtil.project_count == 0){
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, NoProjectActivity.class));
                        SPUtils.remove("phone");
                        SPUtils.remove("password");
                        SPUtils.remove("projectId");
                        finish();
                    }else {
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                }

                @Override
                public void onError(String message) {
                    T.showLong(LoginActivity.this,message);
                }
            });
        }else {
            T.showShort(this,"请输入正确手机号");
        }

    }

    //注册
    public void userRegister(View view) {
        LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void resetPassword(View view) {
        LoginActivity.this.startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }
}
