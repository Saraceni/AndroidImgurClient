package com.saraceni.imgurclient.api.response.images;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saraceni.imgurclient.api.response.common.Image;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class ImageData {

    @Expose
    private Image data;
    @SerializedName("data")

    public Image getData() {
        return data;
    }

    public void setData(Image data) {
        this.data = data;
    }
}
