package com.outsourcing.llxpb.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.T;

public class SelectMyTypeWorkActivity extends CommonActivity implements View.OnClickListener {
    LinearLayout rl_1,rl_2,rl_3,rl_4,rl_5,rl_6,rl_7;
    ImageView iv_1,iv_2,iv_3,iv_4,iv_5,iv_6,iv_7;
    boolean select1,select2,select3,select4,select5,select6,select7;
    @Override
    public int getMainLayoutId() {
        return R.layout.activity_select_my_type_work;
    }
     int id;
    @Override
    protected void findViews(View mRootView) {
        id = getIntent().getIntExtra("id",0);
        getBarTool().setTitle("选择工种");
        rl_1 = findViewById(R.id.rl_1);
        rl_2 = findViewById(R.id.rl_2);
        rl_3 = findViewById(R.id.rl_3);
        rl_4 = findViewById(R.id.rl_4);
        rl_5 = findViewById(R.id.rl_5);
        rl_6 = findViewById(R.id.rl_6);
        rl_7 = findViewById(R.id.rl_7);
        iv_1 = findViewById(R.id.iv_1);
        iv_2 = findViewById(R.id.iv_2);
        iv_3 = findViewById(R.id.iv_3);
        iv_4 = findViewById(R.id.iv_4);
        iv_5 = findViewById(R.id.iv_5);
        iv_6 = findViewById(R.id.iv_6);
        iv_7 = findViewById(R.id.iv_7);
        rl_1.setOnClickListener(this);
        rl_2.setOnClickListener(this);
        rl_3.setOnClickListener(this);
        rl_4.setOnClickListener(this);
        rl_5.setOnClickListener(this);
        rl_6.setOnClickListener(this);
        rl_7.setOnClickListener(this);

        if (id==1) {
            select1 = false;
            select2 = false;
            select3 = false;
            select4 = true;
            select5 = false;
            select6 = false;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_false);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_true);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_false);
        } else if (id==2) {
            select1 = true;
            select2 = false;
            select3 = false;
            select4 = false;
            select5 = false;
            select6 = false;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_true);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_false);
        } else if (id==3) {
            select1 = false;
            select2 = true;
            select3 = false;
            select4 = false;
            select5 = false;
            select6 = false;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_false);
            iv_2.setBackgroundResource(R.drawable.select_true);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_false);
        } else if (id==4) {
            select1 = false;
            select2 = false;
            select3 = true;
            select4 = false;
            select5 = false;
            select6 = false;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_false);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_true);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_false);
        } else if (id==5) {
            select1 = false;
            select2 = false;
            select3 = false;
            select4 = false;
            select5 = true;
            select6 = false;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_false);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_true);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_false);
        } else if (id==6) {
            select1 = false;
            select2 = false;
            select3 = false;
            select4 = false;
            select5 = false;
            select6 = true;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_false);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_true);
            iv_7.setBackgroundResource(R.drawable.select_false);
        } else if (id==7) {
            select1 = false;
            select2 = false;
            select3 = false;
            select4 = false;
            select5 = false;
            select6 = false;
            select7 = true;
            iv_1.setBackgroundResource(R.drawable.select_false);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.rl_1:
            select1 = true;
            select2 = false;
            select3 = false;
            select4 = false;
            select5 = false;
            select6 = false;
            select7 = false;
            iv_1.setBackgroundResource(R.drawable.select_true);
            iv_2.setBackgroundResource(R.drawable.select_false);
            iv_3.setBackgroundResource(R.drawable.select_false);
            iv_4.setBackgroundResource(R.drawable.select_false);
            iv_5.setBackgroundResource(R.drawable.select_false);
            iv_6.setBackgroundResource(R.drawable.select_false);
            iv_7.setBackgroundResource(R.drawable.select_false);
            break;
            case  R.id.rl_2:
                select1 = false;
                select2 = true;
                select3 = false;
                select4 = false;
                select5 = false;
                select6 = false;
                select7 = false;
                iv_1.setBackgroundResource(R.drawable.select_false);
                iv_2.setBackgroundResource(R.drawable.select_true);
                iv_3.setBackgroundResource(R.drawable.select_false);
                iv_4.setBackgroundResource(R.drawable.select_false);
                iv_5.setBackgroundResource(R.drawable.select_false);
                iv_6.setBackgroundResource(R.drawable.select_false);
                iv_7.setBackgroundResource(R.drawable.select_false);
            break;
            case  R.id.rl_3:
                select1 = false;
                select2 = false;
                select3 = true;
                select4 = false;
                select5 = false;
                select6 = false;
                select7 = false;
                iv_1.setBackgroundResource(R.drawable.select_false);
                iv_2.setBackgroundResource(R.drawable.select_false);
                iv_3.setBackgroundResource(R.drawable.select_true);
                iv_4.setBackgroundResource(R.drawable.select_false);
                iv_5.setBackgroundResource(R.drawable.select_false);
                iv_6.setBackgroundResource(R.drawable.select_false);
                iv_7.setBackgroundResource(R.drawable.select_false);
            break;
            case  R.id.rl_4:
                select1 = false;
                select2 = false;
                select3 = false;
                select4 = true;
                select5 = false;
                select6 = false;
                select7 = false;
                iv_1.setBackgroundResource(R.drawable.select_false);
                iv_2.setBackgroundResource(R.drawable.select_false);
                iv_3.setBackgroundResource(R.drawable.select_false);
                iv_4.setBackgroundResource(R.drawable.select_true);
                iv_5.setBackgroundResource(R.drawable.select_false);
                iv_6.setBackgroundResource(R.drawable.select_false);
                iv_7.setBackgroundResource(R.drawable.select_false);

            break;
            case  R.id.rl_5:
                select1 = false;
                select2 = false;
                select3 = false;
                select4 = false;
                select5 = true;
                select6 = false;
                select7 = false;
                iv_1.setBackgroundResource(R.drawable.select_false);
                iv_2.setBackgroundResource(R.drawable.select_false);
                iv_3.setBackgroundResource(R.drawable.select_false);
                iv_4.setBackgroundResource(R.drawable.select_false);
                iv_5.setBackgroundResource(R.drawable.select_true);
                iv_6.setBackgroundResource(R.drawable.select_false);
                iv_7.setBackgroundResource(R.drawable.select_false);
            break;
            case  R.id.rl_6:
                select1 = false;
                select2 = false;
                select3 = false;
                select4 = false;
                select5 = false;
                select6 = true;
                select7 = false;
                iv_1.setBackgroundResource(R.drawable.select_false);
                iv_2.setBackgroundResource(R.drawable.select_false);
                iv_3.setBackgroundResource(R.drawable.select_false);
                iv_4.setBackgroundResource(R.drawable.select_false);
                iv_5.setBackgroundResource(R.drawable.select_false);
                iv_6.setBackgroundResource(R.drawable.select_true);
                iv_7.setBackgroundResource(R.drawable.select_false);
            break;
            case  R.id.rl_7:
                select1 = false;
                select2 = false;
                select3 = false;
                select4 = false;
                select5 = false;
                select6 = false;
                select7 = true;
                iv_1.setBackgroundResource(R.drawable.select_false);
                iv_2.setBackgroundResource(R.drawable.select_false);
                iv_3.setBackgroundResource(R.drawable.select_false);
                iv_4.setBackgroundResource(R.drawable.select_false);
                iv_5.setBackgroundResource(R.drawable.select_false);
                iv_6.setBackgroundResource(R.drawable.select_false);
                iv_7.setBackgroundResource(R.drawable.select_true);
            break;
        }
    }


    public void confirm(View view) {
        if(!select1&&!select2&&!select3&&!select4&&!select5&&!select6&&!select7){
            T.showLong(this,"必须选择一个");
            return;
        }
        Intent intent = new Intent();
        if(select1){
            intent.putExtra("work","钢筋工");
        }

        if(select2){
            intent.putExtra("work","混凝土工");
        }

        if(select3){
            intent.putExtra("work","模板工");
        }

        if(select4){
            intent.putExtra("work","普工");
        }

        if(select5){
            intent.putExtra("work","架子工");
        }

        if(select6){
            intent.putExtra("work","电焊工");
        }

        if(select7){
            intent.putExtra("work","砌筑工");
        }
        setResult(1,intent);
        finish();
    }
}
