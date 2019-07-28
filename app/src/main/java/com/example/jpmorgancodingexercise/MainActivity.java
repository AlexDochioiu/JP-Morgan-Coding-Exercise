package com.example.jpmorgancodingexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ALBUM";


    private AlbumViewModel albumViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView albumRecyclerView = findViewById(R.id.album_recycler_view);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumRecyclerView.setHasFixedSize(true);

        final AlbumAdapter albumAdapter = new AlbumAdapter();
        albumRecyclerView.setAdapter(albumAdapter);


        albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel.class);
        albumViewModel.getAllAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                albumAdapter.setAlbums(albums);
                for(Album album : albums) {
                    Log.d(TAG, album.getTitle());
                }
            }
        });
    }
}
