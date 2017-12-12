package ru.necessitudo.app.vk_alternative.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.necessitudo.app.vk_alternative.rest.RestClient;
import ru.necessitudo.app.vk_alternative.rest.api.WallApi;

/**
 * Created by olegdubrovin on 06/12/17.
 */

@Module
public class RestModule {

    private RestClient mRestClient;

    public RestModule(){
        mRestClient = new RestClient();

    }

    @Singleton
    @Provides
    public  RestClient provideRestClient(){
        return  mRestClient;

    }

    @Singleton
    @Provides
    public WallApi provideWallApi(){
        return mRestClient.createService(WallApi.class);

    }
}