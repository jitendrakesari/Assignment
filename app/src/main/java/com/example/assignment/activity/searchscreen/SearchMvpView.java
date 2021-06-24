package com.example.assignment.activity.searchscreen;


import com.example.assignment.activity.baseactivity.MvpView;
import com.example.assignment.pojo.WikiPage;

import java.util.List;

public interface SearchMvpView extends MvpView {

    void afterSearchSuccess(List<WikiPage> wikiPageList,String searchText);
    void showNoData();
}
