package com.example.jpmorgancodingexercise.albumslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jpmorgancodingexercise.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter  extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder>{

    private List<Album> albums = new ArrayList<>();

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new AlbumHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        Album currentAlbum = albums.get(position);
        holder.textViewTitle.setText(currentAlbum.getTitle());
        holder.textViewAlbumId.setText(String.valueOf(currentAlbum.getId()));
        holder.textViewUserId.setText(String.valueOf(currentAlbum.getUserId()));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    class AlbumHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewAlbumId;
        private TextView textViewUserId;


        public AlbumHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewUserId = itemView.findViewById(R.id.text_view_userid);
            textViewAlbumId = itemView.findViewById(R.id.text_view_albumid);
        }
    }
}
