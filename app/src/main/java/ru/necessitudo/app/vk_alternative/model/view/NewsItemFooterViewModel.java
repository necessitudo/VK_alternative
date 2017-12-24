package ru.necessitudo.app.vk_alternative.model.view;

import android.view.View;

import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.model.view.counter.CommentCounterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.LikeCounterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.counter.RepostCounterViewModel;
import ru.necessitudo.app.vk_alternative.ui.holder.BaseViewHolder;
import ru.necessitudo.app.vk_alternative.ui.holder.NewsItemFooterHolder;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class NewsItemFooterViewModel extends  BaseViewModel{

    private int mId;
    private int ownerId;

    private LikeCounterViewModel mLikes;
    private RepostCounterViewModel mReposts;
    private CommentCounterViewModel mComments;
    private long mDateLong;


    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public void setLikes(LikeCounterViewModel mLikes) {
        this.mLikes = mLikes;
    }

    public RepostCounterViewModel getReposts() {
        return mReposts;
    }

    public void setReposts(RepostCounterViewModel mReposts) {
        this.mReposts = mReposts;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public void setComments(CommentCounterViewModel mComments) {
        this.mComments = mComments;
    }

    public long getDateLong() {
        return mDateLong;
    }

    public void setDateLong(long mDateLong) {
        this.mDateLong = mDateLong;
    }

    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.mDateLong = wallItem.getDate();

        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mReposts = new RepostCounterViewModel(wallItem.getReposts());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());



    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;

    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }
}
