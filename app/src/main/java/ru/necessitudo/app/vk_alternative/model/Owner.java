package ru.necessitudo.app.vk_alternative.model;

/**
 * Created by olegdubrovin on 18/12/17.
 */

import com.vk.sdk.api.model.Identifiable;

public interface Owner extends  Identifiable{

    String getFullName();
    String getPhoto();

    int getId();
}
