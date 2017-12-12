package ru.necessitudo.app.vk_alternative.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import model.WallItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.necessitudo.app.vk_alternative.CurrentUser;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.rest.api.WallApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.WallGetRequestModel;
import ru.necessitudo.app.vk_alternative.rest.model.response.BaseItemResponse;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;
import ru.necessitudo.app.vk_alternative.rest.model.response.WallGetResponce;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFragment {

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

        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<WallGetResponce>() {
            @Override
            public void onResponse(Call<WallGetResponce> call, Response<WallGetResponce> response) {
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

}
