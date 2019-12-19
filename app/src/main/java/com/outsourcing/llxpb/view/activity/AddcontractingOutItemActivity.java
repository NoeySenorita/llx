package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.outsourcing.llxpb.Bean.DeclareBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.adapter.AddContractionOutItemAdapter;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

public class AddcontractingOutItemActivity extends CommonActivity {


    private RecyclerView mAddContaractingRecyclerView;
    public  List<DeclareBean.DataBean> declareBean = new ArrayList<>();
    private AddContractionOutItemAdapter mAddContractionOutItemAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_addcontracting_out_item;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("新建合同外事项");
        mAddContaractingRecyclerView = findViewById(R.id.addContaractingRecyclerView);

    }

    public void add(View view) {
        startActivity(new Intent(this,CreatecontractingOutItemActivity.class));
    }

    public void ok(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(declareBean.size() > 0){
            declareBean.clear();
        }
        declareBean.addAll(UserUtil.servletDeclare);
        if(UserUtil.localityDeclare != null && UserUtil.localityDeclare.size() > 0){
            declareBean.addAll(UserUtil.localityDeclare);
        }
        if(mAddContractionOutItemAdapter != null){
            mAddContractionOutItemAdapter.notifyDataSetChanged();
            mLinearLayoutManager.scrollToPositionWithOffset(declareBean.size()-1, 0);
            mLinearLayoutManager.setStackFromEnd(true);
        }else{
            mLinearLayoutManager = new LinearLayoutManager(this);
            mAddContaractingRecyclerView.setLayoutManager(mLinearLayoutManager);
            mAddContractionOutItemAdapter = new AddContractionOutItemAdapter(this,declareBean);
            mAddContaractingRecyclerView.setAdapter(mAddContractionOutItemAdapter);
        }
    }
}
