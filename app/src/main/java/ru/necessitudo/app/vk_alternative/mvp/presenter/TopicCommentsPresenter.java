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
import ru.necessitudo.app.vk_alternative.model.CommentItem;
import ru.necessitudo.app.vk_alternative.model.Place;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.CommentBodyViewModel;
import ru.necessitudo.app.vk_alternative.model.view.CommentFooterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.CommentHeaderViewModel;
import ru.necessitudo.app.vk_alternative.mvp.view.BaseFeedView;
import ru.necessitudo.app.vk_alternative.rest.api.BoardApi;
import ru.necessitudo.app.vk_alternative.rest.model.request.BoardGetCommentsRequestModel;

/**
 * Created by olegdubrovin on 20/01/18.
 */

@InjectViewState
public class TopicCommentsPresenter extends BaseFeedPresenter<BaseFeedView> {
    private Place mPlace;

    @Inject
    BoardApi mBoardApi;


    public TopicCommentsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getComments(new BoardGetCommentsRequestModel(
                Integer.parseInt(mPlace.getOwnerId()), Integer.parseInt(mPlace.getPostId()), offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response, true)))
                .doOnNext(commentItem -> commentItem.setPlace(mPlace))
                .doOnNext(this::saveToDb)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.mPlace) && commentItem.isFromTopic)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }


    private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new CommentHeaderViewModel(commentItem));
        baseItems.add(new CommentBodyViewModel(commentItem));
        baseItems.add(new CommentFooterViewModel(commentItem));
        return baseItems;
    }


    public Callable<List<CommentItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"id"};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<CommentItem> results = realm.where(CommentItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }


    public void setPlace(Place place) {
        this.mPlace = place;
    }
}
