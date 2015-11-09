package any.tv.mobile.gamerstm.models;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.activities.BaseActivity;
import any.tv.mobile.gamerstm.activities.HitboxStreamActivity;
import any.tv.mobile.gamerstm.activities.TwitchStreamActivity;
import any.tv.mobile.gamerstm.activities.YoutubePlayerActivity;
import any.tv.mobile.gamerstm.activities.YoutubeStreamActivity;
import any.tv.mobile.gamerstm.helpers.ActivityHelper;

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
        String title = !getType().equals("youtube") ? self.getStream().getDescription() : self.getStream().getTitle();

        if (row == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(c);
            row = vi.inflate(R.layout.stream_item, null, false);
        }

        ((TextView) row.findViewById(R.id.usernameTextView)).setText("" + self.getUser().getUsername());
        ((TextView) row.findViewById(R.id.titleTextView)).setText("" + title);

        Picasso.with(c)
                .load(self.getStream().getThumbnail().getLarge())
                .fit()
                .into((ImageView) row.findViewById(R.id.streamThumb));

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class toRun;
                Bundle b = new Bundle();
                b.putSerializable("stream", self);

                switch (getType()) {
                    case "twitch":
                        toRun = TwitchStreamActivity.class;
                        break;
                    case "hitbox":
                        toRun = HitboxStreamActivity.class;
                        break;
                    default:
                    case "youtube":
                        toRun = YoutubeStreamActivity.class;
                        break;
                }

                ActivityHelper.startActivity(toRun, c, b);
            }
        });

        return row;
    }
}
