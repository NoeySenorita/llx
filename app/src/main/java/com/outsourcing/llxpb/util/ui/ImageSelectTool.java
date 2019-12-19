package com.outsourcing.llxpb.util.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.model.Constants;
import com.outsourcing.llxpb.ui.base.BaseActivity;
import com.outsourcing.llxpb.ui.customView.SelectPicPopupWindow;

import java.io.File;

public class ImageSelectTool {
    private Uri uritempFile;
    /* public String filePath = Environment.getExternalStorageDirectory() + "/temp.jpg";*/
    public String filePath;
    private final static int PHOTO_REQUEST_CUT       = 908;
    private final static int PHOTO_REQUEST_TAKEPHOTO = 909;
    private final static int PHOTO_REQUEST_GALLERY   = 910;

    public boolean isCut = true;

    public int zoomWidth  = 100;//
    public int zoomHeight = 100;//
    private BaseActivity activity;
    private IPhotoCallBack callBack;
    public SelectPicPopupWindow menuWindow;

    public ImageSelectTool(int with, int height, BaseActivity activity, String path, IPhotoCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
        this.zoomWidth = with;
        this.zoomHeight = height;
        this.filePath = path;
        initSelectPopWindow();
    }

    public void initSelectPopWindow() {
        //实例化SelectPicPopupWindow
        menuWindow = new SelectPicPopupWindow(activity, itemsOnClick);
        menuWindow.setAnimationStyle(R.style.anim_photo_select);
    }
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", zoomWidth);
        intent.putExtra("aspectY", zoomHeight);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", zoomWidth);
        intent.putExtra("outputY", zoomHeight);
        intent.putExtra("return-data", false);

        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //        intent.putExtra("return-data", true);
        //uritempFile为Uri类变量，实例化uritempFile

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = new File(filePath);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uritempFile = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", file);
        } else {
            uritempFile = Uri.parse("file://" + "/" + filePath);
        }
        uritempFile = Uri.parse("file://" + "/" + filePath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        /*String s = Bitmap.CompressFormat.JPEG.toString();
        intent.putExtra("outputFormat", s);*/
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //为弹出poupwindow窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null)
                menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo://拍照
                    Intent intent1 = null;
                    File file = new File(filePath);
                    Uri pictureUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        pictureUri = FileProvider.getUriForFile(AppInstance.getContext(), Constants.PermissionConfig.FILE_PROVIDER, new File(filePath));
                    } else {
                        // 调用系统的拍照功能
                        intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 指定调用相机拍照后照片的储存路径
                        pictureUri = Uri.fromFile(file);
                    }
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    activity.startActivityForResult(intent1, PHOTO_REQUEST_TAKEPHOTO);
                    break;
                case R.id.btn_pick_photo://相册
                    Intent intent2 = new Intent(Intent.ACTION_PICK, null);
                    intent2.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            "image/*");
                    activity.startActivityForResult(intent2, PHOTO_REQUEST_GALLERY);
                    break;
                default:
                    break;
            }

        }

    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == activity.RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_TAKEPHOTO://当选择拍照时调用
                    if (isCut) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Uri pictureUri = FileProvider.getUriForFile(AppInstance.getContext(), Constants.PermissionConfig.FILE_PROVIDER, new File(filePath));
                            startPhotoZoom(pictureUri);
                        } else {
                            File file = new File(filePath);
                            startPhotoZoom(Uri.fromFile(file));
                        }

                    } else {
                        //解决图片过大 发生的闪烁桌面问题。
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callBack.callback(filePath);
                                    }
                                });
                            }
                        }).start();
                    }
                    break;
                case PHOTO_REQUEST_GALLERY://当选择从本地获取图片时
                    //做非空判断，当觉得不满意想重新剪裁的时候便不会报异常，下同
                    if (data != null) {
                        final Uri path = data.getData();
                        if (isCut) {
                            startPhotoZoom(path);
                        } else {
                            //解决图片过大 发生的闪烁桌面问题。
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    final String realFilePath = getRealFilePath(path);
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            callBack.callback(realFilePath);
                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                    break;
                case PHOTO_REQUEST_CUT://返回的结果
                    //                    if (data != null)
                    callBack.callback(filePath);
                    break;
            }
        }

    }


    public String getRealFilePath(final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = activity.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 将图片缩小/放大到指定宽高度
     *
     * @param drawable drawable 图片的drawable
     * @param w        w 指定缩小到的宽度
     * @param h        h 指定缩小到的高度
     * @param scale    scale 是否保持宽高比，TRUE:将忽略h的值，根据指定宽度自动计算高度 FALSE：根据指定宽度，高度生成图像
     * @return Drawable 返回新生成图片的Drawable
     */

    private Drawable zoomDrawable(Drawable drawable, int w, int h, Boolean scale) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth;
        float scaleHeight;
        if (scale == true) {
            // 如果要保持宽高比，那说明高度跟宽度的缩放比例都是相同的
            scaleWidth = ((float) w / width);
            scaleHeight = ((float) w / width);
        } else {
            // 如果不保持缩放比，那就根据指定的宽高度进行缩放
            scaleWidth = ((float) w / width);
            scaleHeight = ((float) h / height);
        }
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    /**
     * 根据图片Drawable返回图像
     *
     * @param drawable
     * @return Bitmap bitmap or null ;如果出错，返回NULL
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        try {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                    : Bitmap.Config.RGB_565;
            bitmap = Bitmap.createBitmap(width, height, config);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(activity, "error:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        return bitmap;
    }


}
