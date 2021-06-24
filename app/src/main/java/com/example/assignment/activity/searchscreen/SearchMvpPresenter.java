package com.example.assignment.activity.searchscreen;

import android.content.Context;

import com.example.assignment.activity.baseactivity.MvpPresenter;

public interface SearchMvpPresenter<V extends SearchMvpView> extends MvpPresenter<V> {
    void hitServerForGettingSearchResult(String searchText, Context context);
}
