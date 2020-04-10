package com.test.junket.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectDetailsVo {

@SerializedName("result")
@Expose
private List<ObjectDetailsResultVo> result = null;

public List<ObjectDetailsResultVo> getResult() {
return result;
}

public void setResult(List<ObjectDetailsResultVo> result) {
this.result = result;
}

}