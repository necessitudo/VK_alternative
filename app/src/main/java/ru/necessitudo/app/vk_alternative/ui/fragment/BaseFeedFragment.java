package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.BaseAdapter;
import ru.necessitudo.app.vk_alternative.common.manager.MyLinearLayoutManager;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.mvp.presenter.BaseFeedPresenter;
import ru.necessitudo.app.vk_alternative.mvp.view.BaseFeedView;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView{

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    BaseAdapter mBaseAdapter;

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected ProgressBar mProgressBar;

    protected BaseFeedPresenter<BaseFeedView> mBaseFeedPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        setUpSwipeToRefreshLayout(view);

        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);

        mBaseFeedPresenter = onCreateFeedPresenter();
        mBaseFeedPresenter.loadStart();


    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    private void setUpRecyclerView(View rootView){

        MyLinearLayoutManager mLinearLayoutManager = new MyLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mLinearLayoutManager.isOnNextPagePosition()){
                    mBaseFeedPresenter.loadNext(mBaseAdapter.getRealItemCount());
                }
            }
        });

        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void setUpAdapter(RecyclerView recyclerView){
        mBaseAdapter = new BaseAdapter();

        recyclerView.setAdapter(mBaseAdapter);

    }

    private void setUpSwipeToRefreshLayout(View rootView){

       mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
       mSwipeRefreshLayout.setOnRefreshListener(()->onCreateFeedPresenter().loadRefresh());
       mProgressBar = getBaseActivity().getProgressBar();
    }

    @Override
    public void showRefreshing() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showListProgress() {
       mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
       mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setItems(List<BaseViewModel> items) {
        mBaseAdapter.setItems(items);

    }

    @Override
    public void addItems(List<BaseViewModel> items) {
        mBaseAdapter.addItems(items);

    }

    protected abstract BaseFeedPresenter onCreateFeedPresenter();
}
