package any.tv.mobile.gamerstm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import any.tv.mobile.gamerstm.models.Streamer;
import any.tv.mobile.gamerstm.models.VideoCategory;

/**
 * Created by adin234 on 11/2/15.
 */
public class StreamersListAdapter extends ArrayAdapter<Streamer> {
    private Context c;
    private int resource;
    private List<Streamer> items;

    public StreamersListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public StreamersListAdapter(Context context, int resource, List<Streamer> items) {
        super(context, resource, items);
        this.c = context;
        this.resource = resource;
        this.items = items;
    }

    public void setItems(List<Streamer> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Streamer getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return this.getItem(position).getView(c, convertView, parent);
    }
}
