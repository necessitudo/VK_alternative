package ru.necessitudo.app.vk_alternative.mvp.view;

import com.arellomobile.mvp.MvpView;

import ru.necessitudo.app.vk_alternative.model.Profile;

/**
 * Created by olegdubrovin on 04/12/17.
 */

public interface MainView extends MvpView {

    void startSignIn();

    void signedId();

    void showCurrentUser(Profile profile);

}
