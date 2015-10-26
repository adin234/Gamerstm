package any.tv.mobile.gamerstm.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.activities.YoutubePlayerActivity;
import any.tv.mobile.gamerstm.adapters.HomeCategoriesItemAdapter;
import any.tv.mobile.gamerstm.helpers.ActivityHelper;
import any.tv.mobile.gamerstm.helpers.ViewHelper;

/**
 * Created by adin234 on 10/19/15.
 */
public class VideoCategory {
    private String name;
    private String drawableUrl;
    private List<Video> videos;
    private HomeCategoriesItemAdapter adapter;
    private ListView videosView;

    public VideoCategory(String name, String drawableUrl) {
        this.name = name;
        this.drawableUrl = drawableUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrawableUrl() {
        return drawableUrl;
    }

    public void setDrawableUrl(String drawableUrl) {
        this.drawableUrl = drawableUrl;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;

        if (adapter != null) {
            adapter.setItems(videos);
            adapter.notifyDataSetChanged();
            ViewHelper.setListViewHeightBasedOnItems(videosView);
        }
    }

    public View getView(final Context c, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(c);
            row = vi.inflate(R.layout.home_category, null);
        }

        final TextView label = (TextView) row.findViewById(R.id.categoryLabel);
        videosView = (ListView) row.findViewById(R.id.categoryItems);

        label.setText(this.getName());

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("TAG", "loaded");
                label.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(c.getResources(), bitmap), null, null, null);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("TAG", "onBitmapFailed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("TAG", "onPrepareLoad");
            }
        };

        Picasso.with(c).load(this.getDrawableUrl()).into(target);

        adapter = new HomeCategoriesItemAdapter(c, R.layout.home_category_item, getVideos());

        videosView.setAdapter(adapter);

        ViewHelper.setListViewHeightBasedOnItems(videosView);

        return row;
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }
}
