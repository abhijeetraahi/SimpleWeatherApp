package com.raahi.simpleweatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Raahi on 09-07-2018.
 */

public class Condition_ implements Serializable {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("icon")
    @Expose
    private String icon;
    /*@SerializedName("code")
    @Expose
    private Integer code;*/

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /*public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }*/
}
