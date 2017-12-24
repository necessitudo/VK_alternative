package ru.necessitudo.app.vk_alternative.ui.holder;

import android.view.View;
import android.widget.TextView;
import android.graphics.Typeface;

import javax.inject.Inject;

import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemBodyViewModel;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public class NewsItemBodyHolder extends  BaseViewHolder<NewsItemBodyViewModel>{

    public TextView tvText;

    private TextView tvAttachments;

    @Inject
    protected Typeface mFontGoogle;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        MyApplication.getApplicationComponent().inject(this);

        tvText  = itemView.findViewById(R.id.tv_text);
        tvAttachments = itemView.findViewById(R.id.tv_attachments);

        if (tvAttachments!= null){
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        tvText.setText(item.getmText());
        tvAttachments.setText(item.getmAttachmentsString());

    }

    @Override
    public void unbindViewHolder() {
        tvText.setText(null);
        tvAttachments.setText(null);
    }
}
