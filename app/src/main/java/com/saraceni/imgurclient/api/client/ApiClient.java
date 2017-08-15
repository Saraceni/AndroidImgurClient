package com.saraceni.imgurclient.api.client;

import com.saraceni.imgurclient.api.services.GalleriesService;
import com.saraceni.imgurclient.api.services.ImagesService;
import com.saraceni.imgurclient.api.services.TagsService;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class ApiClient {

    private static final String BASE_URL = "https://api.imgur.com/3/";
    private static final String CLIENT_ID = "YourImgurClientId";

    public static String getBackgroundImageUrl(String imgHash) {
        return "http://i.imgur.com/" + imgHash + ".png";
    }


    public TagsService getTagsObservable() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiClient.BASE_URL)
                .client(getAuthorizationHeaderInterceptor())
                .build();

        return retrofit.create(TagsService.class);
    }

    public GalleriesService getGallerieForTag() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiClient.BASE_URL)
                .client(getAuthorizationHeaderInterceptor())
                .build();

        return retrofit.create(GalleriesService.class);

    }

    public OkHttpClient getAuthorizationHeaderInterceptor() {

        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        "Client-ID " + ApiClient.CLIENT_ID);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();
    }
}
