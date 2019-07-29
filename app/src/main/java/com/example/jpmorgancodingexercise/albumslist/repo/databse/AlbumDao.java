package com.example.jpmorgancodingexercise.albumslist.repo.databse;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jpmorgancodingexercise.albumslist.repo.model.Album;

import java.util.List;

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Album> album);

    @Update
    void delete(Album album);

    @Query("DELETE FROM album_table")
    void deleteAllAlbums();

    @Query("SELECT * FROM album_table ORDER BY id ASC")
    LiveData<List<Album>> getAllAlbums();

}
