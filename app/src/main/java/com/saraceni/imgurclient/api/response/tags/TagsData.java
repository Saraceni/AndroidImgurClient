package com.saraceni.imgurclient.api.response.tags;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saraceni.imgurclient.api.response.common.Gallery;
import com.saraceni.imgurclient.api.response.common.Tag;

import java.util.List;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class TagsData {

    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("featured")
    @Expose
    private String featured;
    @SerializedName("galleries")
    @Expose
    private List<Gallery> galleries = null;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public List<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }
}
