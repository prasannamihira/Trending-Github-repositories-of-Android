package com.xapo.xapogithubtest.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositoryResponse implements Serializable {

    @SerializedName("total_count")
    public int totalCount;

    @SerializedName("items")
    public ArrayList<RepositoryBody> itemsList;

    public RepositoryResponse(int totalCount, ArrayList<RepositoryBody> itemsList) {
        this.totalCount = totalCount;
        this.itemsList = itemsList;
    }
}
