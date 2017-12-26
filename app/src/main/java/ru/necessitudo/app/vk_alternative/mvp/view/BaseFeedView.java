package ru.necessitudo.app.vk_alternative.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;

/**
 * Created by olegdubrovin on 26/12/17.
 */

public interface BaseFeedView extends MvpView {

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void showError(String message);

    void setItems(List<BaseViewModel> items);

    void addItems(List<BaseViewModel> items);
}
