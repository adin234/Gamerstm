package any.tv.mobile.gamerstm.models;

import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Column;

/**
 * Created by adin234 on 21/09/2015.
 */
public class Slider extends BaseModel {
    private String gamers_mobile_android_id;
    private String image_hq;
    private String image_small;
    private String title;
    private String youtube_video;
    private String link;
    private String description;
    private int priority;

    public Slider() {
        super();
    }

    public String getGamers_mobile_android_id() {
        return gamers_mobile_android_id;
    }

    public void setGamers_mobile_android_id(String id) {
        this.gamers_mobile_android_id = id;
    }

    public String getImage_hq() {
        return image_hq;
    }

    public void setImage_hq(String image_hq) {
        this.image_hq = image_hq;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutube_video() {
        return youtube_video;
    }

    public void setYoutube_video(String youtube_video) {
        this.youtube_video = youtube_video;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
