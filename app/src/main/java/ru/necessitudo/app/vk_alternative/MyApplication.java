package ru.necessitudo.app.vk_alternative;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.vk.sdk.VKSdk;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.necessitudo.app.vk_alternative.di.component.ApplicationComponent;
import ru.necessitudo.app.vk_alternative.di.component.DaggerApplicationComponent;
import ru.necessitudo.app.vk_alternative.di.module.ApplicationModule;

/**
 * Created by olegdubrovin on 04/12/17.
 */

public class MyApplication extends Application {

    private static ApplicationComponent sApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        VKSdk.initialize(this);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                 .Builder()
                 .deleteRealmIfMigrationNeeded()
                 .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                 Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        });

    }

    private void  initComponent(){
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

    }

    public  static  ApplicationComponent getApplicationComponent(){

        return sApplicationComponent;

    }
}
