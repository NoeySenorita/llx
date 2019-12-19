package com.outsourcing.llxpb.view.activity;

import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.MessageInfoBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

public class MessageInfoActivity extends CommonActivity {


    private int mId;
    private TextView mMessage;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_message_info;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("通知");
        mId = getIntent().getIntExtra("id",0);
        mMessage = findViewById(R.id.message);
        initServletData();
    }

    private void initServletData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/notice/readNoticeDetail?id="+mId)
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MessageInfoBean>() {
                    @Override
                    public void onSuccess(MessageInfoBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if (earlyWBean1 != null && earlyWBean1.getCode()==0 ) {
                            if(earlyWBean1.getData() !=null){
                                mMessage.setText(earlyWBean1.getData().getContent());
                            }else {
                                T.showLong(MessageInfoActivity.this, "暂无数据");
                            }
                        } else {
                            T.showLong(MessageInfoActivity.this, earlyWBean1.getMessage());
                        }
                    }
                });
    }
}
