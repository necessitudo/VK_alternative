package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.BaseAdapter;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class BaseFeedFragment extends BaseFragment {


    RecyclerView mRecyclerView;

    BaseAdapter mBaseAdapter;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);
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
        mRecyclerView = rootView.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpAdapter(RecyclerView recyclerView){
        mBaseAdapter = new BaseAdapter();

        recyclerView.setAdapter(mBaseAdapter);

    }
}
