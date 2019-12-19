package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.CreateDeclareBean;
import com.outsourcing.llxpb.Bean.DeclareBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.CreateDeclareAdapter;
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

public class CreateDeclareActivity extends CommonActivity {
    private RecyclerView mRvlList;
    CreateDeclareAdapter mAdapter;
    List<CreateDeclareBean.DataBean.ContractListBean> mList = new ArrayList<>();
    private TextView mDeclareNum;
    private int mMonth;
    private Intent mIntent;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_create_declare;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("");
        mMonth = getIntent().getIntExtra("month",-1);
        mRvlList = findViewById(R.id.rvl_list);
     //   mRvlList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvlList.setLayoutManager(new LinearLayoutManager(this));
      //  mAdapter = new CreateDeclareAdapter(R.layout.item_create_declare, mList);
        mAdapter = new CreateDeclareAdapter(this, mList);
        mDeclareNum = findViewById(R.id.declareNum);
        mRvlList.setAdapter(mAdapter);
        getData();
    }

    DeclareBean mDeclareBean;
    private void getData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/output/getContractInfoList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<CreateDeclareBean>() {
                    @Override
                    public void onSuccess(CreateDeclareBean createDeclareBean, okhttp3.Call call, okhttp3.Response response) {
                        if(createDeclareBean.getCode() == 0 ){
                            mList.clear();
                            if(createDeclareBean.getData() !=null){
                                mList.addAll(createDeclareBean.getData().getContract_list());
                            }
                            mAdapter.notifyDataSetChanged();
                        }else {
                            T.showShort(CreateDeclareActivity.this,createDeclareBean.getMessage());
                        }
                    }
                });
        OkGo.get(HttpHost.httpHost + "/subcontractor/output/getMatterList?"+"month="+mMonth)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<DeclareBean>() {
                    @Override
                    public void onSuccess(DeclareBean declareBean, okhttp3.Call call, okhttp3.Response response) {
                        if(declareBean.getCode() == 0 ){
                                mDeclareBean = declareBean;
                                mDeclareNum.setText(declareBean.getData()!= null ?"合同外事项("+declareBean.getData().size()+"条)":0+"");

                        }else {
                            T.showShort(CreateDeclareActivity.this,declareBean.getMessage());
                        }
                    }
                });

    }

    public void initDeclart(View view) {
        if(mDeclareBean != null && mDeclareBean.getData() != null && mDeclareBean.getData().size() > 0){
            mIntent = new Intent(this,AddcontractingOutItemActivity.class);
            if(UserUtil.servletDeclare.size() > 0){
                UserUtil.servletDeclare.clear();
            }
            UserUtil.servletDeclare.addAll(mDeclareBean.getData());
        }else {
            UserUtil.localityDeclare.clear();//清除本地数据
            mIntent = new Intent(this,CreatecontractingOutItemActivity.class);
        }
        startActivity(mIntent);
    }

    public void uoData(View view) {
        JSONArray jsonArray1 = new JSONArray();
        for (int i =0;i < mList.size();i++){
            JSONArray jsonArray2 = new JSONArray();

            for(int j = 0 ; j < mList.get(i).getItem_list().size();j++){
                JSONObject jsonObject2 = new JSONObject();
                try {
                    jsonObject2.put("contract_id",mList.get(i).getId());
                    jsonObject2.put("item_id",mList.get(i).getItem_list().get(j).getItem_id());
                    jsonObject2.put("apply_item_num",mList.get(i).getItem_list().get(j).getNum());
                    int num = mList.get(i).getItem_list().get(j).getNum();
                    jsonObject2.put("construction_position",mList.get(i).getItem_list().get(j).getSuggest_construction_position());
                    String s  = mList.get(i).getItem_list().get(j).getSuggest_construction_position();
                    jsonArray2.put(jsonObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            jsonArray1.put(jsonArray2);
        }
        String s = jsonArray1.toString();
        JSONArray matter_apply = new JSONArray();
        for (int i = 0 ;i < UserUtil.localityDeclare.size();i++){
            JSONObject js = new JSONObject();
            try {
                js.put("matter_subject",UserUtil.localityDeclare.get(i).getMatter_subject());
                js.put("matter_content",UserUtil.localityDeclare.get(i).getMatter_content());
                js.put("matter_num",UserUtil.localityDeclare.get(i).getMatter_num());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            matter_apply.put(js);
        }
        String m = matter_apply.toString();
        JSONObject sendJs =  new JSONObject();
        try {
            sendJs.put("contract_apply",jsonArray1 );
            sendJs.put("matter_apply", matter_apply);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkGo.<String>post(HttpHost.httpHost + "/subcontractor/output/apply")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .upString(sendJs.toString(),okhttp3.MediaType.parse("application/json; charset=utf-8"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String res, okhttp3.Call call, okhttp3.Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            int code = jsonObject.getInt("code");
                            final String message = jsonObject.getString("message");
                            if(code == 0){
                                CreateDeclareActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(CreateDeclareActivity.this,"提交成功");
                                        CreateDeclareActivity.this.finish();
                                    }
                                });

                            }else {
                                CreateDeclareActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(CreateDeclareActivity.this,message);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



       /* OkGo.post(HttpHost.httpHost + "/subcontractor/output/apply")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .upJson(sd)
                //.upString(sd)
                .execute(new JsonCallBack<CreateDeclareSendBean>() {
                    @Override
                    public void onSuccess(final CreateDeclareSendBean updataDeclareBean, okhttp3.Call call, okhttp3.Response response) {
                        if(updataDeclareBean.getCode() == 1){
                            T.showShort(CreateDeclareActivity.this,"提交成功");
                            finish();
                        }else {
                            T.showShort(CreateDeclareActivity.this,updataDeclareBean.getMessage());
                        }
                    }
                });*/
    }
}
