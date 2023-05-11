package com.yyn.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryViewModel extends ViewModel {
    private MutableLiveData<List<Photo>> recentImages;
    private ApiService apiService;

    public GalleryViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<List<Photo>> getRecentImages() {
        if (recentImages == null) {
            recentImages = new MutableLiveData<>();
        }
        return recentImages;
    }

    public void fetchRecentImages() {
        apiService.getRecentImages().enqueue(new Callback<RecentImagesResponse>() {
            @Override
            public void onResponse(Call<RecentImagesResponse> call, Response<RecentImagesResponse> response) {
                if (response.isSuccessful()) {
                    RecentImagesResponse imagesResponse = response.body();
                    if (imagesResponse != null) {
                        List<Photo> photos = imagesResponse.getPhotos();
                        recentImages.setValue(photos);
                    }
                } else {
                    Log.e("GalleryViewModel", "Failed to fetch recent images: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RecentImagesResponse> call, Throwable t) {
                Log.e("GalleryViewModel", "Failed to fetch recent images: " + t.getMessage());
            }
        });
    }
}
