package ru.necessitudo.app.vk_alternative.model.view.counter;

import ru.necessitudo.app.vk_alternative.model.Comments;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class CommentCounterViewModel extends CounterViewModel {

    private Comments mComments;


    public Comments getComments() {
        return mComments;
    }

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.mComments = comments;

    }
}
