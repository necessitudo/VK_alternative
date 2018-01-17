package ru.necessitudo.app.vk_alternative.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;
import ru.necessitudo.app.vk_alternative.rest.model.response.VideosResponse;

/**
 * Created by olegdubrovin on 11/01/18.
 */

public interface VideoApi  {
    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}
