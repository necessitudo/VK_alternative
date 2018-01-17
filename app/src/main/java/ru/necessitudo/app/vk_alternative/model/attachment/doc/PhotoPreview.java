package ru.necessitudo.app.vk_alternative.model.attachment.doc;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by olegdubrovin on 11/01/18.
 */

public class PhotoPreview extends RealmObject {

    RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<Size> sizes) {
        this.sizes = sizes;
    }


}
