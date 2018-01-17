package ru.necessitudo.app.vk_alternative.model.view.attachment;

import android.view.View;

import ru.necessitudo.app.vk_alternative.model.attachment.Photo;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.ui.view.holder.attachment.ImageAttachmentHolder;

/**
 * Created by olegdubrovin on 16/01/18.
 */

public class ImageAttachmentViewModel extends BaseViewModel{

    private String mPhotoUrl;
    public boolean needClick = true;

    public ImageAttachmentViewModel(String url) {

         mPhotoUrl = url;
        needClick = false;
    }

    public ImageAttachmentViewModel(Photo photo) {
        mPhotoUrl = photo.getPhoto604();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    protected ImageAttachmentHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
