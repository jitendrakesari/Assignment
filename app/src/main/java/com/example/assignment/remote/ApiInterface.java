package com.example.assignment.remote;


import com.example.assignment.pojo.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(".")
    Call<Root> getSearchResult(@Query("action") String action, @Query("format") String format,
                                                            @Query("prop") String prop, @Query("generator") String generator,
                                                            @Query("redirects") int redirects, @Query("formatversion") int formatversion,
                                                            @Query("piprop") String piprop, @Query("pithumbsize") int pithumbsize,
                                                            @Query("pilimit") int pilimit, @Query("wbptterms") String wbptterms,
                                                            @Query("gpssearch") String gpssearch, @Query("gpslimit") int gpslimit);



}

