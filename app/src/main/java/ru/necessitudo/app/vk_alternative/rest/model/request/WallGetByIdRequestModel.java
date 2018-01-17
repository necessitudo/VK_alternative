package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import ru.necessitudo.app.vk_alternative.consts.ApiConsts;

/**
 * Created by olegdubrovin on 11/01/18.
 */

public class WallGetByIdRequestModel extends BaseRequestModel{

    @SerializedName(ApiConsts.POSTS)
    private String posts;

    @SerializedName(ApiConsts.EXTENDED)
    private int extended=1;

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }

    public WallGetByIdRequestModel(int ownerId, int postId) {
        this.posts = ownerId + "_" +postId;
    }
    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConsts.POSTS, getPosts());
        map.put(ApiConsts.EXTENDED, String.valueOf(getExtended()));

    }
}
