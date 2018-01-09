package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

import ru.necessitudo.app.vk_alternative.consts.ApiConsts;

/**
 * Created by olegdubrovin on 09/01/18.
 */

public class GroupsGetByIdRequestModel extends BaseRequestModel {

    @SerializedName(VKApiConst.GROUP_ID)
    int groupid;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConsts.DEFAULT_GROUP_FIELDS;

    public int getGroupid() {
        return groupid;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public void setGroupid(int groupid) {

        this.groupid = Math.abs(groupid);
    }

    public GroupsGetByIdRequestModel(int groupid) {
        this.groupid = Math.abs(groupid) ;

    }

    @Override
    public void onMapCreate(Map<String, String> map) {

        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupid()));
        map.put(VKApiConst.FIELDS, String.valueOf(getFields()));

    }
}
