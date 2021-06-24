package com.example.assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Root {
    @SerializedName("batchcomplete")
    @Expose
    private Boolean batchcomplete;
    @SerializedName("continue")
    @Expose
    private Continue _continue;
    @SerializedName("query")
    @Expose
    private Query query;


    public void setBatchcomplete(boolean batchcomplete){
        this.batchcomplete = batchcomplete;
    }

    public boolean getBatchcomplete(){
        return this.batchcomplete;
    }

    public void setQuery(Query query){
        this.query = query;
    }
    public Query getQuery(){
        return this.query;
    }
}
