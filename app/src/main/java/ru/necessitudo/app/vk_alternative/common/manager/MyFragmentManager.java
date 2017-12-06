package ru.necessitudo.app.vk_alternative.common.manager;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

import ru.necessitudo.app.vk_alternative.ui.activity.BaseActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.BaseFragment;

/**
 * Created by olegdubrovin on 05/12/17.
 */

public class MyFragmentManager {

    public static final int  EMPTY_FRAGMENT_STACK_SIZE = 1;

    private Stack<BaseFragment> mFragmentStack = new Stack();

    private  BaseFragment mCurrentFragment;


    public  void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId){

        if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment));

        FragmentTransaction transaction = createAddTransaction(activity, fragment, false);

        transaction.replace(containerId, fragment);
        commitAddTransaction(activity, fragment, transaction, false);

    }

    public  void addFragment (BaseActivity activity, BaseFragment fragment, @IdRes int containerId){

        if(activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)){
            FragmentTransaction transaction = createAddTransaction(activity, fragment, false);

            transaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, true);
        }
    }

    public boolean removeFragment(BaseActivity activity, BaseFragment fragment){

        boolean canRemove = fragment != null && mFragmentStack.size()>EMPTY_FRAGMENT_STACK_SIZE;

        if(canRemove){

            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            mFragmentStack.pop();
            mCurrentFragment = mFragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);
        }

        return canRemove;

    }

    public  boolean removeCurrentFragment(BaseActivity activity){
        return  removeFragment(activity, mCurrentFragment);

    }

    private FragmentTransaction createAddTransaction(BaseActivity activity, BaseFragment fragment, boolean addToBackStack){

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if (addToBackStack){
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        return  fragmentTransaction;


    }

    private  void commitAddTransaction(BaseActivity activity, BaseFragment fragment,FragmentTransaction fragmentTransaction,  boolean addToBackStack){

        if (fragmentTransaction!=null){

            mCurrentFragment = fragment;

            if(!addToBackStack){
                mFragmentStack = new Stack<>();
            }

            mFragmentStack.add(fragment);

            commitTransaction(activity, fragmentTransaction);

        }

    }

    private  void commitTransaction(BaseActivity activity, FragmentTransaction fragmentTransaction){

        fragmentTransaction.commit();

        activity.fragmentOnScreen(mCurrentFragment);



    }

    private  boolean isAlreadyContains(BaseFragment baseFragment){

        if(baseFragment==null){
            return false;
        }

        return mCurrentFragment != null && mCurrentFragment.getClass().getName().equals(baseFragment.getClass().getName());

    }

}
