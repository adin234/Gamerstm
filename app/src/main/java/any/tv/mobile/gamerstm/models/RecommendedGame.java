package any.tv.mobile.gamerstm.models;

import com.orm.dsl.Ignore;

import java.util.List;

/**
 * Created by adin234 on 28/09/2015.
 */
public class RecommendedGame extends BaseModel {
    private String gamers_mobile_android_id;
    private String game_id;
    private String image_source;
    private String platforms;
    private String tags;
    private int video_count;
    private String type;
    @Ignore
    private List<Video> videos;
    @Ignore
    private List<GamersTranslation> game_name;

    public RecommendedGame() {}

    public String getGamers_mobile_android_id() {
        return gamers_mobile_android_id;
    }

    public void setGamers_mobile_android_id(String gamers_mobile_android_id) {
        this.gamers_mobile_android_id = gamers_mobile_android_id;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
