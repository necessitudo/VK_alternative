package ru.necessitudo.app.vk_alternative.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.common.utils.VkListHelper;
import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemBodyViewModel;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemFooterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.NewsItemHeaderViewModel;
import ru.necessitudo.app.vk_alternative.mvp.view.BaseFeedView;
import ru.necessitudo.app.vk_alternative.rest.api.WallApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.WallGetRequestModel;

/**
 * Created by olegdubrovin on 26/12/17.
 */

@InjectViewState
public class NewsFeedPresenter  extends  BaseFeedPresenter<BaseFeedView>{

    @Inject
    WallApi mWallApi;


    public  NewsFeedPresenter(){
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.get(new WallGetRequestModel(-86529522, count, offset).toMap())
                .flatMap(full->Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .doOnNext(wallItem -> saveToDb(wallItem))
                .flatMap(wallItem->{
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                });
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromrealmCallable())
                .flatMap(Observable::fromIterable)
                .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
    }

    private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new NewsItemHeaderViewModel(wallItem));
        baseItems.add(new NewsItemBodyViewModel(wallItem));
        baseItems.add(new NewsItemFooterViewModel(wallItem));

        return baseItems;

    }


    public Callable<List<WallItem>> getListFromrealmCallable(){
      return () -> {
          String[] sortFileds = {"date"};
          Sort[] sortOrder = {Sort.DESCENDING};
          Realm realm = Realm.getDefaultInstance();
          RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                  .findAllSorted(sortFileds, sortOrder);
          return realm.copyFromRealm(realmResults);

      };

    }
}
