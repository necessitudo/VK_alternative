package ru.necessitudo.app.vk_alternative.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.necessitudo.app.vk_alternative.rest.RestClient;

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
}
