package com.example.jpmorgancodingexercise.albumslist.repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.jpmorgancodingexercise.albumslist.repo.databse.AlbumDao;
import com.example.jpmorgancodingexercise.albumslist.repo.model.Album;
import com.example.jpmorgancodingexercise.albumslist.repo.networking.AlbumNetworking;
import com.example.jpmorgancodingexercise.general.NetworkUtils;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AlbumRepository {
    private AlbumDao albumDao;
    private AlbumNetworking albumNetworking;

    private static final String TAG = "AlbumRepository";

    public AlbumRepository(
            @NonNull final NetworkUtils networkUtils,
            @NonNull final AlbumDao albumDao,
            @NonNull final AlbumNetworking albumNetworking) {
        requireNonNull(networkUtils);
        this.albumDao = requireNonNull(albumDao);
        this.albumNetworking = requireNonNull(albumNetworking);

        observeAlbumsNetworkUpdate();

        if (networkUtils.isNetworkActive()) {
            albumNetworking.refreshAlbums();
        }
    }

    public LiveData<List<Album>> getAllAlbums() {
        return albumDao.getAllAlbums();
    }

    private void observeAlbumsNetworkUpdate() {
        albumNetworking.getAlbums().observeForever(albums -> {

            new Thread(() -> {
                albumDao.insertAll(albums);
            }).start();

        });
    }
}
