package any.tv.mobile.gamerstm.models;

import com.google.gson.GsonBuilder;

/**
 * Created by adin234 on 10/26/15.
 */
public class Comment {
    private String title;
    private String content;
    private String username;
    private String userid;
    private String videoid;
    private CommentHeader header;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public CommentHeader getHeader() {
        return header;
    }

    public void setHeader(CommentHeader header) {
        this.header = header;
    }
}
