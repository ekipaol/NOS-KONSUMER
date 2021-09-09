package com.application.bris.ikurma_nos_konsumer.database.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by idong on 27/05/2019.
 */

public class RealmConfig {
    public static String REALM_NAME = "brisidata.realm";
    public static int REALM_CURRENT_VERSION = 3;

    public static void init(Context context){
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(REALM_CURRENT_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(configuration);
        Realm.getInstance(configuration);
    }
}
