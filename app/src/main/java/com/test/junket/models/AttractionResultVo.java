package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttractionResultVo {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("images")
    @Expose
    private String images;

    @SerializedName("dest_id")
    @Expose
    private String dest_id;

    public String getAttractionName() {
        return name;
    }
    public void setAttractionName(String destId) {
        this.name = name;
    }

    public String getAttractionDescription() {
        return description;
    }
    public void setAttractionDescription(String description) {
        this.description = description;
    }

    public String getAttractionImages() {
        return images;
    }
    public void setAttractionImages(String images) {
        this.images = images;
    }

    public String getAttractionDestId() {
        return dest_id;
    }
    public void setAttractionDestId(String dest_id) {
        this.dest_id = dest_id;
    }
}
