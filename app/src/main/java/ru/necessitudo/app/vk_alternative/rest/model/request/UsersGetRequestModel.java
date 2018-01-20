package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

import ru.necessitudo.app.vk_alternative.consts.ApiConstants;

/**
 * Created by olegdubrovin on 30/12/17.
 */

public class UsersGetRequestModel extends  BaseRequestModel{

    @SerializedName(VKApiConst.USER_IDS)
    String userId;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_USER_FIELDS;

    public UsersGetRequestModel(String userId){
        this.userId = userId;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getUserIds() {

        return userId;
    }

    public void setUserIds(String userIds) {
        this.userId = userIds;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {

        map.put(VKApiConst.USER_ID, getUserIds());
        map.put(VKApiConst.FIELDS, getFields());

    }
}
