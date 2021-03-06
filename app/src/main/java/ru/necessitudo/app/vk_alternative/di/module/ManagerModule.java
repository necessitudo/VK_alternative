package ru.necessitudo.app.vk_alternative.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.necessitudo.app.vk_alternative.common.manager.MyFragmentManager;
import ru.necessitudo.app.vk_alternative.common.manager.NetworkManager;

/**
 * Created by olegdubrovin on 06/12/17.
 */

@Module
public class ManagerModule  {

    @Singleton
    @Provides
    MyFragmentManager provideMyFragmentManager(){
        return new MyFragmentManager();

    }


    @Singleton
    @Provides
    NetworkManager provideNetworkManager(){
        return new NetworkManager();

    }
}
