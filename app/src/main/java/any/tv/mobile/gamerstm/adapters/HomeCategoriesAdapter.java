package any.tv.mobile.gamerstm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

import any.tv.mobile.gamerstm.models.VideoCategory;
import any.tv.mobile.gamerstm.models.VideoPile;

/**
 * Created by adin234 on 10/19/15.
 */
public class HomeCategoriesAdapter extends ArrayAdapter<VideoCategory> {
    private List<VideoCategory> categoryList;
    private int resouce;
    private Context c;

    public HomeCategoriesAdapter(Context context, int resource) {
        super(context, resource);
    }

    public HomeCategoriesAdapter(Context context, int resource, List<VideoCategory> categoryList) {
        super(context, resource, categoryList);
        this.c = context;
        this.resouce = resource;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return this.categoryList.size();
    }

    @Override
    public VideoCategory getItem(int position) {
        return this.categoryList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return this.getItem(position).getView(c, convertView, parent);
    }
}
