package com.example.jpmorgancodingexercise.albumslist.repo.networking;

import com.example.jpmorgancodingexercise.albumslist.repo.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("albums")
    Call<List<Album>> getAlbums();
}
