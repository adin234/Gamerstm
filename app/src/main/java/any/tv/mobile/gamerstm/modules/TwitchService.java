package any.tv.mobile.gamerstm.modules;

import java.util.List;

import any.tv.mobile.gamerstm.models.Streamer;
import any.tv.mobile.gamerstm.models.twitch.TwitchToken;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by adin234 on 11/9/15.
 */
public interface TwitchService {
    @GET("channels/{channelId}/access_token")
    Call<TwitchToken> getTwitchToken(@Path("channelId") String channelId);
}
