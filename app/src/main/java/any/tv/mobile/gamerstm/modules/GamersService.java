package any.tv.mobile.gamerstm.modules;

import java.util.List;

import any.tv.mobile.gamerstm.models.Game;
import any.tv.mobile.gamerstm.models.Streamer;
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
public interface GamersService {
    @GET("sliders")
    Call<List<Slider>> sliders();

    @GET("recommendgame")
    Call<List<VideoPile>> recommendedGames();

    @GET("videos")
    Call<List<Video>> getVideos(@Query("order") String order, @Query("limit") int limit);

    @GET("videos/{videoId}")
    Call<Video> getVideoDetail(@Path("videoId") String videoId);

    @GET("videos/search")
    Call<List<Video>> searchVideos(@Query("q") String query);

    @GET("videos/related")
    Call<List<Video>> getRelatedVideos(@Query("videoid") String query);

    @GET("games")
    Call<List<Game>> getGamesList(@Query("format") String format, @Query("page") int page, @Query("limit") int limit);

    @GET("games/{gameId}")
    Call<List<Video>> getGameVideo(@Path("gameId") String gameId, @Query("page") int page, @Query("limit") int limit);

    @GET("streamers/all")
    Call<List<Streamer>> getStreamers();

}
