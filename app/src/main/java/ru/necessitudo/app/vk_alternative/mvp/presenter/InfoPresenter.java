package ru.necessitudo.app.vk_alternative.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.consts.ApiConstants;
import ru.necessitudo.app.vk_alternative.model.Group;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.InfoContactsViewModel;
import ru.necessitudo.app.vk_alternative.model.view.InfoLinksViewModel;
import ru.necessitudo.app.vk_alternative.model.view.InfoStatusViewModel;
import ru.necessitudo.app.vk_alternative.mvp.view.BaseFeedView;
import ru.necessitudo.app.vk_alternative.rest.api.GroupsApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.GroupsGetByIdRequestModel;

/**
 * Created by olegdubrovin on 09/01/18.
 */

@InjectViewState
public class InfoPresenter extends  BaseFeedPresenter<BaseFeedView>{


    @Inject
    GroupsApi mGroupApi;

    public InfoPresenter() {

        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb)
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private List<BaseViewModel> parsePojoModel(Group group) {

        List<BaseViewModel> items = new ArrayList<>();
        items.add(new InfoStatusViewModel(group));
        items.add(new InfoContactsViewModel());
        items.add(new InfoLinksViewModel());

        return items;

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private Callable<Group> getListFromRealmCallable() {

        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
                    .findFirst();
            return realm.copyFromRealm(result);
        };
    }
}
