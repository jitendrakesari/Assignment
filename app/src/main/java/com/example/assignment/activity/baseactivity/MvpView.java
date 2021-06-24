package com.example.assignment.activity.baseactivity;
import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

public interface MvpView {
    void init();
    void showSnackBar(@StringRes int resId);
    void showSnackBar(String message, @ColorRes int color);
    void showSnackBar(String message);
    void showError(@StringRes int resId);
    void showError(String message);
    void showSuccess(String message);
    void showSuccess(@StringRes int resId);
    void showToast(@StringRes int resId);
    boolean isNetworkConnected();
    void hideKeyboard();
    void showLoading();
    void hideLoading();
    void performUserLogout();
    void performUserLogoutApi(String apitype, int errorCode);
}

