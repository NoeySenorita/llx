package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.RegisterBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.ui.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends CommonActivity {


    private TextView mTv_getCode;
    private EditText mEt_name;
    private EditText mEt_ps;
    private EditText mEt_code;
    private EditText mEt_phone;
    private LinearLayout mLl_editData;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().hideTitleBar();
        mEt_phone = findViewById(R.id.et_phone);
        mEt_code = findViewById(R.id.et_code);
        mEt_ps = findViewById(R.id.et_ps);
        mEt_name = findViewById(R.id.et_name);
        mTv_getCode = findViewById(R.id.tv_getCode);
        mLl_editData = findViewById(R.id.ll_editData);
    }

    //获取验证码
    public void getCode(View view) {
        final String phone = mEt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            T.showLong(RegisterActivity.this, "请输入手机号");
            return;
        }
        if(!UIUtils.isPhone(phone)){
            T.showLong(RegisterActivity.this, "请输入正确");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", phone);
            jsonObject.put("code_type", 2);
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
                            T.showLong(RegisterActivity.this, res);
                        }
                    }
                });

    }

    public void registerServer(View view) {
        final String phone = mEt_phone.getText().toString().trim();
        final String code = mEt_code.getText().toString().trim();
        final String ps = mEt_ps.getText().toString().trim();
        final String name = mEt_name.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            T.showLong(RegisterActivity.this, "请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            T.showLong(RegisterActivity.this, "请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(ps)) {
            T.showLong(RegisterActivity.this, "请输入密码");
            return;
        }
       /* if (TextUtils.isEmpty(name)) {
            T.showLong(RegisterActivity.this, "请输入姓名");
            return;
        }*/

        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("uuid", "");
            jsonObject1.put("password",ps);
            jsonObject1.put("device_type",1000);
            jsonObject1.put("verification_code", code);
            jsonObject1.put("mobile", phone);
            jsonObject1.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.post(HttpHost.httpHost+"/subcontractor/user/register")
                .upJson(jsonObject1.toString())
                .execute(new JsonCallBack<RegisterBean>() {
                    @Override
                    public void onSuccess(RegisterBean res, okhttp3.Call call, okhttp3.Response response) {
                        if(res!= null &&res.getData() != null&& res.getMessage().contains("success")){
                            mLl_editData.setVisibility(View.GONE);
                        }else {
                            T.showLong(RegisterActivity.this, res.getMessage());
                        }
                    }
                });
    }

    //用户协议
    public void goAgreement(View view) {

    }

    public void goLogin(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }
}
