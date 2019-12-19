package com.outsourcing.llxpb.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.PerfectInformationBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.squareup.picasso.Picasso;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AffirmInitProject2Activity extends CommonActivity {
    String  photograph_iamge, frontFileUrl, backFileUrl, phone, wordType, money, bankCare;
    TextView name_1, age_1, nativePlace_1, idcard_1, money_1, workType_1, phone_1, bankCare_1;
    EditText sex_1;
    private RadiusImageView mIv_icon;
    PerfectInformationBean mPerfectInformationBean;
    @Override
    public int getMainLayoutId() {
        return R.layout.activity_affirm_init_project;
    }

    @Override
    protected void findViews(View mRootView) {
        UserUtil.sendPerposesActivitys.add(this);
        getBarTool().setTitle("个人信息");
        photograph_iamge = getIntent().getStringExtra("photograph_iamge");
        phone = getIntent().getStringExtra("phone");
        wordType = getIntent().getStringExtra("wordType");
        money = getIntent().getStringExtra("money");
        bankCare = getIntent().getStringExtra("bankCare");
        mPerfectInformationBean = (PerfectInformationBean) getIntent().getSerializableExtra("data");
        mIv_icon = findViewById(R.id.iv_icon);
        if(!TextUtils.isEmpty(photograph_iamge))
        Picasso.with(this).load(photograph_iamge).into(mIv_icon);
        name_1 = findViewById(R.id.name);
        name_1.setText(mPerfectInformationBean.getData().getName());
        sex_1 = findViewById(R.id.sex);
        age_1 = findViewById(R.id.age);
        nativePlace_1 = findViewById(R.id.nativePlace);
        idcard_1 = findViewById(R.id.idcard);
        money_1 = findViewById(R.id.money);
        workType_1 = findViewById(R.id.workType);
        phone_1 = findViewById(R.id.phone);
        bankCare_1 = findViewById(R.id.bankCare);
        money_1.setText(money);
        workType_1.setText(wordType);
        phone_1.setText(phone);
        bankCare_1.setText(bankCare);
        String msex;
        if (mPerfectInformationBean.getData().getSex() == 2) {
            msex = "女";
        } else if (mPerfectInformationBean.getData().getSex() == 1) {
            msex = "男";
        } else {
            msex = "请点击更改";
        }
        sex_1.setText(msex);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
        String s = simpleDateFormat.format(date);
        int a = Integer.parseInt(s);
        int ag = a - mPerfectInformationBean.getData().getBirthday();
        String ms = (ag + "").substring(0, 2);
        age_1.setText(ms);
        nativePlace_1.setText(mPerfectInformationBean.getData().getNative_place());
        idcard_1.setText(mPerfectInformationBean.getData().getId_card_number() + "");
    }

    int myWorkType;

    public void sendData(View view) {
        sendPersonnerData();;
    }

    void sendPersonnerData( ) {
        String mySex = sex_1.getText().toString().trim();
        if (TextUtils.isEmpty(mySex) || !mySex.equals("男") || !mySex.equals("女")) {
            T.showLong(this, "请输入正确性别:男或女");
        }
        String sex = sex_1.getText().toString().trim();
        int sendSex;
        if(sex.equals("男")){
            sendSex = 1;
        }else if(sex.equals("女")){
            sendSex = 2;
        }else{
            sendSex = 0;
        }
        if (wordType.equals("普工")) {
            myWorkType = 1;
        } else if (wordType.equals("钢筋工")) {
            myWorkType = 2;
        } else if (wordType.equals("混凝土工")) {
            myWorkType = 3;
        } else if (wordType.equals("模板工")) {
            myWorkType = 4;
        } else if (wordType.equals("架子工")) {
            myWorkType = 5;
        } else if (wordType.equals("电焊工")) {
            myWorkType = 6;
        } else if (wordType.equals("砌筑工")) {
            myWorkType = 7;
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("id_card_number", mPerfectInformationBean.getData().getId_card_number());
            obj.put("mobile", phone);
            obj.put("name", mPerfectInformationBean.getData().getName());
            obj.put("sex", sendSex);
            obj.put("birthday", mPerfectInformationBean.getData().getBirthday());
            obj.put("native_place", mPerfectInformationBean.getData().getNative_place());
            obj.put("nationality", mPerfectInformationBean.getData().getNationality());
            obj.put("work_type_id", myWorkType);
            obj.put("protocol_wage", money);
            obj.put("bank_card_number", bankCare);
            obj.put("bank_card_img", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(HttpHost.httpHost + "/subcontractor/roster/completeInfo?id="+mPerfectInformationBean.getData().getId())
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .upString(obj.toString(),okhttp3.MediaType.parse("application/json; charset=utf-8"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String res, okhttp3.Call call, okhttp3.Response response) {
                        if (res.contains("success")) {
                            T.showLong(AffirmInitProject2Activity.this, "完善成功");
                            for (int i = 0; i < UserUtil.sendPerposesActivitys.size() ;i ++){
                                UserUtil.sendPerposesActivitys.get(i).finish();
                            }
                        } else {
                            T.showLong(AffirmInitProject2Activity.this, res);
                        }
                    }
                });
    }
}
