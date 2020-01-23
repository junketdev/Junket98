package com.test.junket.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelInfoVo {

@SerializedName("result")
@Expose
private List<HotelResultVo> result = null;

public List<HotelResultVo> getResult() {
return result;
}

public void setResult(List<HotelResultVo> result) {
this.result = result;
}

}