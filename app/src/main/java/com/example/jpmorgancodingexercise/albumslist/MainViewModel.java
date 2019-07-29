package com.example.jpmorgancodingexercise.albumslist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.jpmorgancodingexercise.albumslist.repo.AlbumRepository;
import com.example.jpmorgancodingexercise.albumslist.repo.model.Album;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class MainViewModel extends ViewModel {

    private AlbumRepository repository;
    private LiveData<List<Album>> allAlbums;

    public MainViewModel(
            @NonNull final AlbumRepository albumRepository) {
        repository = requireNonNull(albumRepository);

        allAlbums = repository.getAllAlbums();
    }

    public LiveData<List<Album>> getAllAlbums() {
        return allAlbums;
    }
}
