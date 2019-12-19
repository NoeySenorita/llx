package com.outsourcing.llxpb.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.AffirmInitBean;
import com.outsourcing.llxpb.Bean.CheckIdCardBean;
import com.outsourcing.llxpb.Bean.CheckNameIdCareBean;
import com.outsourcing.llxpb.Bean.IdCardMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.squareup.picasso.Picasso;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AffirmInitProjectActivity extends CommonActivity {
    String  photograph_iamge, frontFileUrl, backFileUrl, phone, wordType, money, bankCare;
    CheckNameIdCareBean mIdCardMessageBean;
    TextView name_1, age_1, nativePlace_1, idcard_1, money_1, workType_1, phone_1, bankCare_1;
    EditText sex_1;
    private RadiusImageView mIv_icon;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_affirm_init_project;
    }

    @Override
    protected void findViews(View mRootView) {
        UserUtil.sendPerposesActivitys.add(this);
        getBarTool().setTitle("个人信息");
        photograph_iamge = getIntent().getStringExtra("photograph_iamge");
        frontFileUrl = getIntent().getStringExtra("frontFileUrl");
        backFileUrl = getIntent().getStringExtra("backFileUrl");
        phone = getIntent().getStringExtra("phone");
        wordType = getIntent().getStringExtra("wordType");
        money = getIntent().getStringExtra("money");
        bankCare = getIntent().getStringExtra("bankCare");
        mIv_icon = findViewById(R.id.iv_icon);
        if(!TextUtils.isEmpty(photograph_iamge))
        Picasso.with(this).load(photograph_iamge).into(mIv_icon);
        name_1 = findViewById(R.id.name);
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
        initIdCareMessage();
    }

    private void initIdCareMessage() {
        name_1.setText(UserUtil.sCheckNameIdCareBean.getData().getName());
        int mysex = UserUtil.sCheckNameIdCareBean.getData().getSex();
        String msex;
        if (mysex == 2) {
            msex = "女";
        } else if (mysex == 1) {
            msex = "男";
        } else {
            msex = "请点击更改";
        }
        sex_1.setText(msex);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String s = simpleDateFormat.format(date);
        int a = Integer.parseInt(s);
        int ag = a - UserUtil.sCheckNameIdCareBean.getData().getBirthday();
        String ms = (ag + "").substring(0, 2);
        age_1.setText(ms);
        nativePlace_1.setText(UserUtil.sCheckNameIdCareBean.getData().getNative_place());
        idcard_1.setText(UserUtil.sCheckNameIdCareBean.getData().getId_card_number() + "");
        mIdCardMessageBean = UserUtil.sCheckNameIdCareBean;

        /*OkGo.post(HttpHost.httpHost + "/subcontractor/roster/recognizeIDCard")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params("id_card_front_url", frontFileUrl)
                .execute(new JsonCallBack<IdCardMessageBean>() {
                    @Override
                    public void onSuccess(final IdCardMessageBean idCardMessageBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                if (idCardMessageBean != null && idCardMessageBean.getCode()==0&&idCardMessageBean.getData() !=null) {
                                    name_1.setText(idCardMessageBean.getData().getName());
                                    int mysex = idCardMessageBean.getData().getSex();
                                    String msex;
                                    if (mysex == 2) {
                                        msex = "女";
                                    } else if (mysex == 1) {
                                        msex = "男";
                                    } else {
                                        msex = "请点击更改";
                                    }
                                    sex_1.setText(msex);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
//获取当前时间
                                    Date date = new Date(System.currentTimeMillis());
                                    String s = simpleDateFormat.format(date);
                                    int a = Integer.parseInt(s);
                                    int ag = a - idCardMessageBean.getData().getBirthday();
                                    String ms = (ag + "").substring(0, 2);
                                    age_1.setText(ms);
                                    nativePlace_1.setText(idCardMessageBean.getData().getNative_place());
                                    idcard_1.setText(idCardMessageBean.getData().getId_card_number() + "");
                                    mIdCardMessageBean = idCardMessageBean;
                                } else {
                                    T.showLong(AffirmInitProjectActivity.this, idCardMessageBean.getMessage());
                                }

                            }
                        });
                    }
                });*/
    }

    int myWorkType;

    public void sendData(View view) {
      ///  checkIdCard();
        sendPersonnerData();
    }

   /* void checkIdCard() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/roster/verifyIDCard?" + "id_card_number=" + mIdCardMessageBean.getData().getId_card_number() + "&name=" + mIdCardMessageBean.getData().getName())
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<CheckIdCardBean>() {
                    @Override
                    public void onSuccess(final CheckIdCardBean checkIdCardBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                if (checkIdCardBean != null && checkIdCardBean.getMessage().contains("success")) {
                                    sendPersonnerData(checkIdCardBean);
                                } else {
                                    T.showLong(AffirmInitProjectActivity.this, checkIdCardBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }*/

    void sendPersonnerData() {
        String mySex = sex_1.getText().toString().trim();
        if (TextUtils.isEmpty(mySex)) {
            T.showLong(this, "请输入正确性别:男或女");
            return;
        }
        if(!mySex.equals("男")&&mySex.equals("女")){
            T.showLong(this, "请输入正确性别:男或女");
            return;
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
            obj.put("id_card_number", mIdCardMessageBean.getData().getId_card_number());
            obj.put("mobile", phone);
            obj.put("name", mIdCardMessageBean.getData().getName());
            obj.put("sex", sendSex);
            obj.put("birthday", mIdCardMessageBean.getData().getBirthday());
            obj.put("native_place", mIdCardMessageBean.getData().getNative_place());
            obj.put("nationality", mIdCardMessageBean.getData().getNationality());
            obj.put("address", mIdCardMessageBean.getData().getAddress());
            obj.put("work_type_id", myWorkType);
            obj.put("protocol_wage", money);
            obj.put("bank_card_number", bankCare);
            obj.put("bank_card_img", "");
            obj.put("facial_photo", photograph_iamge);
            obj.put("id_card_front_img", frontFileUrl);
            obj.put("id_card_back_img", backFileUrl);
            obj.put("id_card_verify_flag", UserUtil.sCheckNameIdCareBean.getData().getVerify_flag());
            obj.put("id_card_verify_msg", UserUtil.sCheckNameIdCareBean.getData().getId_card_verify_msg());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(HttpHost.httpHost + "/subcontractor/roster/entry")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .upString(obj.toString(),okhttp3.MediaType.parse("application/json; charset=utf-8"))
                .execute(new JsonCallBack<AffirmInitBean>() {
                    @Override
                    public void onSuccess(AffirmInitBean res, okhttp3.Call call, okhttp3.Response response) {
                        if (res!= null && res.getCode()== 0) {
                            T.showLong(AffirmInitProjectActivity.this, "进场成功");
                            for (int i = 0; i < UserUtil.sendPerposesActivitys.size() ;i ++){
                                UserUtil.sendPerposesActivitys.get(i).finish();
                            }
                            UserUtil.sendPerposesActivitys = null;
                            //AffirmInitProjectActivity.this.findViewById(R.id.send).setVisibility(View.GONE);
                        } else {
                            T.showLong(AffirmInitProjectActivity.this, res.getMessage());
                        }
                    }
                });
    }
}
