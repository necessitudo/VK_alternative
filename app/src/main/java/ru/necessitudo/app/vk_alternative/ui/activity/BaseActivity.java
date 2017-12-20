package ru.necessitudo.app.vk_alternative.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.manager.MyFragmentManager;
import ru.necessitudo.app.vk_alternative.ui.fragment.BaseFragment;

/**
 * Created by olegdubrovin on 05/12/17.
 */

public abstract  class BaseActivity extends MvpAppCompatActivity{

    @Inject
    MyFragmentManager myFragmentManager;

    public void fragmentOnScreen(BaseFragment fragment){
        setToolbarTitle(fragment.createToolbarTitle(this));

    }

    @LayoutRes
    protected abstract  int getMainContentLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.main_wraper);

        getLayoutInflater().inflate(getMainContentLayout(), parent);


    }

    public  void setToolbarTitle(String title){
        if(getSupportActionBar() != null){

            getSupportActionBar().setTitle(title);

        }
    }

    public  void setContent(BaseFragment fragment){

        myFragmentManager.setFragment(this, fragment, R.id.main_wraper);

    }

    public  void addContent(BaseFragment fragment){

        myFragmentManager.addFragment(this, fragment, R.id.main_wraper);

    }

    public  boolean removeCurrentFragment(){

        return myFragmentManager.removeCurrentFragment(this);

    }


    public  boolean removeFragment(BaseFragment fragment){

        return myFragmentManager.removeFragment(this, fragment);

    }

    @Override
    public void onBackPressed() {

        removeCurrentFragment();
    }
}
