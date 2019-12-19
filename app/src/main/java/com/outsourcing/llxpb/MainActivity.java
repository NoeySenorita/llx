package com.outsourcing.llxpb;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.outsourcing.llxpb.Bean.MessageBean;
import com.outsourcing.llxpb.Bean.ProjectListBean;
import com.outsourcing.llxpb.Bean.SubcontractorBean;
import com.outsourcing.llxpb.adapter.MessageAdapter;
import com.outsourcing.llxpb.adapter.SelectProjectAdapter;
import com.outsourcing.llxpb.adapter.ViewPagerAdapter;
import com.outsourcing.llxpb.manager.MessageEvent;
import com.outsourcing.llxpb.model.Constants;
import com.outsourcing.llxpb.ui.base.CommonActivity;
import com.outsourcing.llxpb.ui.customView.CustomDialog.FadeSlideZoomInTransformer;
import com.outsourcing.llxpb.ui.customView.NoScrollViewPager;
import com.outsourcing.llxpb.util.HttpHost;
import com.outsourcing.llxpb.util.JsonCallBack;
import com.outsourcing.llxpb.util.T;
import com.outsourcing.llxpb.util.UserUtil;
import com.outsourcing.llxpb.view.activity.MeActivity;
import com.outsourcing.llxpb.view.activity.MessageListActivity;
import com.outsourcing.llxpb.view.fragment.EarlyWarningFragment;
import com.outsourcing.llxpb.view.fragment.HomeFragment;
import com.outsourcing.llxpb.view.fragment.IdleLaborFragment;
import com.outsourcing.llxpb.view.fragment.VerificationFragment;
import com.outsourcing.llxpb.view.fragment.ViewDeclarationFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends CommonActivity implements View.OnClickListener {
    private NoScrollViewPager mVpMain;
    private RelativeLayout mRlHome;
    private ImageView mIvHome;
    private TextView mTvHome;
    private RelativeLayout mRlVerify;
    private ImageView mIvVerify;
    private TextView mTvVerify;
    private RelativeLayout mRlWarning;
    private ImageView mIvWarning;
    private TextView mTvWarning;
    private RelativeLayout mIdle;
    private ImageView mIvIdle;
    private TextView mTvIdle;
    private int COLOR_ACTIVE;
    private int COLOR_NORMAL;
    private LinearLayout mContainer;
    private LinearLayout mLlLayout;
    private RelativeLayout mRlViewDeclaration;
    private ImageView mIvViewDeclaration;
    private TextView mTvViewDeclaration;
    public ArrayList<Fragment> mFragments;
    public static final int LOCATION_CODE = 301;
    private LocationManager locationManager;
    private String locationProvider = null;
    private TextView mTv_base_title_text;
    private View show;
    private TextView mName;
    private ImageView mMain_mg;
    private ImageView mMain_me;
    private Badge mBadge;
    RelativeLayout message_state;

    @Override
    public int getMainLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews(View mRootView) {
        getBarTool().hideTitleBar();
        mVpMain = findViewById(R.id.vp_main);
        mVpMain.setOffscreenPageLimit(0);
        mRlHome = findViewById(R.id.rl_home);
        mIvHome = findViewById(R.id.iv_home);
        mTvHome = findViewById(R.id.tv_home);
        mRlVerify = findViewById(R.id.rl_verify);
        mIvVerify = findViewById(R.id.iv_verify);
        mTvVerify = findViewById(R.id.tv_verify);
        mRlWarning = findViewById(R.id.rl_warning);
        mIvWarning = findViewById(R.id.iv_warning);
        mTvWarning = findViewById(R.id.tv_warning);
        mIdle = findViewById(R.id.rl_idle);
        mIvIdle = findViewById(R.id.iv_idle);
        mTvIdle = findViewById(R.id.tv_idle);
        mRlViewDeclaration = findViewById(R.id.rl_view_declaration);
        mIvViewDeclaration = findViewById(R.id.iv_view_declaration);
        mTvViewDeclaration = findViewById(R.id.tv_view_declaration);
        show = findViewById(R.id.show);
        mContainer = findViewById(R.id.container);
        mLlLayout = findViewById(R.id.ll_layout);
        message_state = findViewById(R.id.message_state);


        mVpMain.setScanScroll(false);
        COLOR_ACTIVE = Color.parseColor("#E88110");
        COLOR_NORMAL = Color.parseColor("#666666");
        //mVpMain.setAccessibilityPaneTitle();
        /*
         * ViewPager一次加载全部的Fragment
         * */
        mFragments = new ArrayList<>();
        mFragments.add(FragmentFactory.createFragment(Constants.FragmentsId.FRAGMENT_HOME));
        mFragments.add(FragmentFactory.createFragment(Constants.FragmentsId.FRAGMENT_VERIFICATION));
        mFragments.add(FragmentFactory.createFragment(Constants.FragmentsId.FRAGMENT_EARLY_WARNING));
        mFragments.add(FragmentFactory.createFragment(Constants.FragmentsId.FRAGMENT_IDLE_LABOR));
        mFragments.add(FragmentFactory.createFragment(Constants.FragmentsId.FRAGMENT_VIEW_DECLARATION));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
//        mVpMain.setOffscreenPageLimit(3);
        mVpMain.setAdapter(adapter);
        mVpMain.setPageTransformer(true, new FadeSlideZoomInTransformer());
        refreshStatus();

        initProject();
        getLocation();
        mTv_base_title_text = findViewById(R.id.tv_base_title_text);
        mTv_base_title_text.setText(UserUtil.default_project_short_name);
        mName = findViewById(R.id.name);
        mMain_mg = findViewById(R.id.main_mg);
        mMain_mg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MessageListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        mMain_me = findViewById(R.id.main_me);
        mMain_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserUtil.mainActivity == null){
                    UserUtil.mainActivity = MainActivity.this;
                }
                Intent intent = new Intent(MainActivity.this,MeActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.select_project).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopSelectProject();
            }
        });
        setCurrentItem(mRlHome);
    }

    PopupWindow mPopupWindow;
    private void showPopSelectProject() {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.result_page_popup_window, null);

        ListView list_project = contentView.findViewById(R.id.list_project);
        list_project.setAdapter(new SelectProjectAdapter(this));
        list_project.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserUtil.city_id = -1;
                UserUtil.city_id_time = 0;
                UserUtil.allow_execute_entry =0;
                UserUtil.projectId = UserUtil.projectList.get(position).getId()+"";
                UserUtil.project_id = UserUtil.projectList.get(position).getId();
                UserUtil.projectName = UserUtil.projectList.get(position).getName();
                UserUtil.default_project_short_name= UserUtil.projectList.get(position).getShort_name();
                mTv_base_title_text.setText(UserUtil.default_project_short_name);
                mPopupWindow.dismiss();
                //通知所有fragment
                EventBus.getDefault().post(new MessageEvent(""));
            }
        });
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        int width = mPopupWindow.getWidth();
        int[] xy = new int[2];
        show.getLocationInWindow(xy);
        //  mPopupWindow.showAtLocation(show, Gravity.CENTER_VERTICAL,
        //      xy[0] + (dip2px(width) - show.getWidth()) / 2, xy[1] - dip2px(50));
        mPopupWindow.showAsDropDown(show, 50, 0);
    }

    private void initProject() {
       // CrashReport.testJavaCrash();
        OkGo.get(HttpHost.httpHost + "/subcontractor/project/getUserProjectList")
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .execute(new JsonCallBack<ProjectListBean>() {
                    @Override
                    public void onSuccess(ProjectListBean projectListBean, okhttp3.Call call, okhttp3.Response response) {
                        if (projectListBean != null && projectListBean.getCode()== 0  ) {
                            if(projectListBean.getData()!=null){
                                if ( UserUtil.projectList.size() > 0){
                                    UserUtil.projectList.clear();
                                }
                                UserUtil.projectList.addAll(projectListBean.getData());
                            }else {
                                T.showLong(MainActivity.this, "暂无数据" +
                                        "");
                            }
                        } else {
                            T.showLong(MainActivity.this, projectListBean.getMessage());
                        }
                    }
                });
        OkGo.get(HttpHost.httpHost + "/subcontractor/project/getUserProjectList")
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .execute(new JsonCallBack<ProjectListBean>() {
                    @Override
                    public void onSuccess(ProjectListBean projectListBean, okhttp3.Call call, okhttp3.Response response) {
                        if (projectListBean != null && projectListBean.getCode()== 0 ) {
                            if(projectListBean.getData() !=null){
                                if ( UserUtil.projectList.size() > 0){
                                    UserUtil.projectList.clear();
                                }
                                UserUtil.projectList.addAll(projectListBean.getData());
                            }
                        } else {
                            T.showLong(MainActivity.this, projectListBean.getMessage());
                        }
                    }
                });
        OkGo.get(HttpHost.httpHost + "/subcontractor/home/getSubcontractor")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<SubcontractorBean>() {
                    @Override
                    public void onSuccess(SubcontractorBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {
                        if(earlyWBean1 != null && earlyWBean1.getData() != null ){
                            if(mName != null&& earlyWBean1.getData().getSubcontractor_short_name()!= null)
                                mName.setText(earlyWBean1.getData().getSubcontractor_short_name());
                        }else{
                            if(mName != null)
                                mName.setText("");
                        }

                    }
                });

        OkGo.get(HttpHost.httpHost + "/subcontractor/home/getNoticeList")
                .headers("Content-Type", "application/json")
                .headers("token", UserUtil.token)
                .headers("uuid", UserUtil.uuid)
                .headers("project_id", UserUtil.projectId)
                .execute(new JsonCallBack<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean earlyWBean1, okhttp3.Call call, okhttp3.Response response) {

                            if( mMain_mg!= null){
                                if (earlyWBean1 != null && earlyWBean1.getCode()==0 ) {
                                    if(earlyWBean1.getData() !=null){
                                        mBadge = new QBadgeView(MainActivity.this).bindTarget(mMain_mg);
                                        mBadge.setBadgeNumber(earlyWBean1.getData().getUnread_num());
                                        mBadge.setBadgeTextSize(5, true);
                                        mBadge.setBadgeGravity(Gravity.END | Gravity.TOP);
                                    }
                                } else {
                                    T.showLong(MainActivity.this, earlyWBean1.getMessage());
                                }
                            }


                    }
                });
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRlHome.setOnClickListener(this);
        mRlVerify.setOnClickListener(this);
        mRlWarning.setOnClickListener(this);
        mIdle.setOnClickListener(this);
        mRlViewDeclaration.setOnClickListener(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentFactory.clearCache();
        locationManager.removeUpdates(locationListener);
    }


    private long mExitTime;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
