package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;

/**
 * Created by olegdubrovin on 05/12/17.
 */

public abstract  class BaseFragment extends MvpAppCompatFragment{

    @LayoutRes
    protected abstract  int getMainContentLayout();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getMainContentLayout(), container, false);
    }

    public  String createToolbarTitle(Context context){

        return  context.getString(onCreateToolbarTitle());

    }

    @StringRes
    public  abstract int onCreateToolbarTitle();

    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();

    }
}
