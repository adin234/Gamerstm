package any.tv.mobile.gamerstm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.paging.gridview.PagingBaseAdapter;

import java.util.List;

import any.tv.mobile.gamerstm.models.Game;

/**
 * Created by adin234 on 11/2/15.
 */
public class GamesGridAdapter extends PagingBaseAdapter<Game> {
    Context c;

    public GamesGridAdapter(Context c, List<Game> games) {
        this.c = c;
        this.items = games;
    }

    public void setItems(List<Game> games) {
        this.items = games;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Game getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return items.get(position).getGridView(c, convertView, parent);
    }
}
