package com.outsourcing.llxpb.view.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.Bean.GetUserMessageBean;
import com.outsourcing.llxpb.Bean.HelpMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectTypeWorkActivity extends CommonActivity implements View.OnClickListener {
    private TextView selete1_stop1, selete2_start, selete2_stop, selete3_start, selete3_stop, selete4_start, selete4_stop,
            selete5_start, selete5_stop, selete6_start, selete6_stop, selete7_start, selete7_stop;
    private Calendar mSelectedDate;
    private Calendar mStartDate;
    private Calendar mEndDate;
    TimePickerView pvTime,st1,tt1,st2,tt2,st3,tt3,st4,tt4,st5,tt5,st6,tt6,st7,tt7;
    TextView selete1_start1;
    long day7 = 604800000;
    private ImageView mSelect_i_1;
    private ImageView mSelect_i_2;
    private ImageView mSelect_i_3;
    private ImageView mSelect_i_4;
    private ImageView mSelect_i_5;
    private ImageView mSelect_i_6;
    private ImageView mSelect_i_7;
    private boolean isiState1,isiState2,isiState3,isiState4,isiState5,isiState6,isiState7;
    private EditText mSelect1_num;
    private EditText mSelect2_num;
    private EditText mSelect3_num;
    private EditText mSelect4_num;
    private EditText mSelect5_num;
    private EditText mSelect6_num;
    private EditText mSelect7_num;
    private EditText mEt_phone;
    private String[] mSarr;
    private String[] mEarr;
    private TextView mEt_submitter;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_select_type_work;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("选择工种");
        selete1_start1 = findViewById(R.id.selete1_start);
        selete1_stop1 = findViewById(R.id.selete1_stop);
        selete2_start = findViewById(R.id.selete2_start);
        selete2_stop = findViewById(R.id.selete2_stop);
        selete3_start = findViewById(R.id.selete3_start);
        selete3_stop = findViewById(R.id.selete3_stop);
        selete4_start = findViewById(R.id.selete4_start);
        selete4_stop = findViewById(R.id.selete4_stop);
        selete5_start = findViewById(R.id.selete5_start);
        selete5_stop = findViewById(R.id.selete5_stop);
        selete6_start = findViewById(R.id.selete6_start);
        selete6_stop = findViewById(R.id.selete6_stop);
        selete7_start = findViewById(R.id.selete7_start);
        selete7_stop = findViewById(R.id.selete7_stop);
        selete1_start1.setOnClickListener(this);
        selete1_stop1.setOnClickListener(this);
        selete2_start.setOnClickListener(this);
        selete2_stop.setOnClickListener(this);
        selete3_start.setOnClickListener(this);
        selete3_stop.setOnClickListener(this);
        selete4_start.setOnClickListener(this);
        selete4_stop.setOnClickListener(this);
        selete5_start.setOnClickListener(this);
        selete5_stop.setOnClickListener(this);
        selete6_start.setOnClickListener(this);
        selete6_stop.setOnClickListener(this);
        selete7_start.setOnClickListener(this);
        selete7_stop.setOnClickListener(this);

        findViewById(R.id.send).setOnClickListener(this);
        mSelect_i_1 = findViewById(R.id.select_i_1);
        mSelect_i_2 = findViewById(R.id.select_i_2);
        mSelect_i_3 = findViewById(R.id.select_i_3);
        mSelect_i_4 = findViewById(R.id.select_i_4);
        mSelect_i_5 = findViewById(R.id.select_i_5);
        mSelect_i_6 = findViewById(R.id.select_i_6);
        mSelect_i_7 = findViewById(R.id.select_i_7);

        mSelect_i_1.setOnClickListener(this);
        mSelect_i_2.setOnClickListener(this);
        mSelect_i_3.setOnClickListener(this);
        mSelect_i_4.setOnClickListener(this);
        mSelect_i_5.setOnClickListener(this);
        mSelect_i_6.setOnClickListener(this);
        mSelect_i_7.setOnClickListener(this);

        mSelect1_num = findViewById(R.id.select1_num);
        mSelect2_num = findViewById(R.id.select2_num);
        mSelect3_num = findViewById(R.id.select3_num);
        mSelect4_num = findViewById(R.id.select4_num);
        mSelect5_num = findViewById(R.id.select5_num);
        mSelect6_num = findViewById(R.id.select6_num);
        mSelect7_num = findViewById(R.id.select7_num);
        mSelectedDate = Calendar.getInstance();
        mStartDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        mEndDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
        mEt_phone = findViewById(R.id.et_phone);
        mEt_submitter = findViewById(R.id.et_submitter);
        //正确设置方式 原因：注意事项有说明

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String sim = dateFormat.format(date);
        Date eDate = new Date(getTimeStame());
        String eim = dateFormat.format(eDate);
        mSarr = sim.split("-");
        mEarr = eim.split("-");

       mStartDate.set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
        mEndDate.set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
        /*mStartDate.set(2019,8,31);
        mEndDate.set(2019,11,7);*/
        initServletData();
    }
    private void initServletData() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/labour/getUserInfo")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<GetUserMessageBean>() {
                    @Override
                    public void onSuccess(GetUserMessageBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if (earlyWBean1 != null && earlyWBean1.getCode()==0 && earlyWBean1.getData() !=null) {
                            mEt_phone.setText(earlyWBean1.getData().getMobile());
                            mEt_submitter.setText(earlyWBean1.getData().getName());

                        } else {
                            T.showLong(SelectTypeWorkActivity.this, earlyWBean1.getMessage());
                        }
                    }
                });
    }
    /**
     * 获取当前的时间戳
     * @return
     */
    public long getTimeStame() {
        //获取当前的毫秒值
        long time = System.currentTimeMillis()+day7;
        //将毫秒值转换为String类型数据
      //  String time_stamp = String.valueOf(time);
        //返回出去
        return time;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selete1_start:
                String selete1_stopTime = selete1_stop1.getText().toString().trim();
                if(!TextUtils.isEmpty(selete1_stopTime)){
                    String[] times = selete1_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));

                }
                selectTime(selete1_start1);
                break;
            case R.id.selete1_stop:
                String selete1_startTime = selete1_start1.getText().toString().trim();
                if(!TextUtils.isEmpty(selete1_startTime)){
                    String[] times = selete1_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));

                }
                selectTime(selete1_stop1);
                break;
            case R.id.selete2_start:
                String selete2_stopTime = selete2_stop.getText().toString().trim();
                if(!TextUtils.isEmpty(selete2_stopTime)){
                    String[] times = selete2_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                }
                selectTime(selete2_start);
                break;
            case R.id.selete2_stop:
                String selete2_startTime = selete2_start.getText().toString().trim();
                if(!TextUtils.isEmpty(selete2_startTime)){
                    String[] times = selete2_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
                }
                selectTime(selete2_stop);
                break;
            case R.id.selete3_start:
                String selete3_stopTime = selete3_stop.getText().toString().trim();
                if(!TextUtils.isEmpty(selete3_stopTime)){
                    String[] times = selete3_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                }
                selectTime(selete3_start);
                break;
            case R.id.selete3_stop:
                String selete3_startTime = selete3_start.getText().toString().trim();
                if(!TextUtils.isEmpty(selete3_startTime)){
                    String[] times = selete3_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
                }
                selectTime(selete3_stop);
                break;
            case R.id.selete4_start:
                String selete4_stopTime = selete4_stop.getText().toString().trim();
                if(!TextUtils.isEmpty(selete4_stopTime)){
                    String[] times = selete4_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                }
                selectTime(selete4_start);
                break;
            case R.id.selete4_stop:
                String selete4_startTime = selete4_start.getText().toString().trim();
                if(!TextUtils.isEmpty(selete4_startTime)){
                    String[] times = selete4_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
                }
                selectTime(selete4_stop);
                break;
            case R.id.selete5_start:
                String selete5_stopTime = selete5_stop.getText().toString().trim();
                if(!TextUtils.isEmpty(selete5_stopTime)){
                    String[] times = selete5_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                }
                selectTime(selete5_start);
                break;
            case R.id.selete5_stop:
                String selete5_startTime = selete5_start.getText().toString().trim();
                if(!TextUtils.isEmpty(selete5_startTime)){
                    String[] times = selete5_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
                }
                selectTime(selete5_stop);
                break;
            case R.id.selete6_start:
                String selete6_stopTime = selete6_stop.getText().toString().trim();
                if(!TextUtils.isEmpty(selete6_stopTime)){
                    String[] times = selete6_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                }
                selectTime(selete6_start);
                break;
            case R.id.selete6_stop:
                String selete6_startTime = selete6_start.getText().toString().trim();
                if(!TextUtils.isEmpty(selete6_startTime)){
                    String[] times = selete6_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
                }
                selectTime(selete6_stop);
                break;
            case R.id.selete7_start:
                String selete7_stopTime = selete7_stop.getText().toString().trim();
                if(!TextUtils.isEmpty(selete7_stopTime)){
                    String[] times = selete7_stopTime.split("/");
                    mStartDate  .set(Integer.parseInt(mSarr[0]),Integer.parseInt(mSarr[1])-1,Integer.parseInt(mSarr[2]));
                    mEndDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                }
                selectTime(selete7_start);
                break;
            case R.id.selete7_stop:
                String selete7_startTime = selete7_start.getText().toString().trim();
                if(!TextUtils.isEmpty(selete7_startTime)){
                    String[] times = selete7_startTime.split("/");
                    mStartDate .set(Integer.parseInt( times[0] ),Integer.parseInt( times[1] )-1,Integer.parseInt( times[2] ));
                    mEndDate .set(Integer.parseInt(mEarr[0]),Integer.parseInt(mEarr[1])-1,Integer.parseInt(mEarr[2]));
                }
                selectTime(selete7_stop);
                break;
            case R.id.select_i_1:
                isiState1 = !isiState1;
                mSelect_i_1.setBackgroundResource(isiState1? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.select_i_2:
                isiState2 = !isiState2;
                mSelect_i_2.setBackgroundResource(isiState2? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.select_i_3:
                isiState3 = !isiState3;
                mSelect_i_3.setBackgroundResource(isiState3? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.select_i_4:
                isiState4 = !isiState4;
                mSelect_i_4.setBackgroundResource(isiState4? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.select_i_5:
                isiState5 = !isiState5;
                mSelect_i_5.setBackgroundResource(isiState5? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.select_i_6:
                isiState6 = !isiState6;
                mSelect_i_6.setBackgroundResource(isiState6? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.select_i_7:
                isiState7 = !isiState7;
                mSelect_i_7.setBackgroundResource(isiState7? R.drawable.select_true:R.drawable.select_false);
                break;
            case R.id.send:
                if(isiState1){
                    if(TextUtils.isEmpty(mSelect1_num.getText().toString().trim())){
                        T.showShort(this,"请输入钢筋工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete1_start1.getText().toString().trim())||TextUtils.isEmpty(selete1_stop1.getText().toString().trim())){
                        T.showShort(this,"请选择钢筋工时间");
                        return;
                    }
                }
                if(isiState2){
                    if(TextUtils.isEmpty(mSelect2_num.getText().toString().trim())){
                        T.showShort(this,"请输入混凝土工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete2_start.getText().toString().trim())||TextUtils.isEmpty(selete2_stop.getText().toString().trim())){
                        T.showShort(this,"请选择混凝土工时间");
                        return;
                    }
                }
                if(isiState3){
                    if(TextUtils.isEmpty(mSelect3_num.getText().toString().trim())){
                        T.showShort(this,"请输入模板工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete3_start.getText().toString().trim())||TextUtils.isEmpty(selete3_stop.getText().toString().trim())){
                        T.showShort(this,"请选择模板工时间");
                        return;
                    }
                }
                if(isiState4){
                    if(TextUtils.isEmpty(mSelect4_num.getText().toString().trim())){
                        T.showShort(this,"请输入普工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete4_start.getText().toString().trim())||TextUtils.isEmpty(selete4_stop.getText().toString().trim())){
                        T.showShort(this,"请选择普工时间");
                        return;
                    }
                }
                if(isiState5){
                    if(TextUtils.isEmpty(mSelect5_num.getText().toString().trim())){
                        T.showShort(this,"请输入架子工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete5_start.getText().toString().trim())||TextUtils.isEmpty(selete5_stop.getText().toString().trim())){
                        T.showShort(this,"请选择架子工时间");
                        return;
                    }
                }
                if(isiState6){
                    if(TextUtils.isEmpty(mSelect6_num.getText().toString().trim())){
                        T.showShort(this,"请输入电焊工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete6_start.getText().toString().trim())||TextUtils.isEmpty(selete6_stop.getText().toString().trim())){
                        T.showShort(this,"请选择电焊工时间");
                        return;
                    }
                }
                if(isiState7){
                    if(TextUtils.isEmpty(mSelect7_num.getText().toString().trim())){
                        T.showShort(this,"请输入砌筑工人数");
                        return;
                    }
                    if(TextUtils.isEmpty(selete7_start.getText().toString().trim())||TextUtils.isEmpty(selete7_stop.getText().toString().trim())){
                        T.showShort(this,"请选择砌筑工时间");
                        return;
                    }
                }
                if(TextUtils.isEmpty(mEt_phone.getText().toString().trim())){
                    T.showShort(this,"请输入砌筑工时间");
                }
                createData();
                break;
        }
    }

    private void createData() {
        JSONObject sendJs = new JSONObject();
        try {
            sendJs.put("mobile",mEt_phone.getText().toString().trim());
            JSONArray idle_list = new JSONArray();
            if(!TextUtils.isEmpty(mSelect1_num.getText().toString().trim())){
                JSONObject j1 = new JSONObject();
                j1.put("work_type_id",2);
                j1.put("idle_num",mSelect1_num.getText().toString().trim());
                j1.put("start_date",selete1_start1.getText().toString().trim().replace("/",""));
                j1.put("end_date",selete1_stop1.getText().toString().trim().replace("/",""));
                idle_list.put(j1);
            }
            if(!TextUtils.isEmpty(mSelect2_num.getText().toString().trim())){
                JSONObject j2 = new JSONObject();
                j2.put("work_type_id",3);
                j2.put("idle_num",mSelect2_num.getText().toString().trim());
                j2.put("start_date",selete2_start.getText().toString().trim().replace("/",""));
                j2.put("end_date",selete2_stop.getText().toString().trim().replace("/",""));
                idle_list.put(j2);
            }
                    /*if (wordType.equals("普工")) {
            myWorkType = 1;
        } else if (wordType.equals("钢筋工")) {
            myWorkType = 2;
        } else if (wordType.equals("混凝土工")) {
            myWorkType = 3;
        } else if (wordType.equals("模板工")) {
            myWorkType = 4;
        } else if (wordType.equals("架子工")) {
            myWorkType = 5;
        } else if (wordType.equals("电焊工")) {
            myWorkType = 6;
        } else if (wordType.equals("砌筑工")) {
            myWorkType = 7;
        }*/
            if(!TextUtils.isEmpty(mSelect3_num.getText().toString().trim())){
                JSONObject j3 = new JSONObject();
                j3.put("work_type_id",4);
                j3.put("idle_num",mSelect3_num.getText().toString().trim());
                j3.put("start_date",selete3_start.getText().toString().trim().replace("/",""));
                j3.put("end_date",selete3_stop.getText().toString().trim().replace("/",""));
                idle_list.put(j3);
            }
            if(!TextUtils.isEmpty(mSelect4_num.getText().toString().trim())){
                JSONObject j4 = new JSONObject();
                j4.put("work_type_id",1);
                j4.put("idle_num",mSelect4_num.getText().toString().trim());
                j4.put("start_date",selete4_start.getText().toString().trim().replace("/",""));
                j4.put("end_date",selete4_stop.getText().toString().trim().replace("/",""));
                idle_list.put(j4);
            }
            if(!TextUtils.isEmpty(mSelect5_num.getText().toString().trim())){
                JSONObject j5 = new JSONObject();
                j5.put("work_type_id",5);
                j5.put("idle_num",mSelect5_num.getText().toString().trim());
                j5.put("start_date",selete5_start.getText().toString().trim().replace("/",""));
                j5.put("end_date",selete5_stop.getText().toString().trim().replace("/",""));
                idle_list.put(j5);
            }
            if(!TextUtils.isEmpty(mSelect6_num.getText().toString().trim())){
                JSONObject j6 = new JSONObject();
                j6.put("work_type_id",6);
                j6.put("idle_num",mSelect6_num.getText().toString().trim());
                j6.put("start_date",selete6_start.getText().toString().trim().replace("/",""));
                j6.put("end_date",selete6_stop.getText().toString().trim().replace("/",""));
                idle_list.put(j6);
            }
            if(!TextUtils.isEmpty(mSelect7_num.getText().toString().trim())){
                JSONObject j7 = new JSONObject();
                j7.put("work_type_id",7);
                j7.put("idle_num",mSelect7_num.getText().toString().trim());
                j7.put("start_date",selete7_start.getText().toString().trim().replace("/",""));
                j7.put("end_date",selete7_stop.getText().toString().trim().replace("/",""));
                idle_list.put(j7);
            }

           sendJs.put("idle_list",idle_list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(HttpHost.httpHost + "/subcontractor/labour/publish")
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
                                SelectTypeWorkActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(SelectTypeWorkActivity.this,"提交成功");
                                        finish();
                                    }
                                });

                            }else {
                                SelectTypeWorkActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(SelectTypeWorkActivity.this,message);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void selectTime(final TextView setTime) {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                setTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(0xffE88110)//确定按钮文字颜色
                .setCancelColor(0xff999999)//取消按钮文字颜色
                .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
             //   .setDate(mSelectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(mStartDate,mEndDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }

    String getTime(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public void clickkkk(View view) {
        selectTime((TextView) view);
    }
}
