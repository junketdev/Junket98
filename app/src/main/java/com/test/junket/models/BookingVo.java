package com.test.junket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingVo {
    @SerializedName("result")
    @Expose
    private List<BookingResultVo> result = null;

    public List<BookingResultVo> getResult() {
        return result;
    }

    public void setResult(List<BookingResultVo> result) {
        this.result = result;
    }
}
