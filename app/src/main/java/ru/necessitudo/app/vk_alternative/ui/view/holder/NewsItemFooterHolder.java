package ru.necessitudo.app.vk_alternative.ui.view.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.manager.MyFragmentManager;
import ru.necessitudo.app.vk_alternative.common.utils.Utils;
import ru.necessitudo.app.vk_alternative.model.Place;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemFooterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.CommentCounterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.LikeCounterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.RepostCounterViewModel;
import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.CommentsFragment;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_likes_count)
    TextView tvLikesCount;

    @BindView(R.id.tv_likes_icon)
    TextView tvLikesIcon;

    @BindView(R.id.tv_comments_count)
    TextView tvCommentsCount;

    @BindView(R.id.tv_comments_icon)
    TextView tvCommentsIcon;

    @BindView(R.id.tv_reposts_count)
    TextView tvRepostsCount;

    @BindView(R.id.tv_reposts_icon)
    TextView tvRepostsIcon;

    @Inject
    Typeface mGoogleFontTypeface;

    @BindView(R.id.rl_comments)
    public View rlComments;

    @Inject
    MyFragmentManager mFragmentManager;

    private Resources mResources;
    private Context mContext;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        MyApplication.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();

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

        rlComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.addFragment((BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(String.valueOf(item.getOwnerId()), String.valueOf(item.getId()))),
                        R.id.main_wraper);
            }
        });

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
