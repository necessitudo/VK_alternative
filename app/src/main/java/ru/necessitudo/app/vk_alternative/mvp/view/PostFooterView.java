package ru.necessitudo.app.vk_alternative.mvp.view;

import com.arellomobile.mvp.MvpView;

import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.model.view.counter.LikeCounterViewModel;

/**
 * Created by olegdubrovin on 20/01/18.
 */

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
