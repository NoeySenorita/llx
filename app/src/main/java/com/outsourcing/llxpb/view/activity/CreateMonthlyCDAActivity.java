package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.CheckIDCareBean;
import com.outsourcing.llxpb.Bean.CreateMonthlyCDABean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.CreateMonthlyCDAAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateMonthlyCDAActivity extends CommonActivity {


    private RecyclerView mRl_list;
    private CreateMonthlyCDAAdapter mAdapter;
    private TextView mNext,LL_SHOW;
    private Intent mIntent;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_create_monthly_cda;
    }

    @Override
    protected void findViews(View mRootView) {
        mRl_list = findViewById(R.id.rl_list);
        LL_SHOW = findViewById(R.id.LL_SHOW);
        mNext = findViewById(R.id.next);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRl_list.setLayoutManager(layoutManager);
        // .setLayoutManager(new LinearLayoutManager(this));
        iniServlet();
        getBarTool().setTitle("新建民工工资申报");
        mAdapter = new CreateMonthlyCDAAdapter(CreateMonthlyCDAActivity.this, mListBeans, mNext);
        mRl_list.setAdapter(mAdapter);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListBeans!=null && mListBeans.size() != 0){
                    boolean setState = true;
                    String message = "";
                    for (int j = 0; j < mListBeans.size(); j++) {
                        if (!mListBeans.get(j).isState()) {
                            setState = false;
                        } else {
                            if (j == 0) {
                                message = mListBeans.get(j).getId() + "";
                            } else {
                                message = message + "," + mListBeans.get(j).getId() + "";
                            }
                        }
                    }
                    if (!setState) {
                        T.showShort(CreateMonthlyCDAActivity.this, "请把分配填写完整");
                        return;
                    }
                    checkIDCare(message);
                }else {
                    T.showShort(CreateMonthlyCDAActivity.this, "当前暂无工资可申报项");
                }

            }
        });
    }

    /**
     * 检查信息完整
     */
    private void checkIDCare(String message) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/wage/getNoBankCardList?migrant_worker_ids=" + message)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<CheckIDCareBean>() {
                    @Override
                    public void onSuccess(CheckIDCareBean checkIDCareBean, okhttp3.Call call, okhttp3.Response response) {
                        if (checkIDCareBean != null && checkIDCareBean.getCode() == 0) {
                            if (checkIDCareBean.getData() !=null&&checkIDCareBean.getData().getNo_bank_card_count() > 0) {
                                mIntent = new Intent(CreateMonthlyCDAActivity.this, AddPerpoleIDCareActivity.class);
                                mIntent.putExtra("data", checkIDCareBean);
                                CreateMonthlyCDAActivity.this.startActivity(mIntent);
                            } else {
                                if(mCreateMonthlyCDABean == null || mCreateMonthlyCDABean.getData() ==null){
                                    T.showShort(CreateMonthlyCDAActivity.this,"暂无申报");
                                    return;
                                }
                                JSONObject js = new JSONObject();
                                try {
                                    js.put("apply_month", mCreateMonthlyCDABean.getData().getApply_month());
                                    JSONArray jsonArray = new JSONArray();
                                    for (int i = 0; i < mListBeans.size(); i++) {
                                        JSONObject jo = new JSONObject();
                                        jo.put("id", mListBeans.get(i).getId());
                                        jo.put("apply_wage", mListBeans.get(i).getSuggest_apply_wage());
                                        jo.put("work_day", mListBeans.get(i).getWork_day());
                                        jsonArray.put(jo);
                                    }
                                    js.put("wage_apply_list", jsonArray);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                sendMessage(js.toString());
                            }
                        } else {
                            T.showLong(CreateMonthlyCDAActivity.this, checkIDCareBean.getMessage());
                        }
                    }
                });
    }

    CreateMonthlyCDABean mCreateMonthlyCDABean;

    private void sendMessage(String message) {
        OkGo.<String>post(HttpHost.httpHost + "/subcontractor/wage/apply")
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
                                T.showLong(CreateMonthlyCDAActivity.this, "更改成功");
                                CreateMonthlyCDAActivity.this.finish();

                            }else {
                                T.showLong(CreateMonthlyCDAActivity.this, message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    List<CreateMonthlyCDABean.DataBean.MigrantWorkerListBean> mListBeans = new ArrayList<>();

    private void iniServlet() {
        /*OkGo.get(HttpHost.httpHost + "/subcontractor/wage/getMigrantWorkerInfoList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String  createMonthlyCDABean, okhttp3.Call call, okhttp3.Response response) {
                        Log.e("onSuccess: ",createMonthlyCDABean );
                    }
                });*/
        OkGo.get(HttpHost.httpHost + "/subcontractor/wage/getMigrantWorkerInfoList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<CreateMonthlyCDABean>() {
                    @Override
                    public void onSuccess(CreateMonthlyCDABean createMonthlyCDABean, okhttp3.Call call, okhttp3.Response response) {
                        if (createMonthlyCDABean != null && createMonthlyCDABean.getCode() == 0 && createMonthlyCDABean.getData() != null&& createMonthlyCDABean.getData().getMigrant_worker_list() !=null && createMonthlyCDABean.getData().getMigrant_worker_list().size() > 0) {
                            mRl_list.setVisibility(View.VISIBLE);
                            LL_SHOW.setVisibility(View.GONE);
                            mCreateMonthlyCDABean = createMonthlyCDABean;
                            mListBeans.clear();
                            mListBeans.addAll(createMonthlyCDABean.getData().getMigrant_worker_list());
                            for (int j = 0;j < mListBeans.size();j++){
                                if(TextUtils.isEmpty(mListBeans.get(j).getSuggest_apply_wage()+"")){
                                    mListBeans.get(j).setState(false);
                                }else {
                                    mListBeans.get(j).setState(true);
                                }
                            }
                            boolean setState = true;
                            for (int j = 0;j < mListBeans.size();j++){
                                if( !mListBeans.get(j).isState()){
                                    setState = false;
                                }
                            }
                            mNext.setBackgroundResource(setState?R.drawable.login_button:R.drawable.login_button_offclick);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mRl_list.setVisibility(View.GONE);
                            LL_SHOW.setVisibility(View.VISIBLE);
                            mNext.setBackgroundResource(R.drawable.login_button_offclick);
                            T.showLong(CreateMonthlyCDAActivity.this, createMonthlyCDABean.getMessage());
                        }
                    }
                });
    }


}
