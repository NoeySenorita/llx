package com.outsourcing.llxpb;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;


import com.outsourcing.llxpb.model.Constants;
import com.outsourcing.llxpb.model.net.base.HttpAgent;
import com.outsourcing.llxpb.model.net.base.processor.OkgoProcessor;
import com.tencent.bugly.crashreport.CrashReport;
import com.xuexiang.xui.XUI;

import org.xutils.x;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AppInstance extends Application {
    private static Context context;

    public static Handler gethandler() {
        return mhandler;
    }

    public static void sethandler(Handler mhandler) {
        AppInstance.mhandler = mhandler;
    }

    private static Handler mhandler;
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mhandler = new Handler();
        OkgoProcessor iHttpProcessor = new OkgoProcessor();
        iHttpProcessor.init(this);
        HttpAgent.init(iHttpProcessor, Constants.NetConfig.BASE_URL);
        XUI.init(this);
        XUI.debug(true);
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "a2e8eb8a65", true, strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
