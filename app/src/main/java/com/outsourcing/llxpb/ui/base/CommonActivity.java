package com.outsourcing.llxpb.ui.base;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.StatusBar;
import com.outsourcing.llxpb.util.permission.PermissionCallBack;
import com.outsourcing.llxpb.util.permission.PermissionUtils;
import com.outsourcing.llxpb.util.ui.UIUtils;
import com.lzy.okgo.OkGo;

public abstract class CommonActivity extends BaseActivity {
    private RelativeLayout mRlBack;
    private TextView mTvTitle;
    private LinearLayout mLlRight;
    private RelativeLayout mRlTitleBar;
    protected String TAG;
    private View mRootView;
    private FrameLayout mFlContainer;
    private TitleBarTool mBarTool;
    protected Context mActivity;
    private ImageView mIvRightIcon,iv_title;
    private TextView mTvRight;
    protected ProgressBar mProgress;
    protected boolean mInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
        setContentView(R.layout.activity_common);

        TAG = this.getClass().getSimpleName();
        mActivity = this;
        initTitleView();
        int layoutId = getMainLayoutId();
        mRootView = LayoutInflater.from(this).inflate(layoutId, null);
        addContentLayout(mRootView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initWithPermission();
        } else {
            findViews(mRootView);
            initListener();
            initData();
        }
        floatStatusBar();
    }
    protected void floatStatusBar() {
        // StatusBarUtil.fullScreen(this);
        StatusBar.transportStatus(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //LinearLayout.LayoutParams titleParams = (LinearLayout.LayoutParams) ll_title.getLayoutParams();
            mRlTitleBar.setPadding(0, 28, 0, 0);
            // 标题栏在上方留出一段距离，看起来仍在状态栏下方

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("OkGo", this.getClass().getSimpleName() + " cancel request");
        OkGo.getInstance().cancelAll();
    }

    private void initTitleView() {
        mRlTitleBar = findViewById(R.id.rl_title_bar);
        iv_title = findViewById(R.id.iv_title);
        mRlBack = findViewById(R.id.rl_back);
        mTvTitle = findViewById(R.id.tv_title);
        mLlRight = findViewById(R.id.ll_right_more);
        mFlContainer = findViewById(R.id.fl_container);
        mIvRightIcon = findViewById(R.id.iv_right_icon);
        mTvRight = findViewById(R.id.tv_right);
        mProgress = findViewById(R.id.pb_loading);

        mBarTool = new TitleBarTool();
        mTvTitle.setText(this.getClass().getSimpleName());

        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onBack()) {
                    finish();
                }
            }
        });
        mLlRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightOptionClick();
            }
        });
    }

    private void addContentLayout(View mRootView) {
        mFlContainer.removeAllViews();
        mFlContainer.addView(mRootView);
    }

    public abstract int getMainLayoutId();


    /**
     * 标题栏控制器
     *
     * @author Administrator
     * @time 2018/5/21 0011 下午 4:23
     **/
    protected class TitleBarTool {
        private static final int NONE = 0;

        public TitleBarTool hideTitleBar() {
            mRlTitleBar.setVisibility(View.GONE);
            return this;
        }

        public TitleBarTool hideIvTitleBar() {
            iv_title.setVisibility(View.GONE);
            return this;
        }

        public TitleBarTool showIvTitleBar() {
            iv_title.setVisibility(View.GONE);
            return this;
        }



        public TitleBarTool setTitleBarBackground(int color) {
            mRlTitleBar.setBackgroundColor(color);
            return this;
        }

        public TitleBarTool setTitle(String titleText) {
            mTvTitle.setText(titleText);
            return this;
        }


        public TitleBarTool setRightIcon(int resId) {
            mLlRight.setVisibility(View.VISIBLE);
            mIvRightIcon.setImageResource(resId);
            mTvRight.setText("");
            return this;
        }

        public TitleBarTool setRightText(String text) {
            mLlRight.setVisibility(View.VISIBLE);
            mIvRightIcon.setVisibility(View.GONE);
            mTvRight.setText(text);
            return this;
        }

        public TitleBarTool setRightText(String text, int textColor, boolean showIcon) {
            mLlRight.setVisibility(View.VISIBLE);
            if (!showIcon) {
                mIvRightIcon.setVisibility(View.GONE);
            }
            mTvRight.setText(text);
            mTvRight.setTextColor(textColor);
            return this;
        }

        public TitleBarTool setRightOption(String text, int resId) {
            mLlRight.setVisibility(View.VISIBLE);
            if (text == null) {
                mTvRight.setText("");
            } else {
                mTvRight.setText(text);
            }
            if (resId == NONE) {
                mIvRightIcon.setVisibility(View.GONE);
            } else {
                mIvRightIcon.setImageResource(resId);
            }
            return this;
        }
    }

    public TitleBarTool getBarTool() {
        return mBarTool;
    }

    public View getmRootView() {
        return mRootView;
    }

    private void initWithPermission() {
        Log.i(TAG, this.getClass().getSimpleName() + " call initDataWithPermission()");
        String[] permissions = getRequiredRuntimePermissions();
        Log.i(TAG, this.getClass().getSimpleName() + "initDataWithPermission:" + permissions[0]);
        PermissionUtils.requestPermissions(mActivity, new PermissionCallBack() {
            @Override
            protected void onAcpGranted() {
                findViews(mRootView);
                initListener();
                initData();
            }
        }, permissions);
    }

    protected String[] getRequiredRuntimePermissions() {
        return new String[]{Manifest.permission.INTERNET,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE  ,Manifest.permission.CALL_PHONE};
    }

    protected void initData() {
        mInitial = true;
    }

    protected void initListener() {
    }

    protected abstract void findViews(View mRootView);

    protected void onRightOptionClick() {

    }

    protected void bindBack(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected boolean onBack() {
        return false;
    }


    public void handleBeforeRequest(String s) {
        if (mInitial) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    public void handleAfterRequest(String s, Exception e) {
        mProgress.setVisibility(View.GONE);
        if (e != null && e.getMessage() != null) {
            UIUtils.showSingleToast(e.getMessage());
        }

    }

    public void handleSuccess(String s) {
        mProgress.setVisibility(View.GONE);
    }

    public void handleError(String e) {
        mProgress.setVisibility(View.GONE);
        UIUtils.showSingleToast(e);
    }
}
