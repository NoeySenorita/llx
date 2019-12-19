package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.outsourcing.llxpb.Bean.DeclareBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

public class CreatecontractingOutItemActivity extends CommonActivity {


    @Override
    public int getMainLayoutId() {
        return R.layout.activity_createcontracting_out_item;
    }

    @Override
    protected void findViews(View mRootView) {
        /*if(UserUtil.addDeclareActivitys == null){
            UserUtil.addDeclareActivitys = new ArrayList<>();
        }*/
        getBarTool().setTitle("新建合同外事项");
    }

    public void next(View view) {
        String title = ((EditText)findViewById(R.id.title)).getText().toString().trim();
        String message = ((EditText)findViewById(R.id.message)).getText().toString().trim();
        if(TextUtils.isEmpty(title)){
            T.showShort(this,"请输入标题");
            return;
        }
        if(TextUtils.isEmpty(message)){
            T.showShort(this,"请输入内容");
            return;
        }
        DeclareBean.DataBean dataBean = new DeclareBean.DataBean();
        dataBean.setMatter_subject(title);
        dataBean.setMatter_content(message);
        dataBean.setMatter_num(UserUtil.localityDeclare.size()+1);
        UserUtil.localityDeclare.add(dataBean);
        startActivity(new Intent(this,AddcontractingOutItemActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(UserUtil.addDeclareActivitys != null){
            UserUtil.addDeclareActivitys.clear();
            UserUtil.addDeclareActivitys = null;
        }
    }
}
