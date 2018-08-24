package com.xapo.xapogithubtest.network;

import com.xapo.xapogithubtest.model.response.RepositoryResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface XapoGitApi {

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8"
    })

    /**
     * Get trending repositories of Android
     */
    @GET("repositories")
    Observable<RepositoryResponse> gitRepositoryRequest(@Query("order") String order, @Query("q") String language);
}
