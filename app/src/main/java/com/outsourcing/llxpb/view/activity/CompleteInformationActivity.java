package com.outsourcing.llxpb.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class CompleteInformationActivity extends CommonActivity {
    String photograph_iamge,frontFileUrl,backFileUrl;
    int MY_SCAN_REQUEST_CODE = 0;
    private EditText mEt_phone;
    private EditText mEt_typeWork;
    private EditText mEd_money;
    private EditText mEd_bankCare;
    private TextView mTv_money;
    private TextView mTv_wordType;
    @Override
    public int getMainLayoutId() {
        return R.layout.activity_complete_information;
    }

    @Override
    protected void findViews(View mRootView) {
        UserUtil.sendPerposesActivitys.add(this);
        getBarTool().setTitle("完善信息");
        photograph_iamge = getIntent().getStringExtra("photograph_iamge");
        frontFileUrl = getIntent().getStringExtra("frontFileUrl");
        backFileUrl = getIntent().getStringExtra("backFileUrl");
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
    }
    private String mCardPath;
    private String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
    int REQUEST_CODE = 5;
    public void selectBankCare(View view) {
        /*Intent scanIntent = new Intent(this, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
// scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);//是否显示右下角键盘输入，为不显示
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);*/
        mCardPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getFileName() + ".jpg";
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,mCardPath );
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
        startActivityForResult(intent, REQUEST_CODE);
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
                    T.showShort(CompleteInformationActivity.this,"未找到图片");
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
                                    T.showLong(CompleteInformationActivity.this, "银行卡上传成功");
                                  idCardInfo(  photographPersonnerUpBean.getData().getWeb_url());
                                } else {
                                    T.showLong(CompleteInformationActivity.this, photographPersonnerUpBean.getMessage());
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
                                    T.showLong(CompleteInformationActivity.this, recognizeBankCardBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }


    //选择工种
    public void selectTypeWork(View view) {
        Intent intent  = new Intent(CompleteInformationActivity.this,SelectMyTypeWorkActivity.class);
        //这个方法来启动下一个Activity,请求码是唯一的值，所以我们这里传入1就可以了
        startActivityForResult(intent,1);

    }
    //下一步
    public void intoActivity(View view) {
        String phone = mEt_phone.getText().toString().trim();
        String wordType = mTv_wordType.getText().toString().trim();
        String money = mEd_money.getText().toString().trim();
        String bankCare = mEd_bankCare.getText().toString().trim();
        if(TextUtils.isEmpty(phone)){
            T.showShort(this,"请输入手机号");
            return;
        }
        if(!UIUtils.isPhone(phone)){
            T.showShort(this,"请输入正确手机号");
        }
        if(TextUtils.isEmpty(wordType)){
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
        }
        if(TextUtils.isEmpty(bankCare)||!EditCheckUtil.checkBankCard(bankCare)){
            T.showShort(this,"请输入正确银行卡号码");
            return;
        }
        Intent intent = new Intent(this,AffirmInitProjectActivity.class);
        intent.putExtra("photograph_iamge",photograph_iamge);
        intent.putExtra("frontFileUrl",frontFileUrl);
        intent.putExtra("backFileUrl",backFileUrl);
        intent.putExtra("phone",phone);
        intent.putExtra("wordType",wordType);
        intent.putExtra("money",money);
        intent.putExtra("bankCare",bankCare);
        startActivity(intent);
    }
}
