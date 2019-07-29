package com.example.jpmorgancodingexercise.albumslist.repo;

import com.example.jpmorgancodingexercise.albumslist.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("albums")
    Call<List<Album>> getAlbums();
}
