package com.outsourcing.llxpb.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public abstract class BaseActivity extends AppCompatActivity implements I_SkipActivity {
    // 再点一次退出程序时间设置
    protected Activity mActivity;
    private String TAG = BaseActivity.this.getClass().getName();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        AppManager.getAppManager().addActivity(this);
        Log.i(TAG,this.getClass().getName()+"-----onCreate()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,this.getClass().getName()+"-----onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,this.getClass().getName()+"-----onResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,this.getClass().getName()+"-----onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,this.getClass().getName()+"-----onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,this.getClass().getName()+"-----onDestroy()");
//        RefWatcher refWatcher = AppInstance.getRefWatcher();
//        refWatcher.watch(this);
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }
}
