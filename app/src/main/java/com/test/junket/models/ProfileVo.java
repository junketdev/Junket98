package com.test.junket.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Result;

public class ProfileVo {

    @SerializedName("result")
    @Expose
    private List<ProfileResultVo> result = null;

    public List<ProfileResultVo> getResult() {
        return result;
    }

    public void setResult(List<ProfileResultVo> result) {
        this.result = result;
    }

}