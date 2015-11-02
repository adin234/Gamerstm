package any.tv.mobile.gamerstm.modules;

import java.util.List;

import any.tv.mobile.gamerstm.models.Comment;
import any.tv.mobile.gamerstm.models.Video;
import any.tv.mobile.gamerstm.models.VideoPile;
import any.tv.mobile.gamerstm.models.Slider;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by adin234 on 21/09/2015.
 */
public interface CommentsService {
    @GET("video/comments")
    Call<List<Comment>> getVideoComments(@Query("videoid") String videoId);
}
