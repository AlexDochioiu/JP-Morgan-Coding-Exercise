package com.example.jpmorgancodingexercise;

import android.app.Application;

import com.example.jpmorgancodingexercise.albumslist.MainViewModelFactory;
import com.example.jpmorgancodingexercise.albumslist.repo.AlbumRepository;
import com.example.jpmorgancodingexercise.albumslist.repo.databse.AlbumDatabase;
import com.example.jpmorgancodingexercise.albumslist.repo.networking.AlbumNetworking;
import com.example.jpmorgancodingexercise.albumslist.repo.networking.JsonPlaceHolderApi;
import com.example.jpmorgancodingexercise.general.NetworkUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex Dochioiu on 2019-07-29
 */
@SuppressWarnings("FieldCanBeLocal")
public class JpApplication extends Application {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //TODO those fields should be moved into the dagger graph and injected where neede
    private NetworkUtils networkUtils;
    private Retrofit retrofit;
    private AlbumRepository albumRepository;
    private AlbumDatabase albumDatabase;
    private AlbumNetworking albumNetworking;
    private MainViewModelFactory mainViewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        networkUtils = new NetworkUtils(getApplicationContext());
        createRetrofitInstance();

        albumDatabase = AlbumDatabase.getInstance(getApplicationContext());
        albumNetworking = new AlbumNetworking(retrofit.create(JsonPlaceHolderApi.class));
        albumRepository = new AlbumRepository(networkUtils, albumDatabase.albumDAO(), albumNetworking);

        mainViewModelFactory = new MainViewModelFactory(albumRepository);
    }

    private void createRetrofitInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MainViewModelFactory getMainViewModelFactory() {
        return mainViewModelFactory;
    }
}
