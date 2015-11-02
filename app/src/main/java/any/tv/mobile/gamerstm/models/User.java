package any.tv.mobile.gamerstm.models;

/**
 * Created by adin234 on 11/2/15.
 */
public class User extends BaseModel {
    private String user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String avatar;
    private String stream_banner;
    private String about;
    private int community_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStream_banner() {
        return stream_banner;
    }

    public void setStream_banner(String stream_banner) {
        this.stream_banner = stream_banner;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }
}
