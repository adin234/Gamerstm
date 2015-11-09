package any.tv.mobile.gamerstm.modules;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Streaming;

/**
 * Created by adin234 on 11/9/15.
 */
public interface HitboxService {
    @GET("player/hls/{channelId}.m3u8")
    @Streaming
    Call<ResponseBody> getPlaylist(@Path("channelId") String channelId);
}
