package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

import ru.necessitudo.app.vk_alternative.consts.ApiConsts;

/**
 * Created by olegdubrovin on 31/12/17.
 */

public class GroupsGetMemberRequestModel extends BaseRequestModel {

    @SerializedName(VKApiConst.GROUP_ID)
    int groupId;

    @SerializedName(VKApiConst.COUNT)
    int count = ApiConsts.DEFAULT_COUNT;

    @SerializedName(VKApiConst.OFFSET)
    int offset;

    @SerializedName(VKApiConst.FIELDS)
    String fileds = ApiConsts.DEFAULT_MEMBER_FIELDS;

    public GroupsGetMemberRequestModel(int groupId, int count, int offset) {
        this.groupId = Math.abs(groupId);
        this.count = count;
        this.offset = offset;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getFileds() {
        return fileds;
    }

    public void setFileds(String fileds) {
        this.fileds = fileds;
    }

    public GroupsGetMemberRequestModel(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupId()));
        map.put(VKApiConst.COUNT, String.valueOf(getCount()));
        map.put(VKApiConst.OFFSET, String.valueOf(getOffset()));
        map.put(VKApiConst.FIELDS, String.valueOf(getFileds()));

    }
}
