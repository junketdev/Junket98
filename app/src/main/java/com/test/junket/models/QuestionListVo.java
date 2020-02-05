package com.test.junket.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionListVo {

@SerializedName("result")
@Expose
private List<QuestionnaireResultVo> result = null;

public List<QuestionnaireResultVo> getResult() {
return result;
}

public void setResult(List<QuestionnaireResultVo> result) {
this.result = result;
}

}