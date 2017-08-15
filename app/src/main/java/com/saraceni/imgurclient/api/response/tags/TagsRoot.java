package com.saraceni.imgurclient.api.response.tags;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class TagsRoot {

    @SerializedName("data")
    @Expose
    private TagsData tagsData;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public TagsData getTagsData() {
        return tagsData;
    }

    public void setTagsData(TagsData tagsData) {
        this.tagsData = tagsData;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
