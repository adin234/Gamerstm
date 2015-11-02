package any.tv.mobile.gamerstm.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import any.tv.mobile.gamerstm.R;

/**
 * Created by adin234 on 11/2/15.
 */
public class Streamer extends BaseModel {
    private User user;
    private String type;
    private Stream stream;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public View getView(final Context c, View convertView, ViewGroup parent) {
        View row = convertView;
        final Streamer self = this;

        if (row == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(c);
            row = vi.inflate(R.layout.stream_item, null, false);
        }

        ((TextView) row.findViewById(R.id.usernameTextView)).setText("" + self.getUser().getUsername());
        ((TextView) row.findViewById(R.id.titleTextView)).setText("" + self.getStream().getDescription());

        Picasso.with(c)
                .load(self.getStream().getThumbnail().getMedium())
                .into((ImageView) row.findViewById(R.id.streamThumb));

        return row;
    }
}
