package org.sayar.mobisoftdemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Navid Mahboubi at 9/8/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class MovieRateEntity extends RealmObject {

    @SerializedName("Source")
    private String source;
    @SerializedName("Value")
    private String value;

    public MovieRateEntity() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
