package com.outsourcing.llxpb.util;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by DELL on 2018/7/23.
 */

public class StatusBar {
    public static void transportStatus(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (!isHaveNavigationBar(context))
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    public static boolean isHaveNavigationBar(Activity context) {

        boolean isHave = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            isHave = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                isHave = false;
            } else if ("0".equals(navBarOverride)) {
                isHave = true;
            }
        } catch (Exception e) {
            Log.w("TAG", e);
        }
        return isHave;
    }
}
