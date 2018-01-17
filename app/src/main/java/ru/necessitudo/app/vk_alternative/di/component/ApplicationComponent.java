package ru.necessitudo.app.vk_alternative.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.necessitudo.app.vk_alternative.common.manager.NetworkManager;
import ru.necessitudo.app.vk_alternative.di.module.ApplicationModule;
import ru.necessitudo.app.vk_alternative.di.module.ManagerModule;
import ru.necessitudo.app.vk_alternative.di.module.RestModule;
import ru.necessitudo.app.vk_alternative.mvp.presenter.BoardPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.InfoPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.MainPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.MembersPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.NewsFeedPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.OpenedPostPresenter;
import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.ui.activity.MainActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.NewsFeedFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.OpenedPostFragment;
import ru.necessitudo.app.vk_alternative.ui.view.holder.attachment.ImageAttachmentHolder;
import ru.necessitudo.app.vk_alternative.ui.view.holder.NewsItemBodyHolder;
import ru.necessitudo.app.vk_alternative.ui.view.holder.NewsItemFooterHolder;
import ru.necessitudo.app.vk_alternative.ui.view.holder.attachment.VideoAttachmentHolder;

/**
 * Created by olegdubrovin on 06/12/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    void  inject(BaseActivity activity);

    void  inject(MainActivity activity);


    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);

    void inject(NewsItemBodyHolder holder);

    void inject(NewsItemFooterHolder holder);

    void inject(NewsFeedPresenter presenter);

    void inject(NetworkManager manager);

    void inject(MainPresenter presenter);

    void inject(MembersPresenter presenter);

    void inject(BoardPresenter presenter);

    void inject(InfoPresenter presenter);

    void inject(OpenedPostPresenter presenter);

    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);


}
