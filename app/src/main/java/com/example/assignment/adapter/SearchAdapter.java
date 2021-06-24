package com.example.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.activity.WikiWebActivity;
import com.example.assignment.pojo.WikiPage;

import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ResultsViewHolder> {

    private List<WikiPage> wikiList;
    private Context context;
    private String searchText;

    public class ResultsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView image;
        public LinearLayout main_layout;

        public ResultsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            image = (ImageView)view.findViewById(R.id.item_image);
            main_layout = (LinearLayout)view.findViewById(R.id.main_layout);
        }
    }

    public SearchAdapter(List<WikiPage> wikiList, Context context, String searchText) {
        this.wikiList = wikiList;
        this.context = context;
        this.searchText = searchText;
    }


    @Override
    public void onBindViewHolder(ResultsViewHolder holder, int position) {
        final WikiPage item = wikiList.get(position);
        String description = "";
        String fullText = item.getTitle();
        if (searchText != null && !searchText.isEmpty()) {
            int startPos = fullText.toLowerCase(Locale.US).indexOf(searchText.toLowerCase(Locale.US));
            int endPos = startPos + searchText.length();
            if (startPos != -1) {
                Spannable spannable = new SpannableString(fullText);
                ColorStateList blueColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{Color.BLUE});
                TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);
                spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.title.setText(spannable);
            } else {
                holder.title.setText(fullText);
            }
        } else {
            holder.title.setText(fullText);
        }

        if (item != null) {
            if (item.getTerms() != null) {
                if (!item.getTerms().getDescription().isEmpty() && item.getTerms().getDescription() != null &&
                        item.getTerms().getDescription().size() != 0)
                    description = String.valueOf(item.getTerms().getDescription());
                    holder.description.setText(description);
            }

            if (item.getThumbnail()!= null) {
                if (item.getThumbnail().getSource() != "" && item.getThumbnail().getSource() != null) {
                    String s1 =item.getThumbnail().getSource().substring(item.getThumbnail().getSource().lastIndexOf("/"));
                    s1 = s1.substring(1,s1.indexOf('-'));

                    s1 = item.getThumbnail().getSource().replace(s1, "100px");
                    System.out.println("aa ------------------------------------------------------       " + s1);

                    Glide.with(context)
                            .load(s1)
                            .placeholder(R.drawable.wk2)
                            .into(holder.image);


                }
            }
        }

       holder.main_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String title = item.getTitle().trim().replaceAll(" ", "_");
               Intent i = new Intent(context, WikiWebActivity.class);
               i.putExtra(context.getResources().getString(R.string.title), title);
               context.startActivity(i);

           }
       });
    }

    @Override
    public int getItemCount() {
        return wikiList.size();
    }


    @Override
    public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_adapter,parent, false);
        return new ResultsViewHolder(v);
    }
}