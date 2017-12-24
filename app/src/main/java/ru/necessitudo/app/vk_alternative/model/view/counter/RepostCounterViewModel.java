package ru.necessitudo.app.vk_alternative.model.view.counter;

import ru.necessitudo.app.vk_alternative.model.Reposts;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class RepostCounterViewModel extends CounterViewModel {


    private Reposts  mReposts;

    public Reposts getReposts() {
        return mReposts;
    }

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        this.mReposts = reposts;

        if(reposts.getUserReposted()==1){
            setAccentColor();
        }

    }
}
