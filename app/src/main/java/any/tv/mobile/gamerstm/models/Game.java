package any.tv.mobile.gamerstm.models;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.activities.GameVideosActivity;
import any.tv.mobile.gamerstm.activities.YoutubePlayerActivity;
import any.tv.mobile.gamerstm.helpers.ActivityHelper;

/**
 * Created by adin234 on 11/2/15.
 */
public class Game extends BaseModel {
    private String game_id;
    private String image_source;
    private List<GamersTranslation> game_name;
    private String platforms;
    private String tags;
    private int video_count;
    private String gamers_mobile_android_id;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public List<GamersTranslation> getGame_name() {
        return game_name;
    }

    public void setGame_name(List<GamersTranslation> game_name) {
        this.game_name = game_name;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getVideo_count() {
        return video_count;
    }

    public void setVideo_count(int video_count) {
        this.video_count = video_count;
    }

    public String getGamers_mobile_android_id() {
        return gamers_mobile_android_id;
    }

    public void setGamers_mobile_android_id(String gamers_mobile_android_id) {
        this.gamers_mobile_android_id = gamers_mobile_android_id;
    }

    public View getGridView(final Context c, View convertView, ViewGroup parent) {
        View row = convertView;
        final ImageView view;
        final Game self = this;

        if (row == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(c);
            row = vi.inflate(R.layout.game_grid_item, null, false);
        }

        view = (ImageView) row.findViewById(R.id.gameImage);

        Picasso.with(c)
                .load(self.getImage_source())
                .fit()
                .into(view);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("game", self);
                ActivityHelper.startActivity(GameVideosActivity.class, c, b);
            }
        });
        return row;
    }
}
