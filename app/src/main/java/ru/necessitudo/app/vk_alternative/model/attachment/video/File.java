package ru.necessitudo.app.vk_alternative.model.attachment.video;

import io.realm.RealmObject;

/**
 * Created by olegdubrovin on 15/01/18.
 */

public class File  extends RealmObject{

    public String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
