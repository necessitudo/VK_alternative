package ru.necessitudo.app.vk_alternative.model.view;

import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.R;
import ru.necessitudo.app.vk_alternative.common.manager.MyFragmentManager;
import ru.necessitudo.app.vk_alternative.model.Place;
import ru.necessitudo.app.vk_alternative.model.Topic;
import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.TopicCommentsFragment;
import ru.necessitudo.app.vk_alternative.ui.view.holder.BaseViewHolder;

/**
 * Created by olegdubrovin on 04/01/18.
 */

public class TopicViewModel extends BaseViewModel {

    private int mId;
    private int mGroupId;
    private String mTitle;
    private String mCommentsCount;


    public TopicViewModel() {
    }

    public TopicViewModel(Topic topic) {
        this.mId = topic.getId();
        this.mGroupId = topic.getGroupid();
        this.mTitle = topic.getTitle();
        this.mCommentsCount = topic.getComments() + " сообщений";
    }

    @Override
    public LayoutTypes getType() {

        return LayoutTypes.Topic;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmGroupId() {
        return mGroupId;
    }

    public void setmGroupId(int mGroupId) {
        this.mGroupId = mGroupId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmCommentsCount() {
        return mCommentsCount;
    }

    public void setmCommentsCount(String mCommentsCount) {
        this.mCommentsCount = mCommentsCount;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);

    }

    public static class TopicViewHolder extends  BaseViewHolder<TopicViewModel>{

        @BindView(R.id.tv_title)
        public TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        public TextView tvCommentsCount;

        @Inject
        MyFragmentManager mFragmentManager;


        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            MyApplication.getApplicationComponent().inject(this);

        }

        @Override
        public void bindViewHolder(TopicViewModel model) {
            tvTitle.setText(model.getmTitle());
            tvCommentsCount.setText(model.getmCommentsCount());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) view.getContext(),
                            TopicCommentsFragment.newInstance(new Place(String.valueOf(model.getmGroupId()), String.valueOf(model.getmId()))),
                            R.id.main_wrapper);
                }
            });
        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvCommentsCount.setText(null);

        }
    }

}
