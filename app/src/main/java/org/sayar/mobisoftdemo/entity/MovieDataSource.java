package org.sayar.mobisoftdemo.entity;

import android.util.Log;

import org.sayar.mobisoftdemo.model.MovieEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class MovieDataSource extends DataSource {
    private static MovieDataSource instance;

    public static MovieDataSource getInstance() {
        if (instance == null) {
            instance = new MovieDataSource();
        }
        return instance;
    }

    public void saveAll(final List<MovieEntity> movieEntities) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(movieEntities);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void save(final MovieEntity movie) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(movie);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void deleteAll() {
        final RealmResults<MovieEntity> realmResults = realm.where(MovieEntity.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResults.deleteAllFromRealm();
            }
        });
    }

    public RealmResults<MovieEntity> getAll() {
        return realm.where(MovieEntity.class).findAllAsync();
    }

    public MovieEntity get() {
        return realm.where(MovieEntity.class).findFirstAsync();
    }

    public MovieEntity get(String imdbID) {
        realm.refresh();
        return realm.where(MovieEntity.class).equalTo("imdbID", imdbID).findFirstAsync();
    }
}
