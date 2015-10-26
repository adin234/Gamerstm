package any.tv.mobile.gamerstm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import any.tv.mobile.gamerstm.models.Video;
import any.tv.mobile.gamerstm.models.VideoCategory;

/**
 * Created by adin234 on 10/19/15.
 */
public class HomeCategoriesItemAdapter extends ArrayAdapter<Video> {
    private Context c;
    private int resource;
    private List<Video> items;

    public HomeCategoriesItemAdapter(Context context, int resource, List<Video> objects) {
        super(context, resource, objects);
        this.items = objects;
        this.resource = resource;
        this.c = context;
    }

    @Override
    public int getCount() {
        return this.items != null ? this.items.size() : 0;
    }

    @Override
    public Video getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return this.getItem(position).getView(c, convertView, parent);
    }

    public void setItems(List<Video> items) {
        this.items = items;
    }
}
