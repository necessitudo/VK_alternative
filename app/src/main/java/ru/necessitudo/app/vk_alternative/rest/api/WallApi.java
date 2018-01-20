package ru.necessitudo.app.vk_alternative.rest.api;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.necessitudo.app.vk_alternative.model.CommentItem;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;
import ru.necessitudo.app.vk_alternative.rest.model.response.GetWallByIDResponse;
import ru.necessitudo.app.vk_alternative.rest.model.response.ItemsWithSendersResponse;
import ru.necessitudo.app.vk_alternative.rest.model.response.WallGetResponse;

/**
 * Created by olegdubrovin on 07/12/17.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<WallGetResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIDResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemsWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
