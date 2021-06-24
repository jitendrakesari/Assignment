package com.example.assignment.activity.baseactivity;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}
