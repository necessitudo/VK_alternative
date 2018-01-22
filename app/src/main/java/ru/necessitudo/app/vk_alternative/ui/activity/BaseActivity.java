package ru.necessitudo.app.vk_alternative.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.manager.MyFragmentManager;
import ru.necessitudo.app.vk_alternative.ui.fragment.BaseFragment;

/**
 * Created by olegdubrovin on 05/12/17.
 */

public abstract  class BaseActivity extends MvpAppCompatActivity{

    @BindView(R.id.progress)
    protected ProgressBar mProgressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    @Inject
    MyFragmentManager myFragmentManager;

    public void fragmentOnScreen(BaseFragment fragment){
        setToolbarTitle(fragment.createToolbarTitle(this));
        setupFabVisibility(fragment.needFab());

    }

    @LayoutRes
    protected abstract  int getMainContentLayout();

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        MyApplication.getApplicationComponent().inject(this);

        setSupportActionBar(toolbar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.main_wrapper);

        getLayoutInflater().inflate(getMainContentLayout(), parent);


    }

    public  void setToolbarTitle(String title){
        if(getSupportActionBar() != null){

            getSupportActionBar().setTitle(title);

        }
    }

    public  void setContent(BaseFragment fragment){

        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);

    }

    public  void addContent(BaseFragment fragment){

        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);

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

    public void setupFabVisibility(boolean needFab) {
        if (mFab == null) return;

        if (needFab) {
            mFab.show();
        } else {
            mFab.hide();
        }
    }


}
