package com.example.jpmorgancodingexercise.albumslist.repo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jpmorgancodingexercise.albumslist.model.Album;


@Database(entities = Album.class, version = 2)
public abstract class AlbumDatabase extends RoomDatabase {

    private static AlbumDatabase instance;

    public abstract AlbumDao albumDAO();

    public static synchronized AlbumDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AlbumDatabase.class,
                    "album_database")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
        }
    };

//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private AlbumDao albumDao;
//
//        private PopulateDbAsyncTask(AlbumDatabase db) {
//            albumDao = db.albumDAO();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            albumDao.insert(new Album(1, "Beggars Banquet"));
//            albumDao.insert(new Album(1, "Let It Bleed"));
//            albumDao.insert(new Album(1, "Sticky Fingers"));
//            albumDao.insert(new Album(1, "Exile On Main St"));
//            albumDao.insert(new Album(1, "Tattoo You"));
//            return null;
//        }
//    }
}
