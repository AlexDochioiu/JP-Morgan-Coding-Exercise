package com.example.jpmorgancodingexercise.albumslist.repo.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jpmorgancodingexercise.albumslist.repo.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alex Dochioiu on 2019-07-29
 */
public class AlbumNetworking {
    private static final String TAG = "AlbumNetworking";

    private final MutableLiveData<List<Album>> albums = new MutableLiveData<>();
    private final JsonPlaceHolderApi api;

    public AlbumNetworking(JsonPlaceHolderApi api) {
        this.api = api;
    }

    public void refreshAlbums() {
        Log.d(TAG, "refreshAlbums: Triggered network refresh");

        api.getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, final Response<List<Album>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Code: " + response.code());
                    return;
                }

                albums.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }

    public LiveData<List<Album>> getAlbums() {
        return albums;
    }
}
