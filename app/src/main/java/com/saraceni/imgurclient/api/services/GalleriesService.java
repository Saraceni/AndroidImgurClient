package com.saraceni.imgurclient.api.services;

import com.saraceni.imgurclient.api.response.galleries.GalleriesTagRoot;
import com.saraceni.imgurclient.api.response.images.ImageRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public interface GalleriesService {

    @GET("gallery/t/{tagName}/{sort}/{window}/{page}")
    Observable<GalleriesTagRoot> getPostsFromTag(@Path("tagName") String tagName, @Path("sort") String sort,
                                @Path("window") String window, @Path("page") int page);
}
