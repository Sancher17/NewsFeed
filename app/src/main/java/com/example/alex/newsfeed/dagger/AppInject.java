package com.example.alex.newsfeed.dagger;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AppInject extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();


        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .initialData(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realm.createObject(Parent.class);
//                    }})
                .name("myrealm.realm")
                .build();
//        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);

    }

    public static AppComponent getComponent(){
        return component;
    }

}
