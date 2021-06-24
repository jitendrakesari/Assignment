package com.example.assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Continue {
    @SerializedName("gpsoffset")
    @Expose
    private Integer gpsoffset;
    @SerializedName("continue")
    @Expose
    private String _continue;

    public Integer getGpsoffset() {
        return gpsoffset;
    }

    public void setGpsoffset(Integer gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    public String get_continue() {
        return _continue;
    }

    public void set_continue(String _continue) {
        this._continue = _continue;
    }
}
