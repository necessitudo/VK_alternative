package ru.necessitudo.app.vk_alternative.rest.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegdubrovin on 07/12/17.
 */

public class BaseItemResponse<T> {

    public Integer count;

    public List<T> item = new ArrayList<>();


    public Integer getCount() {
        return count;
    }

    public List<T> getItem() {
        return item;
    }
}
