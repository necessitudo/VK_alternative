package ru.necessitudo.app.vk_alternative.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.BaseAdapter;
import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.model.view.NewsFeedItemBodyViewModel;
import ru.necessitudo.app.vk_alternative.rest.api.WallApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.WallGetRequestModel;
import ru.necessitudo.app.vk_alternative.rest.model.response.WallGetResponce;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi mWallApi;

    RecyclerView mRecyclerView;

    BaseAdapter mBaseAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);
    }

    public NewsFeedFragment() {
        // Required empty public constructor
     }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<WallGetResponce>() {
            @Override
            public void onResponse(Call<WallGetResponce> call, Response<WallGetResponce> response) {
                List<NewsFeedItemBodyViewModel> list = new ArrayList<>();
                for (WallItem item:response.body().response.getItems()){
                    list.add(new NewsFeedItemBodyViewModel(item));
                }
                mBaseAdapter.addItems(list);
                Toast.makeText(getActivity(), "Likes: "+ response.body().response.getItems().get(0).getLikes().getCount(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<WallGetResponce> call, Throwable t) {
                t.printStackTrace();

            }
        });
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
