package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.mvp.presenter.BaseFeedPresenter;
import ru.necessitudo.app.vk_alternative.mvp.presenter.InfoContactsPresenter;

/**
 * Created by olegdubrovin on 24/01/18.
 */

public class InfoContactsFragment extends  BaseFeedFragment {

    @InjectPresenter
    InfoContactsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.title_contacts;
    }
}

