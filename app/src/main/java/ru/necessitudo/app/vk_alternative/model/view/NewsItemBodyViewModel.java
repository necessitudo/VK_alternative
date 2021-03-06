package ru.necessitudo.app.vk_alternative.model.view;

import android.view.View;

import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.ui.view.holder.BaseViewHolder;
import ru.necessitudo.app.vk_alternative.ui.view.holder.NewsItemBodyHolder;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public class NewsItemBodyViewModel extends BaseViewModel {

    private int mId;

    private String mText;

    private String mAttachmentsString;

    public String getAttachmentsString() {
        return mAttachmentsString;
    }

    public void setmAttachmentsString(String mAttachmentsString) {
        this.mAttachmentsString = mAttachmentsString;
    }

    private boolean mIsRepost;


    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();

        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost){
            this.mText = wallItem.getSharedRepost().getText();
            this.mAttachmentsString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            this.mText = wallItem.getText();
            this.mAttachmentsString = wallItem.getAttachmentsString();
        }
    }

    public int getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
