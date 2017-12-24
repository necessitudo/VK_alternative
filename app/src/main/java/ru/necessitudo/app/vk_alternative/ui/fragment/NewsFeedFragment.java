package ru.necessitudo.app.vk_alternative.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.utils.VkListHelper;
import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.NewItemHeaderViewModel;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemBodyViewModel;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemFooterViewModel;
import ru.necessitudo.app.vk_alternative.rest.api.WallApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.WallGetRequestModel;
import ru.necessitudo.app.vk_alternative.rest.model.response.WallGetResponce;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;


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

        //-86529522

        mWallApi.get(new WallGetRequestModel(-47613684).toMap()).enqueue(new Callback<WallGetResponce>() {
            @Override
            public void onResponse(Call<WallGetResponce> call, Response<WallGetResponce> response) {
                List<WallItem> wallItems = VkListHelper.getWallList(response.body().response);
               List<BaseViewModel> list = new ArrayList<>();
               for (WallItem item:wallItems){
                   list.add(new NewItemHeaderViewModel(item));
                   list.add(new NewsItemBodyViewModel(item));
                   list.add(new NewsItemFooterViewModel(item));
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
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }



}
