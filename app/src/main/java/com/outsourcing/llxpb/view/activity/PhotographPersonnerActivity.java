package com.outsourcing.llxpb.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gamerole.orcameralib.CameraActivity;
import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.Bean.PhotographPersonnerUpBean;
import com.outsourcing.llxpb.Bean.RecognizeBankCardBean;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.base.TakePhotoActivity;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class PhotographPersonnerActivity extends CommonActivity implements View.OnClickListener {


    private int REQUEST_CAPTURE_IMAGE = 1;
    private String mFPath;
    private static final int REQUEST_PERMISSION_CODE = 101;
    private Uri mUri;
    private int REQUEST_TAKE_PHOTO_CODE = 2;
    private ImageView mIg_select;
    private boolean fileState = false;
    private String mMessage;

    /*@Override
    public int getContentId() {
        return R.layout.activity_photograph_personner;
    }*/

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_photograph_personner;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().setTitle("上传头像");
        mIg_select = findViewById(R.id.ig_select);
        mIg_select.setOnClickListener(this);
        if( UserUtil.sendPerposesActivitys == null){
            UserUtil.sendPerposesActivitys = new ArrayList<>();
        }
        UserUtil.sendPerposesActivitys.add(this);
    }

    File mFile;

    protected void onPhotoResult(int requestCode, boolean isSuccess, Bitmap bitmap, String outfile, File imageFile) {

        fileState = isSuccess;
        if (isSuccess) {
            mFile = imageFile;
            mIg_select.setImageBitmap(bitmap);
        } else {
            T.showLong(this, "图片获取失败");
        }
    }


    public void PpaNext(View view) {
        if (mFile != null) {
            sendImage();
        } else {
            T.showLong(this, "请先点击拍照");
        }
    }
    private void sendImage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        OkGo.post(HttpHost.httpHost + "/subcontractor/roster/uploadFacialPhoto")
                .headers("Content-Type", "multipart/form-data")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .params("facial_photo_file", mFile)
                .execute(new JsonCallBack<PhotographPersonnerUpBean>() {
                    @Override
                    public void onSuccess(final PhotographPersonnerUpBean photographPersonnerUpBean, okhttp3.Call call, okhttp3.Response response) {

                        AppInstance.gethandler().post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (photographPersonnerUpBean != null && photographPersonnerUpBean.getMessage().contains("success")) {
                                    T.showLong(PhotographPersonnerActivity.this, "上传成功");
                                    Intent intent = new Intent(PhotographPersonnerActivity.this, PersonnerIdCardActivity.class);
                                    intent.putExtra("projectId", UserUtil.projectId);
                                    intent.putExtra("projectName", UserUtil.projectName);
                                    intent.putExtra("photograph_iamge", photographPersonnerUpBean.getData().getWeb_url());
                                    PhotographPersonnerActivity.this.startActivity(intent);
                                } else {
                                    T.showLong(PhotographPersonnerActivity.this, photographPersonnerUpBean.getMessage());
                                }

                            }
                        });
                    }
                });
    }

String mCardPath;
    int REQUEST_CODE = 9;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ig_select) {
            mCardPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/" + getFileName() + ".jpg";
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,mCardPath );
            intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }


    private ProgressDialog progressDialog;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(REQUEST_CODE == requestCode){
            if(data !=null){
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(mCardPath).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, String outfile) {
                        //return the compressed file path and bitmap object
                        mFile = new File(mCardPath);
                        mIg_select.setImageBitmap(bitmap);
                       // T.showShort();
                        //   deletePhoto(path);
                    }
                });

            }

        }
        // else handle other activity results
    }

    private String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
