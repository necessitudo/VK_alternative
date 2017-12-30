package ru.necessitudo.app.vk_alternative.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.necessitudo.app.vk_alternative.common.manager.NetworkManager;
import ru.necessitudo.app.vk_alternative.di.module.ApplicationModule;
import ru.necessitudo.app.vk_alternative.di.module.ManagerModule;
import ru.necessitudo.app.vk_alternative.di.module.RestModule;
import ru.necessitudo.app.vk_alternative.mvp.presenter.MainPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.NewsFeedPresenter;
import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.ui.activity.MainActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.NewsFeedFragment;
import ru.necessitudo.app.vk_alternative.ui.holder.NewsItemBodyHolder;
import ru.necessitudo.app.vk_alternative.ui.holder.NewsItemFooterHolder;

/**
 * Created by olegdubrovin on 06/12/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    void  inject(BaseActivity activity);

    void  inject(MainActivity activity);


    void inject(NewsFeedFragment fragment);

    void inject(NewsItemBodyHolder holder);

    void inject(NewsItemFooterHolder holder);

    void inject(NewsFeedPresenter presenter);

    void inject(NetworkManager manager);

    void inject(MainPresenter presenter);




}
