package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import ru.necessitudo.app.vk_alternative.CurrentUser;
import ru.necessitudo.app.vk_alternative.consts.ApiConsts;

/**
 * Created by olegdubrovin on 11/12/17.
 */

public class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConsts.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();

    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }


}
