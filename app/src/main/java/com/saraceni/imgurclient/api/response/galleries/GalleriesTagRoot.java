package com.saraceni.imgurclient.api.response.galleries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saraceni.imgurclient.api.response.common.Image;
import com.saraceni.imgurclient.api.response.common.Tag;
import com.saraceni.imgurclient.api.response.images.ImageData;

import java.util.List;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class GalleriesTagRoot {

    @SerializedName("data")
    @Expose
    private Tag data;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Tag getData() {
        return data;
    }

    public void setData(Tag data) {
        this.data = data;
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
