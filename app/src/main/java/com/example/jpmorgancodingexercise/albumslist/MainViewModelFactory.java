package com.example.jpmorgancodingexercise.albumslist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jpmorgancodingexercise.albumslist.repo.AlbumRepository;

import static java.util.Objects.requireNonNull;

/**
 * Created by Alex Dochioiu on 2019-07-29
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final AlbumRepository albumRepository;

    public MainViewModelFactory(@NonNull final AlbumRepository albumRepository) {
        this.albumRepository = requireNonNull(albumRepository);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(albumRepository);
        } else {
            throw new IllegalStateException("ViewModel not found");
        }
    }
}
