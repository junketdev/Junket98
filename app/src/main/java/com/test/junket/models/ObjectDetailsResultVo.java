package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectDetailsResultVo {

    @SerializedName("object_id")
    @Expose
    private String objectId;
    @SerializedName("object_title")
    @Expose
    private String objectTitle;
    @SerializedName("object_details")
    @Expose
    private String objectDetails;
    @SerializedName("object_image")
    @Expose
    private String objectImage;
    @SerializedName("object_qr")
    @Expose
    private String objectQr;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    public String getObjectDetails() {
        return objectDetails;
    }

    public void setObjectDetails(String objectDetails) {
        this.objectDetails = objectDetails;
    }

    public String getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(String objectImage) {
        this.objectImage = objectImage;
    }

    public String getObjectQr() {
        return objectQr;
    }

    public void setObjectQr(String objectQr) {
        this.objectQr = objectQr;
    }


}
