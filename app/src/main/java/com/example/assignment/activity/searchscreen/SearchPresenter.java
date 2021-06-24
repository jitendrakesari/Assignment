package com.example.assignment.activity.searchscreen;

import android.content.Context;

import com.example.assignment.Constants;
import com.example.assignment.activity.baseactivity.BasePresenter;
import com.example.assignment.pojo.Query;
import com.example.assignment.pojo.Root;
import com.example.assignment.remote.ApiClient;
import com.example.assignment.remote.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter<V extends SearchMvpView> extends BasePresenter<V>
        implements SearchMvpPresenter<V> {

    @Override
    public void hitServerForGettingSearchResult(String searchText, Context context) {
        getMvpView().showLoading();
        ApiInterface apiService = ApiClient.getClient(context).create(ApiInterface.class);
        Call<Root> call = apiService.getSearchResult(Constants.QUERY, Constants.FORMAT, Constants.PROP,
                Constants.GENERATOR, Constants.REDIRECTS, Constants.FORMATVIRSION, Constants.THUMBNAIL, Constants.PITHUMBSIZE,
                Constants.PILIMIT, Constants.DESCRIPTION, searchText, Constants.GPSLIMIT);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.body() != null) {
                    Root root = response.body();
                    Query query = root.getQuery();
                    if (query != null) {
                        query.getPages();
                        getMvpView().afterSearchSuccess(query.getPages(),searchText);
                        getMvpView().hideLoading();
                    }else{
                        getMvpView().showNoData();
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                //TODO write here server failure message
                getMvpView().hideLoading();
            }
        });


    }

}
