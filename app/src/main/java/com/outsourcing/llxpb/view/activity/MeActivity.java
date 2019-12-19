package com.outsourcing.llxpb.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.MeMessageBean;
import com.outsourcing.llxpb.Bean.MePhoneBean;
import com.outsourcing.llxpb.Bean.PhotographPersonnerUpBean;
import com.outsourcing.llxpb.Bean.UpDataUserIcon;
import com.outsourcing.llxpb.Bean.UserInfoMessageBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.base.TakePhotoActivity;
import com.outsourcing.llxpb.ui.customView.CommomDialogbig;
import com.outsourcing.llxpb.util.DataCleanManager;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.SPUtils;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.squareup.picasso.Picasso;

import java.io.File;

import static com.outsourcing.llxpb.util.ui.UIUtils.showToast;

public class MeActivity extends TakePhotoActivity implements View.OnClickListener {

    private TextView mName;
    private ImageView mIcon;
    private TextView mSex;
    private TextView mPosition;
    private TextView mCompany;
    private TextView mTv_qchc;
    private TextView mTv_dqbb;
    private Intent mIntent;
    private TextView mTv_lxht;
    private Intent mIntent1;
    private Intent mIntent2;

    @Override
    public int getContentId() {
        return  R.layout.activity_me;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("用户中心");
        mIcon = findViewById(R.id.icon);
        mIcon.setOnClickListener(this);
        mName = findViewById(R.id.name);
        mSex = findViewById(R.id.sex);
        mPosition = findViewById(R.id.position);
        mCompany = findViewById(R.id.company);
        mTv_qchc = findViewById(R.id.tv_qchc);
        mTv_dqbb = findViewById(R.id.tv_dqbb);
        mTv_lxht = findViewById(R.id.tv_lxht);
        try {
            mTv_dqbb.setText(getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        findViewById(R.id.ll_ht).setOnClickListener(this);
        findViewById(R.id.ll_hmc).setOnClickListener(this);
        findViewById(R.id.ll_sysm).setOnClickListener(this);
        findViewById(R.id.ll_lxht).setOnClickListener(this);
        findViewById(R.id.ll_xgmm).setOnClickListener(this);
        findViewById(R.id.ll_dqbb).setOnClickListener(this);
        findViewById(R.id.ll_qchc).setOnClickListener(this);
        findViewById(R.id.ll_tcdl).setOnClickListener(this);
        getMessage();
        try {
            String data = DataCleanManager.getTotalCacheSize(MeActivity.this);
            mTv_qchc.setText(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    UserInfoMessageBean re;

    private void getMessage() {
        OkGo.get(HttpHost.httpHost + "/subcontractor/user/getBaseInfo")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<UserInfoMessageBean>() {
                    @Override
                    public void onSuccess(UserInfoMessageBean res, okhttp3.Call call, okhttp3.Response response) {
                        if (res != null && res.getCode() == 0 &&res.getData() !=null) {
                            re = res;
                            mName.setText(res.getData().getName());
                            mSex.setText(res.getData().getSex() == 1 ? "男" : "女");
                            mPosition.setText(res.getData().getRole_name());
                            mCompany.setText(res.getData().getSubcontractor_short_name());
                            if(!TextUtils.isEmpty(res.getData().getProfile_picture())){
                                Picasso.with(MeActivity.this).load(res.getData().getProfile_picture()).into(mIcon);
                            }

                        } else {
                            T.showLong(MeActivity.this, res.getMessage());
                        }
                    }
                });
       /* OkGo.get(HttpHost.httpHost + "/subcontractor/user/getOfficerInfo")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String  res, okhttp3.Call call, okhttp3.Response response) {
                        Log.e("onSuccess: ",res );
                    }
                });*/
        OkGo.get(HttpHost.httpHost + "/subcontractor/user/officerInfo")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MePhoneBean>() {
                    @Override
                    public void onSuccess(MePhoneBean res, okhttp3.Call call, okhttp3.Response response) {
                        if (res != null && res.getCode() == 0  && res.getData() !=null) {
                            mTv_lxht.setText(res.getData().getMobile());
                        } else {
                            T.showLong(MeActivity.this, res.getMessage());
                        }
                    }
                });
     /*   OkGo.get(HttpHost.httpHost + "/subcontractor/system/getUserGuide")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MeMessageBean>() {
                    @Override
                    public void onSuccess(MeMessageBean res, okhttp3.Call call, okhttp3.Response response) {
                        if (res != null && res.getCode() == 0 && res.getData() !=null) {
                            mIntent2 = new Intent(MeActivity.this, MeMessageInfoActivity.class);
                            mIntent2.putExtra("data",res.getData());
                            MeActivity.this.startActivity(mIntent2);
                        } else {
                            T.showLong(MeActivity.this, res.getMessage());
                        }
                    }
                });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_ht:
                if(re != null && re.getData() != null && re.getData().getSubcontractor_short_name() != null){
                    mIntent1 = new Intent(this, MeHtActivity.class);
                    mIntent1.putExtra("name",re.getData().getSubcontractor_short_name());
                    startActivity(mIntent1);
                }else {
                    T.showShort(this,"暂无数据");
                }

                break;
            case R.id.ll_hmc:
               startActivity(new Intent(this,ContractRosterActivity.class));
                break;
            case R.id.ll_sysm:
                startActivity(new Intent(this,HelpMessageActivity.class));
                break;
            case R.id.ll_lxht:
                String phone = mTv_lxht.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                //c 为斜边长  A为斜角    a b 为直角边



                startActivity(intent);
                break;
            case R.id.ll_xgmm:
                MeActivity.this.startActivity(new Intent(MeActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.ll_dqbb:

                break;
            case R.id.icon:
                showTakePhotoDialog();
                break;
            case R.id.ll_qchc:

                new CommomDialogbig(this, R.style.dialog, "是否确认清除缓存?", new CommomDialogbig.OnCloseListener() {

                    @Override
                    public void onClickDialog(Dialog dialog, boolean confirm) {
                        if(confirm){
                            DataCleanManager.clearAllCache(MeActivity.this);
                            mTv_qchc.setText("0.0M");
                            showToast("缓存已清理");
                        }
                        dialog.dismiss();
                    }
                }).setTitle("提示").setPositiveButton("确定").show();
                break;
            case R.id.ll_tcdl:
                new CommomDialogbig(this, R.style.dialog, "是否退出登录?", new CommomDialogbig.OnCloseListener() {

                    @Override
                    public void onClickDialog(Dialog dialog, boolean confirm) {
                        if(confirm){
                            SPUtils.remove("phone");
                            SPUtils.remove("password");
                            SPUtils.remove("projectId");
                            MeActivity.this.startActivity(new Intent(MeActivity.this,LoginActivity.class));
                            finish();
                            UserUtil.mainActivity.finish();
                            UserUtil.mainActivity = null;
                        }
                        dialog.dismiss();

                    }
                }).setTitle("提示").setPositiveButton("确定").show();

                break;
        }
    }

    File mFile;
    private boolean fileState = false;
    protected void onPhotoResult(int requestCode, boolean isSuccess, Bitmap bitmap, String outfile, File imageFile) {

        fileState = isSuccess;
        if (isSuccess) {
            mFile = imageFile;
            mIcon.setImageBitmap(bitmap);
            sendImage();
        } else {
            T.showLong(this, "图片获取失败");
        }
    }


    public void PpaNext(View view) {
        if (fileState) {
            sendImage();
        } else {
            T.showLong(this, "请先点击拍照");
        }
    }
    private ProgressDialog progressDialog;
    private void sendImage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        OkGo.post(HttpHost.httpHost + "/subcontractor/user/uploadImg")
                .headers("Content-Type", "multipart/form-data")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params("image", mFile)
                .execute(new JsonCallBack<UpDataUserIcon>() {
                    @Override
                    public void onSuccess(final UpDataUserIcon photographPersonnerUpBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                if(photographPersonnerUpBean.getCode() == 0){
                                    T.showLong(MeActivity.this, "更换成功");
                                }
                            }
                        });
                    }
                });
    }




    /*
 * 获取当前程序的版本号
 */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }
}
