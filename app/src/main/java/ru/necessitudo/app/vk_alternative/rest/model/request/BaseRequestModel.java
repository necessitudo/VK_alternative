package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

import ru.necessitudo.app.vk_alternative.CurrentUser;
import ru.necessitudo.app.vk_alternative.consts.ApiConstants;

/**
 * Created by olegdubrovin on 11/12/17.
 */

public abstract class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConstants.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();

    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }

     public Map<String, String> toMap(){

         Map<String, String> map = new HashMap<>();

         map.put(VKApiConst.VERSION, String.valueOf(getVersion()));

         if(accessToken!=null){
             map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());

         }

         onMapCreate(map);
         return map;

     }

     public  abstract void onMapCreate(Map<String, String> map);


}
