package ru.necessitudo.app.vk_alternative.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();


}
