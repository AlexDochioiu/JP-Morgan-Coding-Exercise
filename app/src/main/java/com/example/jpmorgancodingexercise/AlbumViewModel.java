package com.example.jpmorgancodingexercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {

    private AlbumRepository repository;
    private LiveData<List<Album>> allAlbums;

    public AlbumViewModel(@NonNull Application application) {
        super(application);
        repository = new AlbumRepository(application);
        allAlbums = repository.getAllAlbums();
    }

    public void insert(Album album) {
        repository.insert(album);
    }

    public void update(Album album) {
        repository.update(album);
    }

    public void delete(Album album) {
        repository.delete(album);
    }

    public void deleteAllAlbums() {
        repository.deleteAllAlbums();
    }

    public LiveData<List<Album>> getAllAlbums() {
        return allAlbums;
    }
}
