package com.yyn.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;


import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private GalleryViewModel galleryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Pass empty list and context to the adapter initially
        imageAdapter = new ImageAdapter(new ArrayList<Photo>(), this);
        recyclerView.setAdapter(imageAdapter);

        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        observeRecentImages();
        fetchRecentImages();

        // Set up the left navigation bar
        findViewById(R.id.navHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle home button click
                Snackbar.make(v, "Home button clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void observeRecentImages() {
        galleryViewModel.getRecentImages().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                imageAdapter.setPhotos(photos);
            }
        });
    }

    private void fetchRecentImages() {
        galleryViewModel.fetchRecentImages();
    }
}
