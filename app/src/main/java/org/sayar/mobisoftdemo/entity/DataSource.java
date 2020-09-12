package org.sayar.mobisoftdemo.entity;

import org.sayar.mobisoftdemo.Main;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class DataSource {

    public static Realm realm;

    public DataSource() {
        Realm.init(Main.appContext);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("mobisoft.db")
                .schemaVersion(2).migration(new DataMigration()).build();
        realm = Realm.getInstance(realmConfiguration);
    }
}
