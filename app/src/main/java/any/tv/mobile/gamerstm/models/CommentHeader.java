package any.tv.mobile.gamerstm.models;

/**
 * Created by adin234 on 10/26/15.
 */
public class CommentHeader {
    private String avatar;
    private String date_posted;
    private String next_page_token;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(String date_posted) {
        this.date_posted = date_posted;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }
}
