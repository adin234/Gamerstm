package any.tv.mobile.gamerstm.modules;

import java.util.List;

import any.tv.mobile.gamerstm.models.RecommendedGame;
import any.tv.mobile.gamerstm.models.Slider;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by adin234 on 21/09/2015.
 */
public interface GamersService {
    @GET("sliders")
    Call<List<Slider>> sliders();

    @GET("recommendgame")
    Call<List<RecommendedGame>> recommendedGames();
}
