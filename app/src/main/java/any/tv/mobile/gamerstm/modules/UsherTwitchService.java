package any.tv.mobile.gamerstm.modules;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Streaming;

/**
 * Created by adin234 on 11/9/15.
 */
public interface UsherTwitchService {
    @GET("channel/hls/{channelId}.m3u8?player=twitchweb&allow_source=true&type=any&p=gamers.tm")
    @Streaming
    Call<ResponseBody> getPlaylist(@Path("channelId") String channelId, @Query(value = "token", encoded = true) String token,
                             @Query("sig") String sig);

}
