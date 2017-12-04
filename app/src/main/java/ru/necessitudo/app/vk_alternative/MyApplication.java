package ru.necessitudo.app.vk_alternative;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by olegdubrovin on 04/12/17.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
