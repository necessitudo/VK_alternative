package ru.necessitudo.app.vk_alternative.common;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.ui.view.holder.BaseViewHolder;

/**
 * Created by olegdubrovin on 13/12/17.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    private List<BaseViewModel> list = new ArrayList<>();

    private ArrayMap<Integer, BaseViewModel> mTypeInstances = new android.support.v4.util.ArrayMap<>();

    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeInstances.get(viewType).createViewHolder(parent);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getValue();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {

        holder.bindViewHolder(getItem(position));

    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public BaseViewModel getItem(int position){
        return list.get(position);
    }

    public void registerTypeInstances(BaseViewModel item){
       if(!mTypeInstances.containsKey(item.getType().getValue())){
           mTypeInstances.put(item.getType().getValue(), item);

       }
    }

    public void addItems(List<?  extends BaseViewModel> newItems){
        for (BaseViewModel newItem:newItems){
            registerTypeInstances(newItem);
        }
        list.addAll(newItems);

        notifyDataSetChanged();

    }

    public void setItems(List<BaseViewModel> items){
        clearList();
        addItems(items);
    }

    private void clearList() {

        list.clear();
    }

    public int getRealItemCount(){
        int count = 0;

        for (int i=0; i<getItemCount(); i++){
            if (!getItem(i).isItemDecorator()){
                count+=1;
            }
        }

        return count;
    }
}

