package com.test.junket.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Result;

public class DestinationListVo {

    @SerializedName("result")
    @Expose
    private List<DestinationResultVo> result = null;

    public List<DestinationResultVo> getResult() {
        return result;
    }

    public void setResult(List<DestinationResultVo> result) {
        this.result = result;
    }

}