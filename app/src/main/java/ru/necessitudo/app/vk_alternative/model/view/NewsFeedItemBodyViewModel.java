package ru.necessitudo.app.vk_alternative.model.view;

import android.view.View;

import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.ui.holder.BaseViewHolder;
import ru.necessitudo.app.vk_alternative.ui.holder.NewsItemBodyHolder;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public class NewsFeedItemBodyViewModel extends BaseViewModel {

    private int mId;

    private String mText;

    public NewsFeedItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mText = wallItem.getText();
    }

    public int getmId() {
        return mId;
    }

    public String getmText() {
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
}
