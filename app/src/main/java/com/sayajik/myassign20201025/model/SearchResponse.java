package com.sayajik.myassign20201025.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("success")
    @Expose
    Boolean success;

    @SerializedName("status")
    @Expose
    int status;

    @SerializedName("data")
    @Expose
    List<ShapeDataModel> searchList;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ShapeDataModel> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<ShapeDataModel> searchList) {
        this.searchList = searchList;
    }
}
