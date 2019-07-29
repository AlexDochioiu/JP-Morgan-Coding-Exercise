package com.example.jpmorgancodingexercise.albumslist;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jpmorgancodingexercise.JpApplication;
import com.example.jpmorgancodingexercise.R;
import com.example.jpmorgancodingexercise.albumslist.adapters.AlbumAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView albumRecyclerView = findViewById(R.id.album_recycler_view);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumRecyclerView.setHasFixedSize(true);

        final AlbumAdapter albumAdapter = new AlbumAdapter();
        albumRecyclerView.setAdapter(albumAdapter);

        final JpApplication application = (JpApplication) getApplication();

        mainViewModel = ViewModelProviders.of(this, application.getMainViewModelFactory()).get(MainViewModel.class);
        mainViewModel.getAllAlbums().observe(this, albums -> {
            Log.d(TAG, "Albums list observed");
            albumAdapter.setAlbums(albums);
        });
    }
}
