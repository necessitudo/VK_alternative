package ru.necessitudo.app.vk_alternative.mvp.view;

import ru.necessitudo.app.vk_alternative.model.view.NewsItemFooterViewModel;

/**
 * Created by olegdubrovin on 17/01/18.
 */

public interface OpenedPostView  extends  BaseFeedView{

    void setFooter(NewsItemFooterViewModel viewModel);

}
