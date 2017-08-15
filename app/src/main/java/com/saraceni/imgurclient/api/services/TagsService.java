package com.saraceni.imgurclient.api.services;

import com.saraceni.imgurclient.api.response.tags.TagsRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public interface TagsService {
    @GET("tags")
    Observable<TagsRoot> getTags();
}
