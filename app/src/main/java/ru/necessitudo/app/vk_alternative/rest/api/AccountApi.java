package ru.necessitudo.app.vk_alternative.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;

/**
 * Created by olegdubrovin on 23/01/18.
 */

public interface AccountApi {
    @GET(ApiMethods.ACCOUNT_REGISTER_DEVICE)
    Observable<Full<Integer>> registerDevice(@QueryMap Map<String, String> map);
}
