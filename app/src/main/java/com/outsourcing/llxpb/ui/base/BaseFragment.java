package com.outsourcing.llxpb.ui.base;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.outsourcing.llxpb.R;
import com.outsourcing.llxpb.util.permission.PermissionCallBack;
import com.outsourcing.llxpb.util.permission.PermissionUtils;


/**
 * Created by Administrator on 2018/3/12 0012.
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    public View mRootView;
    public BaseActivity mActivity;
    public ProgressBar rl_pb;
    private RelativeLayout rlBasePageTitle;
    private TextView tvBaseTitleText;
    private ImageView iv_title,main_me,main_mg;
    private FrameLayout flRootContainer;
    private TitleBarTool mBarTool;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, this.getClass().getSimpleName() + " call onCreate");
        Log.i(TAG, this.getClass().getSimpleName() + " call onCreate()");
        init();
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mRootView) {
            Log.i(TAG, this.getClass().getSimpleName() + " call onCreateView use old instance");
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        } else {
            Log.i(TAG, this.getClass().getSimpleName() + " call onCreateView()");
            mRootView = inflater.inflate(R.layout.fragment_base, container, false);
            findViews(mRootView);
        }
        mRootView = inflater.inflate(R.layout.fragment_base, container, false);
        findViews(mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View contentView = getContentView();
        setContentView(contentView);
        initContentView(contentView);
        initListener();
        isViewCreated = true;
        lazyLoad();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, this.getClass().getSimpleName() + " call onDestroyView");
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, this.getClass().getSimpleName() + " call onDestroy");
//        RefWatcher refWatcher = AppInstance.getRefWatcher();
//        refWatcher.watch(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.v(TAG, this.getClass().getSimpleName() + " isVisible:  " + isVisibleToUser);
        //Fragment第一次对用户可见时还未完成视图的创建
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
            if (isViewCreated) {
                onFragmentInVisibleToUser();
            }
        }
    }

    private void lazyLoad() {
        Log.i(TAG, this.getClass().getSimpleName() + "  is ViewCreated:  " + isViewCreated + " &" + "  is UIVisible:  " + isUIVisible);
        if (isViewCreated && isUIVisible) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                initDataWithPermission();
            } else {
                initData();
            }
            //数据加载完毕,恢复标记,防止重复加载
//            isViewCreated = false;
            isUIVisible = false;
            Log.i(TAG, this.getClass().getSimpleName() + " call lazyLoad()");
        }
    }

    private void findViews(View rootView) {
        rlBasePageTitle = (RelativeLayout) rootView.findViewById(R.id.rl_base_page_title);
        rl_pb =  rootView.findViewById(R.id.rl_pb);
        tvBaseTitleText = (TextView) rootView.findViewById(R.id.tv_base_title_text);
        iv_title = (ImageView) rootView.findViewById(R.id.iv_title);
        main_me = (ImageView) rootView.findViewById(R.id.main_me);
        main_mg = (ImageView) rootView.findViewById(R.id.main_mg);
        flRootContainer = (FrameLayout) rootView.findViewById(R.id.fl_root_container);
        mBarTool = new TitleBarTool(rlBasePageTitle, tvBaseTitleText);
        tvBaseTitleText.setText(this.getClass().getSimpleName());
    }

    /**
     * 将子Fragment的主体视图的View添加到容器中
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:29
     **/
    private void setContentView(View contentView) {
        if (contentView != null) {
            flRootContainer.removeAllViews();
            flRootContainer.addView(contentView);
        }
    }
    /**
     * 子类实现的方法
     */
    /**
     * Fragment创建时可以做的操作
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:24
     **/
    protected void init() {
    }

    /**
     * 获取Fragment主体内容的View
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:24
     **/
    public abstract View getContentView();

    /**
     * Fragment主体内容的View做findViewById操作
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:25
     **/
    protected abstract void initContentView(View contentView);

    /**
     * 初始化控件的监听器
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:22
     **/
    protected void initListener() {
    }

    /**
     * 6.0以上的版本先获取权限，再调用initData
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:38
     **/
    private void initDataWithPermission() {
        Log.i(TAG, this.getClass().getSimpleName() + " call initDataWithPermission()");
        String[] permissions = getRequiredRuntimePermissions();
        Log.i(TAG, this.getClass().getSimpleName() + "initDataWithPermission:" + permissions[0]);
        PermissionUtils.requestPermissions(mActivity, new PermissionCallBack() {
            @Override
            protected void onAcpGranted() {
                initData();
            }
        }, permissions);
    }

    /**
     * 获取数据，当界面对用户可见时调用
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:21
     **/
    protected void initData() {
        Log.i(TAG, this.getClass().getSimpleName() + " call initData()");
    }

    /**
     * 当界面对用户不可见时调用，用于移除监听器，终止线程等操作
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:22
     **/
    protected void onFragmentInVisibleToUser() {
        Log.i(TAG, this.getClass().getSimpleName() + " call onFragmentInVisibleToUser()");
    }

    /**
     * 子类传入需要申请的权限列表
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 11:26
     **/
    public String[] getRequiredRuntimePermissions() {
        /**
         * 坑爹，这里至少要传一个权限字符串给Acp框架，否则报异常。
         * 不想自己写只能按他的来
         * */
        return new String[]{Manifest.permission.INTERNET};
    }

    /**
     * 标题栏控制器
     *
     * @author Administrator
     * @time 2018/4/11 0011 上午 10:23
     **/
    protected class TitleBarTool {
        private RelativeLayout titleBar;
        private TextView title;

        public TitleBarTool(RelativeLayout titleBar, TextView tvTitle) {
            this.titleBar = titleBar;
            this.title = tvTitle;
        }

        public TitleBarTool hideTitleBar() {
            titleBar.setVisibility(View.GONE);
            return this;
        }
        public RelativeLayout getTitleBarRl() {
            return titleBar;
        }

        public TitleBarTool hideIvTitleBar() {
            iv_title.setVisibility(View.GONE);
            return this;
        }

        public TitleBarTool hideMainMeTitleBar() {
            main_me.setVisibility(View.GONE);
            return this;
        }

        public TitleBarTool showMainMeTitleBar() {
            main_me.setVisibility(View.VISIBLE);
            return this;
        }

        public TitleBarTool hideMainMgTitleBar() {
            main_mg.setVisibility(View.GONE);
            return this;
        }

        public TitleBarTool showMainMgTitleBar() {
            main_mg.setVisibility(View.VISIBLE);
            return this;
        }
        public TitleBarTool showIvTitleBar() {
            iv_title.setVisibility(View.VISIBLE);
            return this;
        }



        public TitleBarTool setTitleBarBackground(int color) {
            titleBar.setBackgroundColor(color);
            return this;
        }

        public TitleBarTool setTitle(String titleText) {
            title.setText(titleText);
            return this;
        }
        public TitleBarTool setTitle(int resId) {
            title.setText(getString(resId));
            return this;
        }
        public TextView getTitle() {
            return title;
        }
        public ImageView getMainMe() {
            return main_me;
        }
        public ImageView getMainMg() {
            return main_mg;
        }
    }

    public TitleBarTool getBarTool() {
        return mBarTool;
    }

}
