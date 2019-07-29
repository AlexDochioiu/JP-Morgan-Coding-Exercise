package com.example.jpmorgancodingexercise.albumslist.repo.databse;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jpmorgancodingexercise.albumslist.repo.model.Album;


@Database(entities = Album.class, version = 2)
public abstract class AlbumDatabase extends RoomDatabase {

    private static AlbumDatabase instance;

    public abstract AlbumDao albumDAO();

    public static synchronized AlbumDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AlbumDatabase.class,
                    "album_database")
                    .fallbackToDestructiveMigration() // todo add migration as needed
                    .build();
        }
        return instance;
    }

}
