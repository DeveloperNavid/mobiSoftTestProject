package org.sayar.mobisoftdemo.util;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by saeed on 8/17/16.
 * Email: saeed.motevalian@gmail.com
 * Phone: +989124309658
 */
public class RealmResultsUtil {

    public static ArrayList convert(RealmResults realmResults) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.trimToSize();
        if (realmResults != null) {
            for (int i = 0; i < realmResults.size(); i++) {
                arrayList.add(realmResults.get(i));
            }
        }
        return arrayList;
    }
}
