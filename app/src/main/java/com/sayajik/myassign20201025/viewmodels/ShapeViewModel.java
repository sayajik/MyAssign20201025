package com.sayajik.myassign20201025.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sayajik.myassign20201025.model.SearchResponse;
import com.sayajik.myassign20201025.networking.ShapeRepository;

public class ShapeViewModel extends ViewModel {
    private MutableLiveData<SearchResponse> mutableLiveData;
    private ShapeRepository shapeRepository;

//    public void init() {
//        if (mutableLiveData != null) {
//            return;
//        }
//        shapeRepository = ShapeRepository.getInstance();
//        mutableLiveData = shapeRepository.getNews("google-news", "API_KEY");
//
//    }

    public LiveData<SearchResponse> getShapeRepository() {
        return mutableLiveData;
    }
}
