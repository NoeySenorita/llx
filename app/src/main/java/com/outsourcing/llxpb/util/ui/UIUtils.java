package com.outsourcing.llxpb.util.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.outsourcing.llxpb.AppInstance;
import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.model.Constants;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 创建者     Hfengxiang
 * 版权       黄凤翔
 * 描述	      封装和ui相关的操作
 */
public class UIUtils {
    /**
     * 单例吐司
     */
    private static Toast toast;
    private static ProgressDialog dialog;

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return AppInstance.getContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串信息
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }


    /**
     * 得到String.xml中的字符串数组信息
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到Color.xml中的颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static float getDimen(int resId) {
        return getResources().getDimension(resId);
    }

    /**
     * 得到应用程序包名
     *
     * @return
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * dip-->px
     *
     * @param dip
     * @return
     */
    public static int dip2Px(int dip) {
        /*
        1.  px/(ppi/160) = dp
        2.  px/dp = density
         */

        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2Dip(int px) {
        // 2.  px/dp = density

        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;

        int dip = (int) (px / density + .5f);
        return dip;
    }

    /**
     * 弹出单例吐司
     */
    public static void showSingleToast(CharSequence sequence) {
        if (toast == null) {
            //创建一个空的吐司
            toast = Toast.makeText(AppInstance.getContext(), sequence, Toast.LENGTH_SHORT);
        }
        //设置吐司的位置在左侧板块居中
//        int width = ScreenUtils.getScreenWidth(getContext());
//        int height = ScreenUtils.getScreenHeight(getContext());
//        toast.setGravity(Gravity.CENTER, -width/6, height/6);
        //给吐司的内容设置自己想要的值
        toast.setText(sequence.toString());
        toast.show();//弹出吐司

    }

    /**
     * 弹出单例吐司
     */
    public static void showToast(CharSequence sequence) {
        Toast.makeText(AppInstance.getContext(), sequence, Toast.LENGTH_SHORT).show();

    }

    public static void showSingleToast(CharSequence sequence, boolean isDebug) {
        if (isDebug && Constants.AppSettings.CLOSE_TOAST_DEBUG) {
            return;
        }
        if (toast == null) {
            //创建一个空的吐司
            toast = Toast.makeText(AppInstance.getContext(), sequence, Toast.LENGTH_SHORT);
        }
        //设置吐司的位置在左侧板块居中
//        int width = ScreenUtils.getScreenWidth(getContext());
//        int height = ScreenUtils.getScreenHeight(getContext());
//        toast.setGravity(Gravity.CENTER, -width/6, height/6);
        //给吐司的内容设置自己想要的值
        toast.setText(sequence.toString());
        toast.show();//弹出吐司

    }

    public static void showSingleToast(Context context, CharSequence sequence) {
        if (Constants.AppSettings.CLOSE_TOAST_DEBUG) {
            return;
        }
        if (toast == null) {
            //创建一个空的吐司
            toast = Toast.makeText(context.getApplicationContext(), sequence, Toast.LENGTH_SHORT);
        }
        //给吐司的内容设置自己想要的值
        toast.setText(sequence.toString());
        toast.show();//弹出吐司

    }

//    public static void showProgressDialog(Context context) {
//        if (dialog != null) {
//            dialog.dismiss();
//            dialog = null;
//        }
//        dialog = new ProgressDialog(context);
//        dialog.setCancelable(false);
//        dialog.show();
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
//        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.dialog_view);
//        dialog.setContentView(layout, new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT));
//        Point size = new Point();
//        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
//        int width = size.x;
//
//
//        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//        params.alpha = 0.8f;   //设置进度条背景透明度
//        params.gravity = Gravity.BOTTOM;
//        params.width = 9 * width / 10;
//        params.dimAmount = 0f;    //设置进度条显示后后面Activity的背景
//        dialog.getWindow().setAttributes(params);
//    }

//    public static void showProgressDialog(Context context, String msg) {
//        if (dialog != null) {
//            dialog.dismiss();
//            dialog = null;
//        }
//        dialog = new ProgressDialog(context);
//        dialog.setCancelable(false);
//        dialog.show();
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
//        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.dialog_view);
//        TextView tvLoadingMsg = (TextView) layout.findViewById(R.id.tv_loading_msg);
//        tvLoadingMsg.setText(msg);
//        dialog.setContentView(layout, new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT));
//        Point size = new Point();
//        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
//        int width = size.x;
//        int height = size.y;
//        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//        params.alpha = 0.8f;   //设置进度条背景透明度
//        params.y = height / 6;
//        params.width = 4 * width / 10;
//        params.dimAmount = 0f;    //设置进度条显示后后面Activity的背景
//        dialog.getWindow().setAttributes(params);
//
//    }
//
//    public static void closeProgressDialog() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//            dialog = null;
//        }
//    }


    public static String transForDate2(long ms) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(ms));
        return sd;
    }

    public static void displayImageWithRoundCorner(int corner, Bitmap bitmap, ImageView imageView, int defaultId) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(defaultId)
                .transform(new RoundedCorners(corner));
        Glide.with(AppInstance.getContext())
                .load(stream.toByteArray())
