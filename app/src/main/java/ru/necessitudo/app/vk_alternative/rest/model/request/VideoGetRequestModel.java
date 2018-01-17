package ru.necessitudo.app.vk_alternative.rest.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import ru.necessitudo.app.vk_alternative.consts.ApiConsts;

/**
 * Created by olegdubrovin on 15/01/18.
 */

public class VideoGetRequestModel  extends  BaseRequestModel{

    @SerializedName(ApiConsts.VIDEOS)
    String videos;


    public VideoGetRequestModel() {

    }

    public VideoGetRequestModel(String ownerId, String videoId) {
        this.videos = ownerId + "_"+videoId;
    }

    public VideoGetRequestModel(int ownerId, int videoId) {
        this.videos = ownerId + "_"+videoId;
    }

    public VideoGetRequestModel(String videos) {
        this.videos = videos;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConsts.VIDEOS, getVideos());

    }
}
