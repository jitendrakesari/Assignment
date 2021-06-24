package com.example.assignment.activity.baseactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment.R;
import com.example.assignment.networkutil.ConnectionDetector;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {
    private static String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        if (newBase.getResources().getConfiguration().fontScale > 1.15f) {
            final Configuration override = new Configuration(newBase.getResources().getConfiguration());
            override.fontScale = 1.15f;
            applyOverrideConfiguration(override);
        }
        Log.e(TAG, "attachBaseContext: Configuration > " + getResources().getConfiguration().fontScale);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showToast(int resId) {
        String message = getString(resId);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void showSuccess(String message) {

    }

    public void showTimeoutScreen() {

    }

    @Override
    public void performUserLogout() {

    }

    @Override
    public void performUserLogoutApi(String apitype, int errorCode) {

    }


    @Override
    public void showSuccess(int resId) {
        showSuccess(getString(resId));
    }

    @Override
    public void showError(@StringRes int resId) {
        showError(getString(resId));
    }

    @Override
    public void showSnackBar(String message) {
        showSnackBar(message, R.color.colorAccent);
    }

    @Override
    public void showSnackBar(String message, @ColorRes int backgroundColor) {

    }

    @Override
    public void showSnackBar(@StringRes int resId) {
    }

    @Override
    public boolean isNetworkConnected() {
        return new ConnectionDetector(getApplicationContext()).isNetworkConnectionAvailable();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void init() {

    }

}

