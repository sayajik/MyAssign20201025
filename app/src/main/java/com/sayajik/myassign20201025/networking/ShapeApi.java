package com.sayajik.myassign20201025.networking;

import com.sayajik.myassign20201025.model.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ShapeApi {
//    https://api.imgur.com/3/gallery/search/
//    https://api.imgur.com/3/gallery/search/1?q=vanilla
    @GET("1")
    Call<SearchResponse> getShapeData(@Header("authorization") String authorization, @Query("q") String searchData, @Query("q_type") String type);
}
