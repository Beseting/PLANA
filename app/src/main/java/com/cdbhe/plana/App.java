package com.cdbhe.plana;

import android.app.Application;

import com.cdbhe.plib.http.retrofit.HttpConfig;
import com.cdbhe.plib.http.retrofit.RetrofitClient;
import com.cdbhe.plib.utils.AppUtils;
import com.cdbhe.plib.utils.LogUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Kevin on 2018/3/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化Bugly
        String processName = AppUtils.getProcessName(android.os.Process.myPid());// 获取当前进程名
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());// 设置是否为上报进程
        strategy.setUploadProcess(processName == null || processName.equals(getApplicationContext().getPackageName()));
        CrashReport.initCrashReport(getApplicationContext(), "420f93d2c2", true, strategy);

        //初始化Retrofit
        RetrofitClient.getInstance().init(new HttpConfig.Builder().setBaseUrl("https://restapi.amap.com/").build());
    }
}
