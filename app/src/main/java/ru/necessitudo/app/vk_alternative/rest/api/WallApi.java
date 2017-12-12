package ru.necessitudo.app.vk_alternative.rest.api;

import java.util.Map;

import model.WallItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.necessitudo.app.vk_alternative.rest.model.response.BaseItemResponse;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;
import ru.necessitudo.app.vk_alternative.rest.model.response.WallGetResponce;

/**
 * Created by olegdubrovin on 07/12/17.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<WallGetResponce> get(@QueryMap Map<String, String> map);
}
