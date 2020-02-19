package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttractionVo {

    @SerializedName("result")
    @Expose
    private List<AttractionResultVo> result = null;

    public List<AttractionResultVo> getResult() {
        return result;
    }

    public void setResult(List<AttractionResultVo> result) {
        this.result = result;
    }

}
