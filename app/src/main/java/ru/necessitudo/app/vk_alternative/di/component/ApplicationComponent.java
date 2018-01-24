package ru.necessitudo.app.vk_alternative.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.necessitudo.app.vk_alternative.common.manager.NetworkManager;
import ru.necessitudo.app.vk_alternative.di.module.ApplicationModule;
import ru.necessitudo.app.vk_alternative.di.module.ManagerModule;
import ru.necessitudo.app.vk_alternative.di.module.RestModule;
import ru.necessitudo.app.vk_alternative.model.view.CommentBodyViewModel;
import ru.necessitudo.app.vk_alternative.model.view.CommentFooterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.TopicViewModel;
import ru.necessitudo.app.vk_alternative.mvp.presenter.BoardPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.CommentsPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.InfoContactsPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.InfoLinksPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.InfoPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.MainPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.MembersPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.NewsFeedPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.OpenedCommentPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.OpenedPostPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.TopicCommentsPresenter;
import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.ui.activity.MainActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.CommentsFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.InfoContactsFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.InfoLinksFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.NewsFeedFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.OpenedCommentFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.OpenedPostFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.TopicCommentsFragment;
import ru.necessitudo.app.vk_alternative.ui.view.holder.NewsItemBodyHolder;
import ru.necessitudo.app.vk_alternative.ui.view.holder.NewsItemFooterHolder;
import ru.necessitudo.app.vk_alternative.ui.view.holder.attachment.ImageAttachmentHolder;
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

    void inject(CommentsFragment fragment);

    void inject(OpenedCommentFragment fragment);

    void inject(TopicCommentsFragment fragment);

    void inject(NewsItemBodyHolder holder);

    void inject(NewsItemFooterHolder holder);

    void inject(NewsFeedPresenter presenter);

    void inject(NetworkManager manager);

    void inject(MainPresenter presenter);

    void inject(MembersPresenter presenter);

    void inject(BoardPresenter presenter);

    void inject(OpenedCommentPresenter presenter);

    void inject(TopicCommentsPresenter presenter);

    void inject(InfoPresenter presenter);

    void inject(CommentsPresenter presenter);

    void inject(OpenedPostPresenter presenter);

    void inject(ImageAttachmentHolder holder);

    void inject(VideoAttachmentHolder holder);

    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);

    void inject(CommentFooterViewModel.CommentFooterHolder holder);

    void inject(TopicViewModel.TopicViewHolder holder);

    void inject(InfoContactsPresenter presenter);
    void inject(InfoLinksPresenter presenter);

    void inject(InfoContactsFragment fragment);
    void inject(InfoLinksFragment fragment);

}
