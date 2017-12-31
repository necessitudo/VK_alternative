package ru.necessitudo.app.vk_alternative.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.necessitudo.app.vk_alternative.model.Member;
import ru.necessitudo.app.vk_alternative.rest.model.response.BaseItemResponse;
import ru.necessitudo.app.vk_alternative.rest.model.response.Full;

/**
 * Created by olegdubrovin on 31/12/17.
 */

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);
}
