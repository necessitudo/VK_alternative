package ru.necessitudo.app.vk_alternative.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.necessitudo.app.vk_alternative.MyApplication;
import ru.necessitudo.app.vk_alternative.common.utils.VkListHelper;
import ru.necessitudo.app.vk_alternative.model.CommentItem;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.CommentFooterViewModel;
import ru.necessitudo.app.vk_alternative.model.view.OpenedPostHeaderViewModel;
import ru.necessitudo.app.vk_alternative.mvp.view.BaseFeedView;

/**
 * Created by olegdubrovin on 20/01/18.
 */

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView> {

    int id;

    public OpenedCommentPresenter() {
        MyApplication.getApplicationComponent().inject(this);

    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return createObservable();

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return createObservable();
    }


    private Observable<BaseViewModel> createObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .retry(2)
                .flatMap(commentItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(commentItem));

                    list.addAll(VkListHelper.getAttachmentVhItems(commentItem.getAttachments()));

                    list.add(new CommentFooterViewModel(commentItem));

                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }



    public Callable<CommentItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            CommentItem commentItem = realm.where(CommentItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(commentItem);
        };
    }

    public void setId(int id) {
        this.id = id;
    }
}
