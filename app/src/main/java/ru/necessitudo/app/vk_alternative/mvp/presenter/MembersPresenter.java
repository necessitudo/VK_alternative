package ru.necessitudo.app.vk_alternative.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.consts.ApiConsts;
import ru.necessitudo.app.vk_alternative.model.Member;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.MemberViewModel;
import ru.necessitudo.app.vk_alternative.mvp.view.BaseFeedView;
import ru.necessitudo.app.vk_alternative.rest.api.GroupsApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.GroupsGetMemberRequestModel;

/**
 * Created by olegdubrovin on 31/12/17.
 */

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    GroupsApi mGroupsApi;

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupsApi.getMembers(new GroupsGetMemberRequestModel(
                ApiConsts.MY_GROUP_ID, count, offset).toMap())
                .flatMap(baseItemResponseFull -> {
                    return Observable.fromIterable(baseItemResponseFull.response.getItems());
                })
                .doOnNext(member -> saveToDb(member))
                .map(member -> new MemberViewModel(member));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(member-> new MemberViewModel(member));
    }

    private Callable<List<Member>> getListFromRealmCallable() {

        return ()-> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Member> results = realm.where(Member.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };


    }

    public MembersPresenter() {

        MyApplication.getApplicationComponent().inject(this);
    }
}
