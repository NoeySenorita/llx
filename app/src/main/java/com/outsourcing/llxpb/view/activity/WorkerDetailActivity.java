package com.outsourcing.llxpb.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.WorkerDetailResult;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.LookPhotoDialog;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.util.ui.UIUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public class WorkerDetailActivity extends CommonActivity implements View.OnClickListener {

    private TextView mTvWorkDetailNation;
    private TextView mTvWorkDetailType;
    private TextView mTvBankCardNo;
    private TextView mTvSalaryCount;
    private TextView mTvAddress;
    private TextView mTvInTimeCount;
    private TextView mTvInCount;
    private TextView mTvInDate;
    private TextView mTvOutCount;
    private TextView mTvOutDate;
    private TextView name;
    private TextView sex;
    private TextView age;
    private TextView mTvViewPhoto;
    private TextView idcard;
    private TextView mTvViewIdCard;
    private ImageView mIvHead;
    private LookPhotoDialog mLookPhotoDialog;
    private String mFacialPhoto;
    private String mIdCardImage;
    private int migrant_worker_id;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_worker_detail;
    }

    @Override
    protected void findViews(View mRootView) {

        mTvWorkDetailNation = (TextView) findViewById(R.id.tv_work_detail_nation);
        mTvWorkDetailType = (TextView) findViewById(R.id.tv_work_detail_type);
        mTvBankCardNo = (TextView) findViewById(R.id.tv_bank_card_no);
        name = (TextView) findViewById(R.id.name);
        sex = (TextView) findViewById(R.id.sex);
        age = (TextView) findViewById(R.id.age);
        idcard = (TextView) findViewById(R.id.idcard);
        mTvSalaryCount = (TextView) findViewById(R.id.tv_salary_count);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mTvInTimeCount = (TextView) findViewById(R.id.tv_in_time_count);
        mTvInCount = (TextView) findViewById(R.id.tv_in_count);
        mTvInDate = (TextView) findViewById(R.id.tv_in_date);
        mTvOutCount = (TextView) findViewById(R.id.tv_out_count);
        mTvOutDate = (TextView) findViewById(R.id.tv_out_date);
        mTvViewPhoto = (TextView) findViewById(R.id.tv_view_photo);
        mTvViewIdCard = (TextView) findViewById(R.id.tv_view_id_card);
        mIvHead = (ImageView) findViewById(R.id.iv_head);
        mLookPhotoDialog = new LookPhotoDialog(mActivity);
    }

    private void bindingIcon(ImageView imageView, String imageUrl) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(60))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//缩放
                .setLoadingDrawableId(R.mipmap.notify)//加载中默认显示图片
                .setUseMemCache(true)//设置使用缓存
                .setFailureDrawableId(R.mipmap.notify)//加载失败后默认显示图片
                .build();
        x.image().bind(imageView, imageUrl, imageOptions);
    }

    @Override
    protected void initData() {
        super.initData();
        migrant_worker_id = getIntent().getIntExtra("migrant_worker_id",0);

        OkGo.get(HttpHost.httpHost + "/subcontractor/user/getMigrantWorkerDetail?")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params("migrant_worker_id",migrant_worker_id)
                .execute(new JsonCallBack<WorkerDetailResult>() {
                    @Override
                    public void onSuccess(WorkerDetailResult result, Call call, Response response) {
                        if (result.getCode() == 0 && result.getData() !=null) {
                            WorkerDetailResult.DataBean data = result.getData();
                            refreshUI(data);
                            getBarTool().setTitle(data.getName());
                        } else {
                            UIUtils.showToast(result.getMessage());
                        }
                    }
                });
    }

    private void refreshUI(WorkerDetailResult.DataBean data) {
        mFacialPhoto = data.getFacial_photo();
        mIdCardImage = data.getId_card_front_img();
        if(!TextUtils.isEmpty(mFacialPhoto))
      //  Glide.with(mActivity).load(mFacialPhoto).into(mIvHead);
        bindingIcon(mIvHead,mFacialPhoto);
        name.setText(data.getName());
        mTvAddress.setText(data.getAddress());
        long lastTime = data.getLast_entry_record_time();
        long endTime = data.getLast_exit_record_time();
        Date date = new Date(lastTime);
        Date date2 = new Date(endTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        mTvInDate.setText(sd.format(date));

        mTvInTimeCount.setText(data.getIn_project_time()+"天");
        mTvInCount.setText(data.getEntry_record_count()+"次");
        mTvOutCount.setText(data.getExit_record_count()+"次");
        if(data.getExit_record_count() == 0){
            mTvOutDate.setText("未退场");
        }else {
            mTvOutDate.setText(sd.format(date2));
        }
        if(data.getEntry_record_count() == 0){
            mTvInDate.setText("未进场");
        }else {
            mTvInDate.setText(sd.format(date));
        }
        sex.setText(data.getSex()  == 1 ? "男" : "女");
        age.setText(data.getAge()+"");
        mTvWorkDetailNation.setText(data.getNationality()+"");
        mTvSalaryCount.setText(data.getWage_total()+"");
        idcard.setText(data.getId_card_number()+"");
        /*String workName = "";
        switch (data.getWork_type_name()){
            case "1":
                workName = "普工";
                break;
            case "2":
                workName = "钢筋工";
                break;
            case "3":
                workName = "混凝土工";
                break;
            case "4":
                workName = "模板工";
                break;
            case "5":
                workName = "架子工";
                break;
            case "6":
                workName = "电焊工";
                break;
            case "7":
                workName = "砌筑工";
                break;

        }*/
        mTvWorkDetailType.setText(data.getWork_type_name());

    }

    @Override
    protected void initListener() {
        super.initListener();
        mTvViewIdCard.setOnClickListener(this);
        mTvViewPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_view_id_card:
                if (mLookPhotoDialog != null) {
                    mLookPhotoDialog.setImagePath(mIdCardImage).show();
                }
                break;
            case R.id.tv_view_photo:
                if (mLookPhotoDialog != null) {
                    mLookPhotoDialog.setImagePath(mFacialPhoto).show();
                }
                break;
        }
    }
}
