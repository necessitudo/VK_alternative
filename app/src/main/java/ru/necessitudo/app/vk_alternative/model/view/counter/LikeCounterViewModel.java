package ru.necessitudo.app.vk_alternative.model.view.counter;

import ru.necessitudo.app.vk_alternative.model.countable.Likes;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class LikeCounterViewModel extends CounterViewModel {

    private Likes mLikes;

    public Likes getLikes() {
        return mLikes;
    }

    public LikeCounterViewModel(Likes likes)  {
        super(likes.getCount());

        this.mLikes = likes;

        if(mLikes.getUserLikes()==1){

            setAccentColor();
        }

    }
}
