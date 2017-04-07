package com.gfd.city.app;

import android.app.Application;

/**
 * 类描述：
 * 作者：郭富东
 * 创建日期： 2017/3/24 0024 下午 4:18
 * 更新日期：
 */
public class App extends Application{

    public static App appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
