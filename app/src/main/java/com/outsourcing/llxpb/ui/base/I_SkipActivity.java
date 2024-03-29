package com.outsourcing.llxpb.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

/**
 * 规范Activity跳转的接口协议
 *
 * @author Administrator
 */
public interface I_SkipActivity {
    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls);

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Intent it);

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Intent it);

    /**
     * show a @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls, Bundle extras);
}
