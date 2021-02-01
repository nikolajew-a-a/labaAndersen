package com.example.android.topic41.domain.util;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "sourceId")
    private String id;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    public Source(@NonNull String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
