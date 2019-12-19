package com.outsourcing.llxpb.ui.base;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.ui.customView.CustomDialog.CustomDialog;
import com.outsourcing.llxpb.ui.customView.PicSelectDialog;
import com.outsourcing.llxpb.ui.customView.RoundImageView;
import com.outsourcing.llxpb.util.ui.UIUtils;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class TakePhotoActivity extends CommonActivity {

    private static final int SELECT_PICTURE_REQUEST = 201;
    private static final int OPEN_CAMERA_REQUEST = 202;
    private String imageFilePath;
    private Context context;
    private File mImageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @Override
    public int getMainLayoutId() {
        return getContentId();
    }

    protected void showTakePhotoDialog() {
        new PicSelectDialog(this, new PicSelectDialog.OnItemSelectListener() {
            @Override
            public void onSelectPic() {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_PICTURE_REQUEST);
            }

            @Override
            public void onTakePhoto() {
                imageFilePath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" + getFileName() + ".jpg";
                mImageFile = new File(imageFilePath);
                Uri imageFileUri = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageFileUri = FileProvider.getUriForFile(context, getApplicationContext().getPackageName() + ".fileprovider", mImageFile);
                } else {
                    imageFileUri = Uri.fromFile(mImageFile);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(intent, OPEN_CAMERA_REQUEST);
            }
        });
    }

    private boolean select;
    protected void showTakePhotoDialog(boolean selsct) {
        this.select = selsct;
        new PicSelectDialog(this, new PicSelectDialog.OnItemSelectListener() {
            @Override
            public void onSelectPic() {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_PICTURE_REQUEST);
            }

            @Override
            public void onTakePhoto() {
                imageFilePath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" + getFileName() + ".jpg";
                mImageFile = new File(imageFilePath);
                Uri imageFileUri = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageFileUri = FileProvider.getUriForFile(context, getApplicationContext().getPackageName() + ".fileprovider", mImageFile);
                } else {
                    imageFileUri = Uri.fromFile(mImageFile);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(intent, OPEN_CAMERA_REQUEST);
            }
        });
    }

    private String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;
        if (data != null) {
            final String path = getPicPath(data);
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(path).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, String outfile) {
                    //return the compressed file path and bitmap object
                    File file = new File(path);
                    onPhotoResult(requestCode,isSuccess,bitmap,outfile,file);
                    onPhotoResult(requestCode,isSuccess,bitmap,outfile,file,select);
                 //   deletePhoto(path);
                }
            });

        } else {
            /**
             * 拍照时返回的data是空的
             * */
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(imageFilePath).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, String outfile) {
                    //return the compressed file path and bitmap object

                    onPhotoResult(requestCode,isSuccess,bitmap, outfile,mImageFile);
                    onPhotoResult(requestCode,isSuccess,bitmap, outfile,mImageFile,select);

                  //  deletePhoto(imageFilePath);
                }
            });

        }
    }

    private String getPicPath(Intent intent) {
        Uri selectedImage = intent.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    protected void onPhotoResult(int requestCode, boolean isSuccess, Bitmap bitmap, String outfile,File mImageFile){

    }
    protected void onPhotoResult(int requestCode, boolean isSuccess, Bitmap bitmap, String outfile,File mImageFile,Boolean select){

    }
    public abstract int getContentId();

    protected void showImagePreview(Bitmap bitmap) {
        CustomDialog dialog = new CustomDialog.Builder(context)
                .setContentView(R.layout.view_image_preview)
                .bindCloseView(R.id.iv_close)
                .fullScreen().fromBottom(true)
                .show();
        RoundImageView iv = dialog.getView(R.id.iv_img);
        long start = SystemClock.currentThreadTimeMillis();
//        UIUtils.displayImageWithRoundCorner(10, bitmap, iv,R.drawable.add_img);
        iv.setImageBitmap(bitmap);
        long end = SystemClock.currentThreadTimeMillis();
        Log.i(UIUtils.class.getName(),end-start+"ms");
    }

    protected void deletePhoto(String imageFilePath){
        try {
           // File file = new File(imageFilePath);
        //    boolean delete = file.delete();
         //   String s = delete?"成功":"失败";
         //   UIUtils.showToast("删除原图"+s);
        }catch (Exception e){
         //   UIUtils.showToast("删除失败:文件不存在！");
        }

    }
}
