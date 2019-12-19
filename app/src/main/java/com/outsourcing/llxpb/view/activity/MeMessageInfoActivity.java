package com.outsourcing.llxpb.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;

public class MeMessageInfoActivity extends CommonActivity {


    @Override
    public int getMainLayoutId() {
        return R.layout.activity_me_message_info;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("使用说明");
        String data = getIntent().getStringExtra("data");
        ((TextView)findViewById(R.id.message)).setText(data);
    }
}
