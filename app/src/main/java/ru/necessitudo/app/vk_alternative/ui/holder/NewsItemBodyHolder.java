package ru.necessitudo.app.vk_alternative.ui.holder;

import android.view.View;
import android.widget.TextView;

import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.model.view.NewsFeedItemBodyViewModel;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public class NewsItemBodyHolder extends  BaseViewHolder<NewsFeedItemBodyViewModel>{

    public TextView mText;



    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        mText  = itemView.findViewById(R.id.tv_text);
    }

    @Override
    public void bindViewHolder(NewsFeedItemBodyViewModel newsFeedItemBody) {
        mText.setText(newsFeedItemBody.getmText());

    }

    @Override
    public void unbindViewHolder() {
        mText.setText(null);
    }
}