//                AppManager.getAppManager().AppExit(AppInstance.getContext());
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        refreshStatus();
        setCurrentItem(view);
    }

    private void refreshStatus() {
    //    mIvHome.setImageResource(R.mipmap.home_normal);
        mIvHome.setImageResource(R.mipmap.verify_normal);
        mTvHome.setTextColor(COLOR_NORMAL);
        mIvVerify.setImageResource(R.mipmap.verify_normal);
        mTvVerify.setTextColor(COLOR_NORMAL);
        mIvWarning.setImageResource(R.mipmap.warning_normal);
        mTvWarning.setTextColor(COLOR_NORMAL);
        mIvIdle.setImageResource(R.mipmap.idle_normal);
        mTvIdle.setTextColor(COLOR_NORMAL);
        mIvViewDeclaration.setImageResource(R.mipmap.view_declaration_normal);
        mTvViewDeclaration.setTextColor(COLOR_NORMAL);

    }

    public void setCurrentItem(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
            //    mIvHome.setImageResource(R.mipmap.home_active);
                mIvHome.setImageResource(R.mipmap.verify_active);
                mTvHome.setTextColor(COLOR_ACTIVE);
                mVpMain.setCurrentItem(0);
                message_state.setVisibility(View.VISIBLE);
                mMain_me.setVisibility(View.VISIBLE);
                mName.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_verify:
                mIvVerify.setImageResource(R.mipmap.verify_active);
                mTvVerify.setTextColor(COLOR_ACTIVE);
                mVpMain.setCurrentItem(1);
                message_state.setVisibility(View.GONE);
                mMain_me.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                break;
            case R.id.rl_warning:
                mIvWarning.setImageResource(R.mipmap.warning_active);
                mTvWarning.setTextColor(COLOR_ACTIVE);
                mVpMain.setCurrentItem(2);
                message_state.setVisibility(View.GONE);
                mMain_me.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                break;
            case R.id.rl_idle:
                mIvIdle.setImageResource(R.mipmap.idle_active);
                mTvIdle.setTextColor(COLOR_ACTIVE);
                mVpMain.setCurrentItem(3);
                message_state.setVisibility(View.GONE);
                mMain_me.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                break;
            case R.id.rl_view_declaration:
                mIvViewDeclaration.setImageResource(R.mipmap.view_declaration_active);
                mTvViewDeclaration.setTextColor(COLOR_ACTIVE);
                mVpMain.setCurrentItem(4);
                message_state.setVisibility(View.GONE);
                mMain_me.setVisibility(View.GONE);
                mName.setVisibility(View.GONE);
                break;

        }
    }

    public void clickChange(){
        mIvViewDeclaration.setImageResource(R.mipmap.view_declaration_active);
        mTvViewDeclaration.setTextColor(COLOR_ACTIVE);
        mVpMain.setCurrentItem(1);
        mVpMain.setCurrentItem(4);
    }



    private void getLocation () {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
            } else {
                //监视地理位置变化
                if(locationListener!= null && locationManager!=null&&locationProvider!=null){
                    locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
                    Location location = locationManager.getLastKnownLocation(locationProvider);
                    if (location != null) {
                        //输入经纬度
                        UserUtil.lat = location.getLatitude();
                        UserUtil.lon = location.getLongitude();
                        //  Toast.makeText(this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            //监视地理位置变化
            locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                //不为空,显示地理位置经纬度
                UserUtil.lat = location.getLatitude();
                UserUtil.lon = location.getLongitude();
            //    Toast.makeText(this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
        }
        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
        }
        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                //不为空,显示地理位置经纬度
                UserUtil.lat = location.getLatitude();
                UserUtil.lon = location.getLongitude();
            }
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "申请权限", Toast.LENGTH_LONG).show();
                    try {
                        List<String> providers = locationManager.getProviders(true);
                        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                            //如果是Network
                            locationProvider = LocationManager.NETWORK_PROVIDER;
                        }else if (providers.contains(LocationManager.GPS_PROVIDER)) {
                            //如果是GPS
                            locationProvider = LocationManager.GPS_PROVIDER;
                        }
                        //监视地理位置变化
                        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
                        Location location = locationManager.getLastKnownLocation(locationProvider);
                        if (location != null) {
                            //不为空,显示地理位置经纬度
                            UserUtil.lat = location.getLatitude();
                            UserUtil.lon = location.getLongitude();
                          //  Toast.makeText(MainActivity.this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                        }
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "请打开位置权限", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }


}
