package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.os.Bundle;

import ru.necessitudo.app.vk_alternative.R;

/**
 * Created by olegdubrovin on 31/12/17.
 */

public class MyPostsFragment extends NewsFeedFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnabledIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }
}
