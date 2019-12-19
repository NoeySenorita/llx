package com.outsourcing.llxpb.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gamerole.orcameralib.CameraActivity;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.PerfectInformationBean;
import com.outsourcing.llxpb.Bean.PhotographPersonnerUpBean;
import com.outsourcing.llxpb.Bean.RecognizeBankCardBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.EditCheckUtil;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.util.ui.UIUtils;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class CompleteInformation2Activity extends CommonActivity {
    int id;
    int MY_SCAN_REQUEST_CODE = 0;
    private EditText mEt_phone;
    private EditText mEt_typeWork;
    private EditText mEd_money;
    private EditText mEd_bankCare;
    private TextView mTv_money;
    private TextView mTv_wordType;
    private String mCardPath;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_complete_information;
    }

    @Override
    protected void findViews(View mRootView) {
        UserUtil.sendPerposesActivitys.add(this);
        getBarTool().setTitle("完善信息");
        id = getIntent().getIntExtra("id",0);
        mEt_phone = findViewById(R.id.et_phone);
        mEt_typeWork = findViewById(R.id.et_typeWork);
        mEd_money = findViewById(R.id.ed_money);
        mEd_bankCare = findViewById(R.id.ed_BankCare);
        mTv_money = findViewById(R.id.tv_money);
        mTv_wordType = findViewById(R.id.tv_wordType);
        mEd_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    mTv_money.setVisibility(View.INVISIBLE);
                }else {
                    mTv_money.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        initServerData();
        if (id==1) {
            mTv_wordType.setText("普工");
        } else if (id==2) {
            mTv_wordType.setText("钢筋工");
        } else if (id==3) {
            mTv_wordType.setText("混凝土工");
        } else if (id==4) {
            mTv_wordType.setText("模板工");
        } else if (id==5) {
            mTv_wordType.setText("架子工");
        } else if (id==6) {
            mTv_wordType.setText("电焊工");
        } else if (id==7) {
            mTv_wordType.setText("砌筑工");
        }
    }
    PerfectInformationBean photograph_iamge ;
    private void initServerData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/roster/getInfoForComplete?id="+id)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<PerfectInformationBean>() {
                    @Override
                    public void onSuccess(PerfectInformationBean res, okhttp3.Call call, okhttp3.Response response) {
                        if(res!= null && res.getCode()== 0 && res.getData() !=null){
                            photograph_iamge = res;
                            mEt_phone.setText(res.getData().getMobile());
                            String type = "";
                            switch (res.getData().getWork_type_id()){
                                case 1:
                                    type = "普工";
                                    break;
                                case 2:
                                    type = "钢筋工";
                                    break;
                                case 3:
                                    type = "混凝土工";
                                    break;
                                case 4:
                                    type = "模板工";
                                    break;
                                case 5:
                                    type = "架子工";
                                    break;
                                case 6:
                                    type = "电焊工";
                                    break;
                                case 7:
                                    type = "砌筑工";
                                    break;
                            }
                            mEt_typeWork.setText(type);
                            mEd_money.setText(res.getData().getProtocol_wage()+"");
                            mEd_bankCare.setText(res.getData().getBank_card_number());
                            mEt_phone.setText(res.getData().getMobile());
                            mEt_phone.setText(res.getData().getMobile());
                        }else {
                            T.showLong(CompleteInformation2Activity.this,res.getMessage());
                        }
                    }
                });
    }
    int REQUEST_CODE = 5;
    public void selectBankCare(View view) {
        /*Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // default: false不需要     是否需要失效日期等信息
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, false); // default: false               是否隐藏LOGO标记
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, true); // default: false               是否使用card.io LOGO

        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false不需要     邮政编码
        scanIntent.putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, 0xffE88110); // default: Color.GREEN      扫描线的颜色
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false不需要              CVV信息

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);*/

        /*Intent scanIntent = new Intent(this, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
         scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);//是否显示右下角键盘输入，为不显示
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);*/
        mCardPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getFileName() + ".jpg";
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,mCardPath );
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
    String resultDisplayStr;
    private ProgressDialog progressDialog;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {

            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr =  scanResult.cardNumber;
                mEd_bankCare.setText(resultDisplayStr);
                Log.i("aaa", "银行卡号:" + resultDisplayStr);
               // EventBus.getDefault().post(new UpdateCardNumberEvent(resultDisplayStr));
                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );
                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                    Log.i("aaa", "银行卡号有效期:" + resultDisplayStr);

                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            } else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }else if(requestCode == 1){
            if(data != null){
                String work = data.getStringExtra("work");
                if(TextUtils.isEmpty(work)){
                    return;
                }
                mTv_wordType.setText(work);
            }

        }else if(REQUEST_CODE == requestCode){
            if(data !=null){
                /*Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(mCardPath).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, String outfile) {
                        //return the compressed file path and bitmap object
                        File file = new File(mCardPath);
                       // T.showShort();
                        //   deletePhoto(path);
                    }
                });*/
                progressDialog = new ProgressDialog(this);
                progressDialog.show();
                if(TextUtils.isEmpty(mCardPath)){
                    T.showShort(CompleteInformation2Activity.this,"未找到图片");
                    return;
                }
                File file = new File(mCardPath);
                sendImageBreak("bank_card_img",file);
            }

        }
        // else handle other activity results
    }

    private void sendImageBreak(String name,File mFile) {
        OkGo.post(HttpHost.httpHost + "/subcontractor/roster/uploadBankCardImg")
                .headers("Content-Type", "multipart/form-data")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params(name, mFile)
                .execute(new JsonCallBack<PhotographPersonnerUpBean>() {
                    @Override
                    public void onSuccess(final PhotographPersonnerUpBean photographPersonnerUpBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (photographPersonnerUpBean != null && photographPersonnerUpBean.getMessage().contains("success")) {
                                    T.showLong(CompleteInformation2Activity.this, "银行卡上传成功");
                                    idCardInfo(  photographPersonnerUpBean.getData().getWeb_url());
                                } else {
                                    T.showLong(CompleteInformation2Activity.this, photographPersonnerUpBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }

    private void idCardInfo(String web_url) {
        OkGo.post(HttpHost.httpHost + "/subcontractor/roster/recognizeBankCard?bank_card_img="+web_url)
                .headers("Content-Type", "multipart/form-data")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<RecognizeBankCardBean>() {
                    @Override
                    public void onSuccess(final RecognizeBankCardBean recognizeBankCardBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (recognizeBankCardBean != null && recognizeBankCardBean.getCode()== 0 && recognizeBankCardBean.getData()!=null&&recognizeBankCardBean.getData().getBank_card_number() != null) {
                                    mEd_bankCare.setText(recognizeBankCardBean.getData().getBank_card_number());
                                } else {
                                    T.showLong(CompleteInformation2Activity.this, recognizeBankCardBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }


    //选择工种
    public void selectTypeWork(View view) {
        Intent intent  = new Intent(CompleteInformation2Activity.this,SelectMyTypeWorkActivity.class);
        //这个方法来启动下一个Activity,请求码是唯一的值，所以我们这里传入1就可以了
        intent.putExtra("id",id);
        startActivityForResult(intent,1);

    }
    //下一步
    public void intoActivity(View view) {
        if(photograph_iamge == null){
            T.showShort(this,"未找到此人");
            return;
        }
        String phone = mEt_phone.getText().toString().trim();
        String wordType = mTv_wordType.getText().toString().trim();
        String money = mEd_money.getText().toString().trim();
        String bankCare = mEd_bankCare.getText().toString().trim();
        /*if(TextUtils.isEmpty(phone)){
            T.showShort(this,"请输入手机号");
            return;
        }*/
        if(!UIUtils.isPhone(phone)){
            T.showShort(this,"请输入正确手机号");
        }
        /*if(TextUtils.isEmpty(wordType)){
            T.showShort(this,"请选择工种");
            return;
        }
        if(TextUtils.isEmpty(money)){
            T.showShort(this,"请输入工资");
            return;
        }
        if(TextUtils.isEmpty(bankCare)){
            T.showShort(this,"请输入银行卡号");
            return;
        }*/
        if(!TextUtils.isEmpty(bankCare) && EditCheckUtil.checkBankCard(bankCare)){
            T.showShort(this,"请输入正确银行卡号码");
            return;
        }
        Intent intent = new Intent(this,AffirmInitProject2Activity.class);
        intent.putExtra("photograph_iamge",photograph_iamge.getData().getFacial_photo());
        intent.putExtra("phone",phone);
        intent.putExtra("wordType",wordType);
        intent.putExtra("money",money);
        intent.putExtra("bankCare",bankCare);
        intent.putExtra("data",photograph_iamge);
        startActivity(intent);
    }
}
