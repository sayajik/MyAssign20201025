package com.sayajik.myassign20201025.networking;

import androidx.lifecycle.MutableLiveData;

import com.sayajik.myassign20201025.model.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShapeRepository {
    private static ShapeRepository shapeRepository;

    public static ShapeRepository getInstance(){
        if (shapeRepository == null){
            shapeRepository = new ShapeRepository();
        }
        return shapeRepository;
    }

    private ShapeApi shapeApi;

    public ShapeRepository(){
        shapeApi = RetrofitService.cteateService(ShapeApi.class);
    }

//    public MutableLiveData<SearchResponse> getNews(String source, String key){
//        final MutableLiveData<SearchResponse> newsData = new MutableLiveData<>();
//        shapeApi.getNewsList(source, key).enqueue(new Callback<SearchResponse>() {
//            @Override
//            public void onResponse(Call<SearchResponse> call,
//                                   Response<SearchResponse> response) {
//                if (response.isSuccessful()){
//                    newsData.setValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SearchResponse> call, Throwable t) {
//                newsData.setValue(null);
//            }
//        });
//        return newsData;
//    }
}
