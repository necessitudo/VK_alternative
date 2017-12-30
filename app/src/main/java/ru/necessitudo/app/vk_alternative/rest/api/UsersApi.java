package ru.necessitudo.app.vk_alternative.rest.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.necessitudo.app.vk_alternative.model.Profile;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;

/**
 * Created by olegdubrovin on 30/12/17.
 */

public interface UsersApi {

    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);
}
