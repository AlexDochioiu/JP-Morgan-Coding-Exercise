package com.example.jpmorgancodingexercise;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;

import java.net.NetworkInterface;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumRepository {
    private AlbumDao albumDao;
    private LiveData<List<Album>> allAlbums;

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String TAG = "AlbumRepository";

    private boolean isConnected;

    public AlbumRepository(Application application) {

        Context context = application.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activNetwork = cm.getActiveNetworkInfo();
        isConnected = activNetwork != null && activNetwork.isConnectedOrConnecting();

        AlbumDatabase database = AlbumDatabase.getInstance(application);
        albumDao = database.albumDAO();

        if(isConnected){
            ApiCallAndPopulateDb();
        }
        allAlbums = albumDao.getAllAlbums();

    }

    public void insert(Album album) {
        new InsertAlbumAsyncTask(albumDao).execute(album);
    }

    public void update(Album album) {
        new UpdateAlbumAsyncTask(albumDao).execute(album);
    }

    public void delete(Album album) {
        new DeleteAlbumAsyncTask(albumDao).execute(album);
    }

    public void deleteAllAlbums() {
        new DeleteAllAlbumsAsyncTask(albumDao).execute();

    }

    public LiveData<List<Album>> getAllAlbums() {
        return allAlbums;
    }

    private static class InsertAlbumAsyncTask extends AsyncTask<Album, Void, Void> {
        private AlbumDao albumDao;

        private InsertAlbumAsyncTask(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.insert(albums[0]);
            return null;
        }
    }

    private static class UpdateAlbumAsyncTask extends AsyncTask<Album, Void, Void> {
        private AlbumDao albumDao;

        private UpdateAlbumAsyncTask(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.update(albums[0]);
            return null;
        }
    }

    private static class DeleteAlbumAsyncTask extends AsyncTask<Album, Void, Void> {
        private AlbumDao albumDao;

        private DeleteAlbumAsyncTask(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Album... albums) {
            albumDao.delete(albums[0]);
            return null;
        }
    }

    private static class DeleteAllAlbumsAsyncTask extends AsyncTask<Void, Void, Void> {
        private AlbumDao albumDao;

        private DeleteAllAlbumsAsyncTask(AlbumDao albumDao) {
            this.albumDao = albumDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            albumDao.deleteAllAlbums();
            return null;
        }
    }

    public void ApiCallAndPopulateDb() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Album>> call = jsonPlaceHolderApi.getAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, final Response<List<Album>> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "Code: " + response.code() );
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            albumDao.deleteAllAlbums();
                            for (Album album : response.body()) {
                                albumDao.insert(album);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });




    }
}
