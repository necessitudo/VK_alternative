package ru.necessitudo.app.vk_alternative.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import ru.necessitudo.app.vk_alternative.CurrentUser;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.common.manager.MyFragmentManager;
import ru.necessitudo.app.vk_alternative.common.manager.NetworkManager;
import ru.necessitudo.app.vk_alternative.model.Profile;
import ru.necessitudo.app.vk_alternative.mvp.view.MainView;
import ru.necessitudo.app.vk_alternative.rest.api.UsersApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.UsersGetRequestModel;
import ru.necessitudo.app.vk_alternative.ui.activity.SettingActivity;
import ru.necessitudo.app.vk_alternative.ui.fragment.BaseFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.BoardFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.GroupRulesFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.InfoFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.MembersFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.MyPostsFragment;
import ru.necessitudo.app.vk_alternative.ui.fragment.NewsFeedFragment;

/**
 * Created by olegdubrovin on 04/12/17.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>{


    @Inject
    MyFragmentManager myFragmentManager;

    @Inject
    UsersApi mUserApi;

    @Inject
    NetworkManager mNetworkManager;

    public  void checkAuth() {
        if (!CurrentUser.isAutorized()){
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedId();
        }
    }

    public MainPresenter() {

        MyApplication.getApplicationComponent().inject(this);
    }

    public Observable<Profile> getProfileFromNetwork(){
        return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    private Observable<Profile> getProfileFromDb(){
        return Observable.fromCallable(getListFromRealmCallable());
    }

    private Callable<Profile> getListFromRealmCallable() {
        return ()->{
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    public void saveToDb(RealmObject item){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    private void getCurrentUser(){
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if(!CurrentUser.isAutorized()){
                        getViewState().startSignIn();
                    }
                    return aBoolean
                            ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> {
                    getViewState().showCurrentUser(profile);
                }, error -> {
                    error.printStackTrace();
                });
        }

    public void drawerItemClick(int id){

        BaseFragment fragment = null;
        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 3:
                getViewState().startActivityFromDrawer(SettingActivity.class);
                return;
            case 4:
                fragment = new MembersFragment();
                break;
            case 5:
                fragment = new BoardFragment();
                break;
            case 6:
                fragment = new InfoFragment();
                break;
            case 7:
                fragment = new GroupRulesFragment();
                break;
        }

        if(fragment != null && !myFragmentManager.isAlreadyContains(fragment)){
            getViewState().showFragmentFromDrawer(fragment);

        }


        }
    }
