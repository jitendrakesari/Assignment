package com.example.assignment.activity.searchscreen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assignment.R;
import com.example.assignment.activity.baseactivity.BaseActivity;
import com.example.assignment.adapter.SearchAdapter;
import com.example.assignment.networkutil.ConnectionDetector;
import com.example.assignment.pojo.WikiPage;
import java.util.List;

public class SearchActivity extends BaseActivity implements  SearchMvpView {

    private AutoCompleteTextView inputField;
    private RecyclerView recyclerView;
    private RelativeLayout no_data;
    private List<WikiPage> listOfWikiPages;
    private SearchAdapter adapter;
    private ImageView wikiImage;
    private ProgressBar progressBar;
    private SearchPresenter searchPresenter;
    private ImageView crossIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void init() {
        inputField = findViewById(R.id.et_input);
        recyclerView = findViewById(R.id.recyclerview);
        no_data = findViewById(R.id.no_data_found_layout);
        wikiImage = findViewById(R.id.wiki_image);
        progressBar = findViewById(R.id.progress_bar);
        crossIcon = findViewById(R.id.cross_icon);
        searchPresenter = new SearchPresenter();
        searchPresenter.onAttach(this);
        setListeners();
    }

    private void setListeners() {
        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (new ConnectionDetector(SearchActivity.this).isNetworkConnectionAvailable()) {
                    searchData();
                }else{
                    showToast(R.string.check_internet_connection);
                }
            }
        });

        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        crossIcon.setOnClickListener(v -> {
            inputField.clearListSelection();
            inputField.setText("");
            hideKeyboard();
        });
    }

    private void searchData(){
        String searchText = inputField.getText().toString().trim();
        if (!searchText.equalsIgnoreCase("")) {
            searchPresenter.hitServerForGettingSearchResult(searchText,SearchActivity.this);
            recyclerView.setVisibility(View.VISIBLE);
            wikiImage.setVisibility(View.GONE);
        } else {
            listOfWikiPages.clear();
            adapter.notifyDataSetChanged();
            wikiImage.setVisibility(View.VISIBLE);
        }
    }

    private void setRecyclerView(List<WikiPage> listOfWikiPages, String searchText) {
        if (listOfWikiPages != null && listOfWikiPages.size() != 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new SearchAdapter(listOfWikiPages, this, searchText);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showNoData() {
        recyclerView.setVisibility(View.GONE);
        no_data.setVisibility(View.VISIBLE);
        wikiImage.setVisibility(View.GONE);
    }


    @Override
    public void afterSearchSuccess(List<WikiPage> listOfWikiPages,String searchText) {
        this.listOfWikiPages = listOfWikiPages;
        setRecyclerView(listOfWikiPages, searchText);
        wikiImage.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_text)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
