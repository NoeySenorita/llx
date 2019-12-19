package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.util.ui.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResetPasswordActivity extends CommonActivity {
    private EditText mEt_code;
    private EditText mEt_phone;
    private TextView mTv_getCode;
    @Override
    public int getMainLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("重置密码");
        mEt_phone = findViewById(R.id.et_phone);
        mEt_code = findViewById(R.id.et_code);
        mTv_getCode = findViewById(R.id.tv_getCode);
    }

    public void getCode(View view) {
        final String phone = mEt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            T.showLong(ResetPasswordActivity.this, "请输入手机号");
            return;
        }
        if (UIUtils.isPhone(phone)) {
            T.showLong(ResetPasswordActivity.this, "请输入正确手机号");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", phone);
            jsonObject.put("code_type", 3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.post(HttpHost.httpHost+"/subcontractor/user/sendSmsVerificationCode")
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String res, okhttp3.Call call, okhttp3.Response response) {
                        if (res != null && res.contains("success")) {
                            /** 倒计时60秒，一次1秒 */
                            mTv_getCode.setTextColor(0xff999999);
                            CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    // TODO Auto-generated method stub
                                    mTv_getCode.setText( millisUntilFinished / 1000 + "秒后重新获取");

                                }

                                @Override
                                public void onFinish() {
                                    mTv_getCode.setClickable(true);
                                    mTv_getCode.setText("获取验证码");
                                    mTv_getCode.setTextColor(0xffE88110);
                                }
                            }.start();
                        } else {
                            mTv_getCode.setClickable(true);
                            T.showLong(ResetPasswordActivity.this, res);
                        }
                    }
                });
    }

    public void onNext1(View view) {
        final String phone = mEt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            T.showLong(ResetPasswordActivity.this, "请输入手机号");
            return;
        }
        final String code = mEt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            T.showLong(ResetPasswordActivity.this, "请输入验证码");
            return;
        }
        UserUtil.sActivities = new ArrayList<>();
        UserUtil.sActivities.add(this);
        Intent intent = new Intent(this,ResetPassword2Activity.class);
        intent.putExtra("phone",phone);
        intent.putExtra("code",code);
        startActivity(intent);
    }
}
