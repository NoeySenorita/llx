package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class ResetPassword2Activity extends CommonActivity {


    private String mCode;
    private String mPhone;
    private EditText mEt_newPs1;
    private EditText mEt_newPs2;
    private TextView mPs_errorMessage;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_reset_password2;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("重置密码");
        mCode = getIntent().getStringExtra("code");
        mPhone = getIntent().getStringExtra("phone");
        mEt_newPs1 = findViewById(R.id.et_newPs1);
        mEt_newPs2 = findViewById(R.id.et_newPs2);
        mPs_errorMessage = findViewById(R.id.ps_errorMessage);
    }

    public void onNext2(View view) {
        final String ps1 = mEt_newPs1.getText().toString().trim();
        final String sp2 = mEt_newPs2.getText().toString().trim();
        if(TextUtils.isEmpty(ps1) || TextUtils.isEmpty(sp2)){
            T.showLong(this,"请输入密码");
            return;
        }
        if(!ps1.equals(sp2)){
            mPs_errorMessage.setVisibility(View.VISIBLE);
            return;
        }else {
            mPs_errorMessage.setVisibility(View.INVISIBLE);
        }


        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("mobile",mPhone);
            jsonObject1.put("verification_code",mCode);
            jsonObject1.put("password",ps1);
            jsonObject1.put("confirm_password",sp2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.post(HttpHost.httpHost+"/subcontractor/user/changePassword")
                .upJson(jsonObject1.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String res, okhttp3.Call call, okhttp3.Response response) {
                        if(res!= null && res.contains("success")){
                            UserUtil.sActivities.add(ResetPassword2Activity.this);
                            ResetPassword2Activity.this.startActivity(new Intent(ResetPassword2Activity.this,ResetPassword3Activity.class));
                        }else {
                            T.showLong(ResetPassword2Activity.this,res);
                        }
                    }
                });



        /*BaseRequest iRequest = new BaseRequest() {
            @Override
            public void addParam(RequestBuilder builder) {
                builder.addParam("mobile", mPhone);
                builder.addParam("verification_code", mCode);
                builder.addParam("password", ps1);
                builder.addParam("confirm_password", sp2);
            }

            @Override
            public String provideURI() {
                return "/subcontractor/user/changePassword";
            }
        };
        HttpAgent.obtain().post(iRequest, new HttpCallback<String>() {
            @Override
            public void onSuccess(String res) {
                if(res!= null && res.contains("success")){
                    UserUtil.sActivities.add(ResetPassword2Activity.this);
                    ResetPassword2Activity.this.startActivity(new Intent(ResetPassword2Activity.this,ResetPassword3Activity.class));
                }else {
                    T.showLong(ResetPassword2Activity.this,"修改失败");
                }

            }

            @Override
            public void onError(String e) {
                T.showLong(ResetPassword2Activity.this,e);
            }
        });*/
    }
}
