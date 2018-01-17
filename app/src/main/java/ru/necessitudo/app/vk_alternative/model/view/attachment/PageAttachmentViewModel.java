package ru.necessitudo.app.vk_alternative.model.view.attachment;

import android.view.View;

import ru.necessitudo.app.vk_alternative.model.attachment.Page;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.ui.view.holder.attachment.PageAttachmentHolder;

/**
 * Created by olegdubrovin on 17/01/18.
 */

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    protected PageAttachmentHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }


    public String getmUrl() {
        return mUrl;
    }
}