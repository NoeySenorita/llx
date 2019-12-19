package com.outsourcing.llxpb.view.activity;

import android.app.Dialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.PersonnelListBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.PersonnelReportOutAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.CommomDialogbig;
import com.outsourcing.llxpb.ui.customView.RecyclerLayout;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonnelReportOutActivity extends CommonActivity implements View.OnClickListener {
    private List<PersonnelListBean.DataBean.MigrantWorkerListBean> mPersonnelListBeans;
    private RecyclerLayout mLv_personnerl_report_out;
    private PersonnelReportOutAdapter mPersonnelReportOutAdapter;
    @Override
    public int getMainLayoutId() {
        return R.layout.activity_personnel_report_out;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("民工退场");
        mLv_personnerl_report_out = findViewById(R.id.lv_personnerl_report_out);
        mLv_personnerl_report_out.setLayoutManager(new GridLayoutManager(PersonnelReportOutActivity.this,1));
        mPersonnelListBeans = new ArrayList<>();

        mPersonnelReportOutAdapter = new PersonnelReportOutAdapter(R.layout.item_personner_list_out, mPersonnelListBeans);
        mLv_personnerl_report_out.setAdapter(mPersonnelReportOutAdapter);
        findViewById(R.id.px_all).setOnClickListener(this);
        findViewById(R.id.pr_next).setOnClickListener(this);


        mLv_personnerl_report_out.setOnRefreshListener(new RecyclerLayout.OnRefreshListener() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {//刷新数据

                refreshLayout.finishRefreshing();
                initServieData(1,999,true,refreshLayout,false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {//加载更多
                if(mPersonnelListBean != null && mPersonnelListBean.getData() != null ){
                    initServieData(mPersonnelListBean.getData().getPage()+1,mPersonnelListBean.getData().getPage_size(),false,refreshLayout,false);
                }
            }
        });
        initServieData(1,999,true,null,true);
    }
    int num ;
    String names = "";
    List<PersonnelListBean.DataBean.MigrantWorkerListBean> personnerReportOutList;


    boolean selectState = true;

    @Override
    public void onClick(View v) {
        num = 0;
        if (v.getId() == R.id.px_all) {
            for (int i = 0; i < mPersonnelListBeans.size(); i++) {
                mPersonnelListBeans.get(i).setStateSelect(selectState);
            }
            selectState = !selectState;
            mPersonnelReportOutAdapter = new PersonnelReportOutAdapter(R.layout.item_personner_list_out, mPersonnelListBeans);
            mLv_personnerl_report_out.setAdapter(mPersonnelReportOutAdapter);
        } else if (v.getId() == R.id.pr_next) {
            final JSONArray jsonArray = new JSONArray();
            personnerReportOutList= new ArrayList<>();
            for (int i = 0; i < mPersonnelListBeans.size(); i++) {
               if(mPersonnelListBeans.get(i).isStateSelect()){
                   num ++;
                   if(num ==1){
                       names = mPersonnelListBeans.get(i).getName();
                   }else if(num == 2){
                       names = names+"、"+mPersonnelListBeans.get(i).getName()+"等";
                   }
                   personnerReportOutList.add(mPersonnelListBeans.get(i));
                   try {
                       JSONObject jsonObject = new JSONObject();
                       jsonObject.put("id",mPersonnelListBeans.get(i).getId());
                       jsonArray.put(jsonObject);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
            }

            if(num == 0){
                T.showLong(PersonnelReportOutActivity.this,"请选择退场人员");
                return;
            }
            new CommomDialogbig(this, R.style.dialog, "您确定将"+names+num+"人退场？", new CommomDialogbig.OnCloseListener() {

                @Override
                public void onClickDialog(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("exit_list",jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String j = jsonObject.toString();

                        OkGo.post(HttpHost.httpHost + "/subcontractor/roster/exit")
                                .headers("Content-Type", "application/json")
                                .headers("token", UserUtil.token)
                                .headers("uuid", UserUtil.uuid)
                                .headers("project_id", UserUtil.projectId)
                                .upString(jsonObject.toString(),okhttp3.MediaType.parse("application/json; charset=utf-8"))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(final String s, okhttp3.Call call, okhttp3.Response response) {

                                        AppInstance.gethandler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (s.contains("success")) {
                                                    initServieData(1,999,true,null,true);//刷新
                                                //    mPersonnelReportOutAdapter.notifyDataSetChanged();
                                                    T.showLong(PersonnelReportOutActivity.this, "退场成功");
                                                } else {
                                                    T.showLong(PersonnelReportOutActivity.this, "退场失败");
                                                }

                                            }
                                        });
                                    }
                                });

                        dialog.dismiss();
                    }
                }
            }).setTitle("提示").show();
        }
    }

    PersonnelListBean mPersonnelListBean ;
    private void initServieData(int page, int page_size, final boolean stateFre, final TwinklingRefreshLayout refreshLayout, final boolean state) {
        OkGo.get(HttpHost.httpHost + "/subcontractor/roster/searchInProject?page="+page+"&page_size="+page_size)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<PersonnelListBean>() {
                    @Override
                    public void onSuccess(PersonnelListBean res, okhttp3.Call call, okhttp3.Response response) {
                        mPersonnelListBean = res;
                        if(res.getCode() == 0){
                            if(res.getData().getMigrant_worker_list() != null && res.getData().getMigrant_worker_list().size() > 0){
                                if(stateFre){
                                    mPersonnelListBeans.clear();
                                    mPersonnelListBeans.addAll(res.getData().getMigrant_worker_list());
                                    if(refreshLayout != null)
                                        refreshLayout.finishRefreshing();
                                }else {
                                    mPersonnelListBeans.addAll(res.getData().getMigrant_worker_list());
                                    if(refreshLayout!= null)
                                        refreshLayout.finishLoadmore();
                                }
                                //  mLv_personnel.hideLoading();
                                mLv_personnerl_report_out.notifyDataSetChanged();
                            }else {
                                if(refreshLayout != null){
                                    if(stateFre){
                                        refreshLayout.finishRefreshing();
                                    }else {
                                        refreshLayout.finishLoadmore();
                                    }
                                }
                                if(state){
                                    T.showLong(PersonnelReportOutActivity.this,"暂无人员");
                                }else {
                                    T.showLong(PersonnelReportOutActivity.this,"已经到底啦");
                                }
                            }
                        }else {
                            T.showLong(PersonnelReportOutActivity.this,res.getMessage());
                        }
                    }
                });
    }
}