//                .asBitmap()
//                .skipMemoryCache(true)
//                .placeholder(defaultId)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .transform(transformation)
                .into(imageView);
    }

    public static void displayImageWithRoundCorner(int corner, String url, ImageView imageView, int defaultImgId) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .error(defaultImgId)
                .placeholder(defaultImgId)
                .transform(new RoundedCorners(corner));
        Glide.with(AppInstance.getContext())
                .load(url)
//                .asBitmap()
//                .skipMemoryCache(true)
//                .error(defaultImgId)
//                .placeholder(defaultImgId)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .transform(transformation)
                .apply(options)
                .into(imageView);
    }

    public static void displayImage(Context context, Object source, int defaultId, ImageView target) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .placeholder(defaultId);
        Glide.with(context).load(source).apply(options).into(target);
    }

    public static void displayImageWithRoundCorner(int corner, String url, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .transform(new RoundedCorners(corner));
        Glide.with(AppInstance.getContext())
                .load(url)
//                .asBitmap()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .transform(transformation)
                .apply(options)
                .into(imageView);
    }

    public static List<String> convertToList(String strs) {
        String[] split = strs.split(",");
        return new ArrayList<>(Arrays.asList(split));
    }

    public static void initRefreshLayout(TwinklingRefreshLayout mRefreshLayout, Context context) {
        SinaRefreshView headerView = new SinaRefreshView(context);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(context);
        mRefreshLayout.setBottomView(loadingView);
    }

    public static void initRefreshLayout(TwinklingRefreshLayout mRefreshLayout, boolean isPureScroll) {
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableOverScroll(isPureScroll);
    }

    public static String getTime(Date date, String formater) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat(formater);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date string2Date(String time, String formater) {//可根据需要自行截取数据显示
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getStepDay(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, step);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    public static int getCurrentMonthDays() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static int getLastMonthDays() {
        //取得系统当前时间
        Calendar cal = Calendar.getInstance();
        //取得系统当前时间所在月第一天时间对象
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //日期减一,取得上月最后一天时间对象
        cal.add(Calendar.DAY_OF_MONTH, -1);
        //输出上月最后一天日期
        int maxDate = cal.get(Calendar.DAY_OF_MONTH);
        return maxDate;
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            showSingleToast("手机号应为11位数");
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                showSingleToast("请填入正确的手机号");
            }
            return isMatch;
        }
    }

    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }


    // 两次点击按钮之间的点击间隔不能少于300毫秒
    private static final int MIN_CLICK_DELAY_TIME = 300;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getLocalVersion(Context ctx) {
        int localVersion = Integer.MAX_VALUE;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static void setFont(TextView textView,int fontResId){
        Typeface typeface = ResourcesCompat.getFont(AppInstance.getContext(), fontResId);
        textView.setTypeface(typeface);
    }
    public static String getCurrentDate(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public static String getCurrentDate(){
        return getCurrentDate("yyyy-MM-dd");
    }
    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
