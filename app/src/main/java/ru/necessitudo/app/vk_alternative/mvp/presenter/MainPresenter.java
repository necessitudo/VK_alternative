package ru.necessitudo.app.vk_alternative.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.necessitudo.app.vk_alternative.CurrentUser;
import ru.necessitudo.app.vk_alternative.mvp.view.MainView;

/**
 * Created by olegdubrovin on 04/12/17.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>{

    public  void checkAuth() {
        if (!CurrentUser.isAutorized()){
            getViewState().startSignIn();
        } else {
            getViewState().signedId();
        }
    }
}
