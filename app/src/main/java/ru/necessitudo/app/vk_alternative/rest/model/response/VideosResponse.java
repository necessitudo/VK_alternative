package ru.necessitudo.app.vk_alternative.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.necessitudo.app.vk_alternative.model.attachment.video.Video;

/**
 * Created by olegdubrovin on 17/01/18.
 */

public class VideosResponse {
    public int count;

    @SerializedName("items")
    @Expose
    public List<Video> items;

}