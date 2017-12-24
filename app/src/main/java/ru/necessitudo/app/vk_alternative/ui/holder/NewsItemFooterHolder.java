package ru.necessitudo.app.vk_alternative.ui.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.utils.Utils;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemFooterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.CommentCounterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.LikeCounterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.RepostCounterViewModel;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

    private TextView tvDate;

    private TextView tvLikesCount;
    private TextView tvLikesIcon;

    private TextView tvCommentsCount;
    private TextView tvCommentsIcon;

    private TextView tvRepostsCount;
    private TextView tvRepostsIcon;

    @Inject
    Typeface mGoogleFontTypeface;

    private Resources mResources;
    private Context mContext;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        MyApplication.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();

        tvDate = itemView.findViewById(R.id.tv_date);

        tvLikesCount = itemView.findViewById(R.id.tv_likes_count);
        tvLikesIcon = itemView.findViewById(R.id.tv_likes_icon);

        tvRepostsCount = itemView.findViewById(R.id.tv_reposts_count);
        tvRepostsIcon = itemView.findViewById(R.id.tv_reposts_icon);

        tvCommentsCount = itemView.findViewById(R.id.tv_comments_count);
        tvCommentsIcon = itemView.findViewById(R.id.tv_comments_icon);

        tvLikesIcon.setTypeface(mGoogleFontTypeface);
        tvRepostsIcon.setTypeface(mGoogleFontTypeface);
        tvCommentsIcon.setTypeface(mGoogleFontTypeface);


    }

    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

        bindLikes(item.getLikes());
        bindComment(item.getComments());
        bindReposts(item.getReposts());


    }

    private void bindLikes(LikeCounterViewModel likes){
        tvLikesCount.setText(String.valueOf(likes.getCount()));
        tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
        tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));
    }

    private void bindComment(CommentCounterViewModel comments){
        tvCommentsCount.setText(String.valueOf(comments.getCount()));
        tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
        tvCommentsIcon.setTextColor(mResources.getColor(comments.getIconColor()));
    }

    private void bindReposts(RepostCounterViewModel reposts){
        tvRepostsCount.setText(String.valueOf(reposts.getCount()));
        tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
        tvRepostsIcon.setTextColor(mResources.getColor(reposts.getIconColor()));
    }



    @Override
    public void unbindViewHolder() {

        tvDate.setText(null);
        tvLikesCount.setText(null);
        tvCommentsCount.setText(null);
        tvRepostsCount.setText(null);


    }
}
