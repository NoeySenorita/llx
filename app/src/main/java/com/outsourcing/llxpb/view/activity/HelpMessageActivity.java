package com.outsourcing.llxpb.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.HelpMessageBean;
import com.outsourcing.llxpb.Bean.MessageInfoBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

public class HelpMessageActivity extends CommonActivity {


    private TextView mMessage;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_help_message;
    }

    @Override
    protected void findViews(View mRootView) {
        mMessage = findViewById(R.id.message);
        initServletData();
        getBarTool().setTitle("使用说明");
    }

    private void initServletData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/user/getUserGuide")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<HelpMessageBean>() {
                    @Override
                    public void onSuccess(HelpMessageBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if (earlyWBean1 != null && earlyWBean1.getCode()==0 && earlyWBean1.getData() !=null) {
                            mMessage.setText(earlyWBean1.getData());
                        } else {
                            T.showLong(HelpMessageActivity.this, earlyWBean1.getMessage());
                        }
                    }
                });
    }
}
