package com.example.jpmorgancodingexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("albums")
    Call<List<Album>> getAlbums();
}
