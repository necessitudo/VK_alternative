package ru.necessitudo.app.vk_alternative.rest.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegdubrovin on 07/12/17.
 */

public class BaseItemResponse<T> {

    public Integer count;

    public List<T> items = new ArrayList<>();


    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }
}
