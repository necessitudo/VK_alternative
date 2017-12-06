package ru.necessitudo.app.vk_alternative.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olegdubrovin on 06/12/17.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application){
        mApplication = application;

    }

    @Singleton
    @Provides
    public Context provideContext(){

        return  mApplication;

    }

}
