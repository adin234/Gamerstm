package any.tv.mobile.gamerstm.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import any.tv.mobile.gamerstm.R;

/**
 * Created by adin234 on 11/2/15.
 */
public class Stream extends BaseModel {
    private String gamers_mobile_android_id;
    private String stream_url;
    private Thumbnail thumbnail;
    private String date;
    private String description;
    private String title;
    private int views;
    private String chat_url;
    private String game_name;
    private int channel_views;
    private int channel_followers;
    private String about;

    public String getGamers_mobile_android_id() {
        return gamers_mobile_android_id;
    }

    public void setGamers_mobile_android_id(String gamers_mobile_android_id) {
        this.gamers_mobile_android_id = gamers_mobile_android_id;
    }

    public String getStream_url() {
        return stream_url;
    }

    public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getChat_url() {
        return chat_url;
    }

    public void setChat_url(String chat_url) {
        this.chat_url = chat_url;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public int getChannel_views() {
        return channel_views;
    }

    public void setChannel_views(int channel_views) {
        this.channel_views = channel_views;
    }

    public int getChannel_followers() {
        return channel_followers;
    }

    public void setChannel_followers(int channel_followers) {
        this.channel_followers = channel_followers;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public class Thumbnail {
        private String medium;
        private String large;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }
    }
}
