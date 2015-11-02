package any.tv.mobile.gamerstm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.paging.gridview.PagingBaseAdapter;

import java.util.List;

import any.tv.mobile.gamerstm.models.Game;
import any.tv.mobile.gamerstm.models.Video;

/**
 * Created by adin234 on 11/2/15.
 */
public class VideoInfiniteAdapter extends PagingBaseAdapter<Video> {
    Context c;

    public VideoInfiniteAdapter(Context c, List<Video> videos) {
        this.c = c;
        this.items = videos;
    }

    public void setItems(List<Video> videos) {
        this.items = videos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Video getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return items.get(position).getView(c, convertView, parent);
    }
}