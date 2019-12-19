package com.outsourcing.llxpb.view.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gamerole.orcameralib.CameraActivity;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.CheckInProjectBean;
import com.outsourcing.llxpb.Bean.CheckNameIdCareBean;
import com.outsourcing.llxpb.Bean.PhotographPersonnerUpBean;
import com.outsourcing.llxpb.Bean.RecognizeBankCardBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.base.TakePhotoActivity;
import com.outsourcing.llxpb.ui.customView.CommomDialogbig;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.fragment.VerificationFragment;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class PersonnerIdCardActivity extends CommonActivity {


    private ImageView mFront_id_card_fileBODY;
    private ImageView mBack_id_card_fileBODY;
    String photograph_iamge;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_personner_id_card;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("上传身份证");
        photograph_iamge = getIntent().getStringExtra("photograph_iamge");
        mFront_id_card_fileBODY = findViewById(R.id.front_id_card_fileBODY);
        mBack_id_card_fileBODY = findViewById(R.id.back_id_card_fileBODY);
        UserUtil.sendPerposesActivitys.add(this);
    }


    //上传身份证照片
    public void nextSendIdCore(View view) {
        if (frontFile == null) {
            T.showLong(this, "请选择身份证正面");
            return;
        }
        if (backFile == null) {
            T.showLong(this, "请选择身份证国徽面");
            return;
        }

       /* new CommomDialogbig(this, R.style.dialog, "此人身份证提交公安部门比对后存在问题，如继续提交系统会自动标识此身份证异常，是否继续提交？", new CommomDialogbig.OnCloseListener() {

            @Override
            public void onClickDialog(Dialog dialog, boolean confirm) {
                if (confirm) {
                    sendImageFront("id_card_front_file", frontFile);
                    dialog.dismiss();
                }
            }
        }).setTitle("提示").show();*/
        sendImageFront("id_card_front_file", frontFile);

    }

    String frontFileUrl;
    String backFileUrl;
    private ProgressDialog progressDialog;

    private void sendImageFront(String name, File mFile) {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        OkGo.post(HttpHost.httpHost + "/subcontractor/roster/uploadIDCardFront")
                .headers("Content-Type", "multipart/form-data")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params(name, mFile)
                .execute(new JsonCallBack<PhotographPersonnerUpBean>() {
                    @Override
                    public void onSuccess(final PhotographPersonnerUpBean photographPersonnerUpBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                if (photographPersonnerUpBean != null && photographPersonnerUpBean.getMessage().contains("success") && photographPersonnerUpBean.getData() != null) {
                                    T.showLong(PersonnerIdCardActivity.this, "正脸上传成功");
                                    frontFileUrl = photographPersonnerUpBean.getData().getWeb_url();
                                    sendImageBreak("id_card_back_file", backFile);
                                } else {
                                    T.showLong(PersonnerIdCardActivity.this, photographPersonnerUpBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }

    private void sendImageBreak(String name, File mFile) {
        OkGo.post(HttpHost.httpHost + "/subcontractor/roster/uploadIDCardBack")
                .headers("Content-Type", "multipart/form-data")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params(name, mFile)
                .execute(new JsonCallBack<PhotographPersonnerUpBean>() {
                    @Override
                    public void onSuccess(final PhotographPersonnerUpBean photographPersonnerUpBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (photographPersonnerUpBean != null && photographPersonnerUpBean.getMessage().contains("success")) {

                                    checkIdCard(frontFileUrl, photographPersonnerUpBean.getData().getWeb_url());
                                    T.showLong(PersonnerIdCardActivity.this, "国徽上传成功");

                                } else {
                                    T.showLong(PersonnerIdCardActivity.this, photographPersonnerUpBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }

    private void checkIdCard(String front, final String back) {
        UserUtil.sCheckNameIdCareBean = null;
        OkGo.get(HttpHost.httpHost + "/subcontractor/roster/recognizeAndVerifyIDCard?id_card_front_url=" + front + "&id_card_back_url=" + back)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<CheckNameIdCareBean>() {
                    @Override
                    public void onSuccess(final CheckNameIdCareBean checkNameIdCareBean, okhttp3.Call call, okhttp3.Response response) {
                        if (checkNameIdCareBean != null && checkNameIdCareBean.getCode() == 0) {
                            UserUtil.sCheckNameIdCareBean =  checkNameIdCareBean;
                            if (checkNameIdCareBean.getData().getAllow_migrant_worker_entry() == 1) {
                                if (checkNameIdCareBean.getData().getVerify_flag() == 0) { //实名认证未通过
                                    new CommomDialogbig(PersonnerIdCardActivity.this, R.style.dialog, "此身份证提交公安部门对比后存在问题，可以继续提交，系统会自动标识此身份证异常，是否继续提交？", new CommomDialogbig.OnCloseListener() {

                                        @Override
                                        public void onClickDialog(Dialog dialog, boolean confirm) {
                                            if (confirm) {
                                                UserUtil.sCheckNameIdCareBean =  checkNameIdCareBean;
                                                Intent intent = new Intent(PersonnerIdCardActivity.this, CompleteInformationActivity.class);
                                                intent.putExtra("photograph_iamge", photograph_iamge);
                                                intent.putExtra("frontFileUrl", frontFileUrl);
                                                intent.putExtra("backFileUrl", back);
                                                PersonnerIdCardActivity.this.startActivity(intent);
                                                dialog.dismiss();
                                            }
                                        }
                                    }).setTitle("提示").show();
                                } else {
                                    UserUtil.sCheckNameIdCareBean =  checkNameIdCareBean;
                                    Intent intent = new Intent(PersonnerIdCardActivity.this, CompleteInformationActivity.class);
                                    intent.putExtra("photograph_iamge", photograph_iamge);
                                    intent.putExtra("frontFileUrl", frontFileUrl);
                                    intent.putExtra("backFileUrl", back);
                                    PersonnerIdCardActivity.this.startActivity(intent);
                                }
                            } else {
                                T.showLong(PersonnerIdCardActivity.this, checkNameIdCareBean.getData().getNot_allow_reason());
                            }
                        } else {
                            T.showLong(PersonnerIdCardActivity.this, checkNameIdCareBean.getMessage());
                        }
                    }
                });
    }

    //选择正面
    public void selectIdCartFront(View view) {
        frontPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getFileName() + ".jpg";
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, frontPath);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        startActivityForResult(intent, FRONT_CODE);
    }

    //选择反面
    public void selectIdCartBack(View view) {
        backPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getFileName() + ".jpg";
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, backPath);
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
        startActivityForResult(intent, BACK_CODE);
    }

    File frontFile;
    File backFile;
    String frontPath;
    String backPath;
    int FRONT_CODE = 8;
    int BACK_CODE = 9;

    private String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    protected void onPhotoResult(int requestCode, boolean isSuccess, Bitmap bitmap, String outfile, File mImageFile, Boolean select) {
        if (isSuccess) {
            if (select) {
                frontFile = mImageFile;
                mFront_id_card_fileBODY.setImageBitmap(bitmap);
            } else {
                backFile = mImageFile;
                mBack_id_card_fileBODY.setImageBitmap(bitmap);
            }

        } else {
            T.showLong(this, "图片获取失败");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (FRONT_CODE == requestCode) {
            if (data != null) {
                //  File file = new File(frontPath);
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(frontPath).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, String outfile) {
                        //return the compressed file path and bitmap object
                        frontFile = new File(frontPath);
                        mFront_id_card_fileBODY.setImageBitmap(bitmap);
                        // T.showShort();
                        //   deletePhoto(path);
                    }
                });
            }

        } else if (BACK_CODE == requestCode) {
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(backPath).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, String outfile) {
                    //return the compressed file path and bitmap object
                    backFile = new File(backPath);
                    mBack_id_card_fileBODY.setImageBitmap(bitmap);
                    // T.showShort();
                    //   deletePhoto(path);
                }
            });
        }
        // else handle other activity results
    }

}
