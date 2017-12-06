package ru.necessitudo.app.vk_alternative.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by olegdubrovin on 06/12/17.
 */

public class Full<T> {

    @SerializedName("response")
    @Expose
    public T response;
}
