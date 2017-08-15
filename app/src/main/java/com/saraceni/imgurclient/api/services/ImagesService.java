package com.saraceni.imgurclient.api.services;

import com.saraceni.imgurclient.api.response.images.ImageRoot;
import com.saraceni.imgurclient.api.response.tags.TagsRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public interface ImagesService {

    @GET("image/{{hash}}")
    Observable<ImageRoot> getImage(@Path("hash") String imgHash);
}
