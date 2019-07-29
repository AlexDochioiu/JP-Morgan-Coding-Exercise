package com.example.jpmorgancodingexercise.albumslist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "album_table")
public class Album {

    @PrimaryKey(autoGenerate = false)
    private int id;
    private int userId;
    private String title;

    public Album(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    @SerializedName("id")
    public int getId() {
        return id;
    }

    @SerializedName("userId")
    public int getUserId() {
        return userId;
    }

    @SerializedName("title")
    public String getTitle() {
        return title;
    }
}
