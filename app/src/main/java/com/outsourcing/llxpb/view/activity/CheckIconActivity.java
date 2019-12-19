package com.outsourcing.llxpb.view.activity;

import android.view.View;
import android.widget.ImageView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.squareup.picasso.Picasso;

public class CheckIconActivity extends CommonActivity {

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_check_icon;
    }

    @Override
    protected void findViews(View mRootView) {
        String image = getIntent().getStringExtra("image");
        ImageView user_icon = findViewById(R.id.user_icon);
        if(image != null)
        Picasso.with(this).load(image).into(user_icon);
    }
}
