package com.application.bris.ikurma_nos_konsumer.baseapp;

import android.app.Application;

import com.application.bris.ikurma_nos_konsumer.database.realm.RealmConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by idong on 27/05/2019.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        try {
            RealmConfiguration configuration = new RealmConfiguration.Builder()
                    .name(RealmConfig.REALM_NAME)
                    .schemaVersion(RealmConfig.REALM_CURRENT_VERSION)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(configuration);
            Realm.getInstance(configuration);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}
