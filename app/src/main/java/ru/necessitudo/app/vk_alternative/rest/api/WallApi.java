package ru.necessitudo.app.vk_alternative.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.necessitudo.app.vk_alternative.rest.model.response.BaseItemResponse;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;

/**
 * Created by olegdubrovin on 07/12/17.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<Full<BaseItemResponse>> get(@Query("owner_id") String ownerId,
                                     @Query("access_token") String accessToken,
                                     @Query("extended") Integer extended,
                                     @Query("v") String version);
}
