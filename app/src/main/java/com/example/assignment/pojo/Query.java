package com.example.assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Query {
    @SerializedName("redirects")
    @Expose
    private List<Redirects> redirects = null;
    @SerializedName("pages")
    @Expose

    private List<WikiPage> pages = null;

    public List<Redirects> getRedirects() {
        return redirects;
    }

    public void setRedirects(List<Redirects> redirects) {
        this.redirects = redirects;
    }

    public List<WikiPage> getPages() {
        return pages;
    }

    public void setPages(List<WikiPage> pages) {
        this.pages = pages;
    }
}