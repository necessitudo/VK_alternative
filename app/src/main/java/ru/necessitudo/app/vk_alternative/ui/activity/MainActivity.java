package ru.necessitudo.app.vk_alternative.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import ru.necessitudo.app.vk_alternative.CurrentUser;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.consts.ApiConsts;
import ru.necessitudo.app.vk_alternative.mvp.presenter.MainPresenter;
import ru.necessitudo.app.vk_alternative.mvp.view.MainView;
import ru.necessitudo.app.vk_alternative.ui.fragment.BaseFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.NewsFeedFragment;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);

       mPresenter.checkAuth();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                mPresenter.checkAuth();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        }))
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSignIn() {

        VKSdk.login(this, ApiConsts.DEFAULT_LOGIN_SCOPE);

    }

    @Override
    public void signedId() {

        Toast.makeText(this, "Current user is: "+ CurrentUser.getId(), Toast.LENGTH_SHORT).show();
        BaseFragment mNewsFeedFragment = new NewsFeedFragment();

        setContent(new NewsFeedFragment());
    }
}
