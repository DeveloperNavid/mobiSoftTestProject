package org.sayar.mobisoftdemo.entity;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class DataMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        final RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            oldVersion++;
        }
        if (oldVersion == 1) {
            schema.create("MovieRateEntity")
                    .addField("source", String.class)
                    .addField("value", String.class);

            schema.create("MovieEntity")
                    .addRealmListField("ratings", schema.get("MovieRateEntity"));

            oldVersion++;
        }
    }
}
