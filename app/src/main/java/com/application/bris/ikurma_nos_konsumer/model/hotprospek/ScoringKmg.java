package com.application.bris.ikurma_nos_konsumer.model.hotprospek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScoringKmg {
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("desc")
    @Expose
    private String desc;

@SerializedName("kesimpulan")
@Expose
private String kesimpulan;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(String kesimpulan) {
        this.kesimpulan = kesimpulan;
    }
}
