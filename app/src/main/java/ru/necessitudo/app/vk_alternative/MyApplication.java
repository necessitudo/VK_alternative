package ru.necessitudo.app.vk_alternative;

import android.app.Application;

import com.vk.sdk.VKSdk;

import ru.necessitudo.app.vk_alternative.di.component.ApplicationComponent;
import ru.necessitudo.app.vk_alternative.di.component.DaggerApplicationComponent;
import ru.necessitudo.app.vk_alternative.di.module.ApplicationModule;

/**
 * Created by olegdubrovin on 04/12/17.
 */

public class MyApplication extends Application {

    private static ApplicationComponent sApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        VKSdk.initialize(this);
    }

    private void  initComponent(){
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

    }

    public  static  ApplicationComponent getApplicationComponent(){

        return sApplicationComponent;

    }
}
