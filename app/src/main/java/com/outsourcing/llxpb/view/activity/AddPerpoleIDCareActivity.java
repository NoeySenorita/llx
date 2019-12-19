package com.outsourcing.llxpb.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.CheckIDCareBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class AddPerpoleIDCareActivity extends CommonActivity {
    CheckIDCareBean checkIDCareBean;
    private RecyclerView mRl_data;
    private AddPerpoleIDCareAdapter mAdapter;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_add_perpole_idcare;
    }
    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("完善银行卡信息");
        checkIDCareBean = (CheckIDCareBean) getIntent().getSerializableExtra("data");
        mRl_data = findViewById(R.id.rl_data);
        mRl_data.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AddPerpoleIDCareAdapter(this, checkIDCareBean.getData().getNo_bank_card_list());
        mRl_data.setAdapter(mAdapter);
    }

    public void next(View view) {
        boolean state = true;
        for (int i = 0; i <checkIDCareBean.getData().getNo_bank_card_list().size();i++ ){
            if(TextUtils.isEmpty(checkIDCareBean.getData().getNo_bank_card_list().get(i).getIdCare())){
                state = false;
            }
        }
        if(state){
            JSONObject js = new JSONObject();
            JSONArray ja= new JSONArray();

            try {
                for (int i = 0; i < checkIDCareBean.getData().getNo_bank_card_list().size();i++){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id",checkIDCareBean.getData().getNo_bank_card_list().get(i).getId());
                    jsonObject.put("bank_card_number",checkIDCareBean.getData().getNo_bank_card_list().get(i).getIdCare());
                    jsonObject.put("bank_card_img","");
                    ja.put(jsonObject);
                }
                js.put("bank_card_list",ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sendMessage(js.toString());
        }else {
            T.showShort(this,"请输入完整");
        }
    }

    private void sendMessage(String message) {
        /*OkGo.post(HttpHost.httpHost + "/subcontractor/wage/completeBankCard")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .upString(message)
                .execute(new JsonCallBack<CreateMonthlyCDACreateBean>() {
                    @Override
                    public void onSuccess(CreateMonthlyCDACreateBean createMonthlyCDABean, okhttp3.Call call, okhttp3.Response response) {
                        if (createMonthlyCDABean != null && createMonthlyCDABean.getCode()==0) {
                            T.showLong(AddPerpoleIDCareActivity.this, "更改成功");
                            finish();
                        } else {
                            T.showLong(AddPerpoleIDCareActivity.this, createMonthlyCDABean.getMessage());
                        }
                    }
                });*/

        OkGo.<String>post(HttpHost.httpHost + "/subcontractor/wage/completeBankCard")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .upString(message.toString(),okhttp3.MediaType.parse("application/json; charset=utf-8"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String res, okhttp3.Call call, okhttp3.Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            int code = jsonObject.getInt("code");
                            final String message = jsonObject.getString("message");
                            if(code == 0){
                                T.showLong(AddPerpoleIDCareActivity.this, "更改成功");
                                finish();

                            }else {
                                T.showLong(AddPerpoleIDCareActivity.this, message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public class AddPerpoleIDCareAdapter extends RecyclerView.Adapter<AddPerpoleIDCareAdapter.AddPerpoleIDCareHolder> {
        private Context mContext;
        List<CheckIDCareBean.DataBean.NoBankCardListBean> mListBeans;
        public AddPerpoleIDCareAdapter(AddPerpoleIDCareActivity addPerpoleIDCareActivity, List<CheckIDCareBean.DataBean.NoBankCardListBean> no_bank_card_list) {
            mContext = addPerpoleIDCareActivity;
            mListBeans = no_bank_card_list;
        }

        @NonNull
        @Override
        public AddPerpoleIDCareHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AddPerpoleIDCareHolder(View.inflate(mContext, R.layout.item_add_perpole_idcare,null));
        }

        @Override
        public void onBindViewHolder(@NonNull AddPerpoleIDCareHolder addPerpoleIDCareHolder, final int i) {
            TextView name = addPerpoleIDCareHolder.mView.findViewById(R.id.name);
            EditText ed_BankCare = addPerpoleIDCareHolder.mView.findViewById(R.id.ed_BankCare);
            RelativeLayout selectBankCare = addPerpoleIDCareHolder.mView.findViewById(R.id.selectBankCare);
            name.setText(mListBeans.get(i).getName());
            ed_BankCare.setHint("请填入"+mListBeans.get(i).getName()+"银行卡号");
            selectBankCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent scanIntent = new Intent(AddPerpoleIDCareActivity.this, CardIOActivity.class);

                    // customize these values to suit your needs.
                    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // default: false不需要     是否需要失效日期等信息
                    scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, false); // default: false               是否隐藏LOGO标记
                    scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, true); // default: false               是否使用card.io LOGO

                    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false不需要     邮政编码
                    scanIntent.putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, 0xffE88110); // default: Color.GREEN      扫描线的颜色
                    scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false不需要              CVV信息

                    // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                    startActivityForResult(scanIntent, i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mListBeans.size();
        }

        class AddPerpoleIDCareHolder extends RecyclerView.ViewHolder{
            private View mView;
            public AddPerpoleIDCareHolder(@NonNull View itemView) {
                super(itemView);
                mView = itemView;
            }
        }
    }
    String resultDisplayStr;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

            // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
            resultDisplayStr =  scanResult.cardNumber;
            checkIDCareBean.getData().getNo_bank_card_list().get(requestCode).setIdCare(resultDisplayStr);
            mAdapter.notifyDataSetChanged();
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

        // else handle other activity results
    }
}
