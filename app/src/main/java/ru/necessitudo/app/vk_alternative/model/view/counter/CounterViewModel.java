package ru.necessitudo.app.vk_alternative.model.view.counter;

import ru.necessitudo.app.vk_alternative.R;

/**
 * Created by olegdubrovin on 24/12/17.
 */

public class CounterViewModel {

    protected int mCount;
    protected int mIconColor = R.color.colorIconDis;
    protected int mTextColor = R.color.colorIconDis;

    public int getCount() {
        return mCount;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public CounterViewModel(int count){
        this.mCount = count;

        if (mCount>0){
            setDefaultColor();
        } else {
            setDisableColor();
        }
        
    }

    private void setDisableColor() {
        mIconColor = R.color.colorIconDis;
        mTextColor = R.color.colorIconDis;
    }

    private void setDefaultColor() {
        mIconColor = R.color.colorIcon;
        mTextColor = R.color.colorIcon;
    }

    protected void setAccentColor(){
        mIconColor = R.color.colorAccent;
        mTextColor = R.color.colorAccent;
    }
}
