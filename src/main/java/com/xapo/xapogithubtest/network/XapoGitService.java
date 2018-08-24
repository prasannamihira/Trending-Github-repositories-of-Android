package com.xapo.xapogithubtest.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xapo.xapogithubtest.model.response.RepositoryResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class XapoGitService {

    private XapoGitApi xapoGitApi;
    private static XapoGitService xapoGitService;

    /**
     * XapoGitService singleton
     *
     * @return
     */
    public static XapoGitService getInstance() {
        if (xapoGitService == null)
            xapoGitService = new XapoGitService();
        return xapoGitService;
    }

    /**
     * XapoGitService constructor
     */
    private XapoGitService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(rxAdapter)
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        try {
            this.xapoGitApi = retrofit.create(XapoGitApi.class);
        } catch (Exception ex) {
            Log.d("XapoGitApi", ex.toString());
        }
    }

    /**
     * Trending GitHub repositories of Android
     *
     * @param order
     * @param language
     * @return
     */
    public Observable<RepositoryResponse> gitRepositoryRequest(String order, String language) {
        return this.xapoGitApi.gitRepositoryRequest(order, language);
    }
}
