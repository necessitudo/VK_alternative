package ru.necessitudo.app.vk_alternative.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemBodyViewModel;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public class NewsItemBodyHolder extends  BaseViewHolder<NewsItemBodyViewModel>{

    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.tv_attachments)
    TextView tvAttachments;

    @Inject
    protected Typeface mFontGoogle;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        MyApplication.getApplicationComponent().inject(this);

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
