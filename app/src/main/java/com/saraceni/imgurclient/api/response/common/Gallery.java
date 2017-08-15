package com.saraceni.imgurclient.api.response.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saraceni.imgurclient.api.response.common.TopPost;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class Gallery {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("topPost")
    @Expose
    private TopPost topPost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TopPost getTopPost() {
        return topPost;
    }

    public void setTopPost(TopPost topPost) {
        this.topPost = topPost;
    }
}
