package any.tv.mobile.gamerstm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.models.Stream;
import any.tv.mobile.gamerstm.models.Streamer;
import any.tv.mobile.gamerstm.models.Video;

public class YoutubeStreamActivity extends BaseActivity implements
        YouTubePlayer.OnInitializedListener {

    public String API_KEY;
    private static final int RQS_ErrorDialog = 1;

    private YouTubePlayer youTubePlayer;
    private YouTubePlayerFragment youTubePlayerFragment;
    private Streamer video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_stream);

        API_KEY = getResources().getString(R.string.GOOGLE_API_KEY);

        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(API_KEY, this);

        video = (Streamer) getIntent().getExtras().getBundle("bundle").getSerializable("stream");

        getSupportActionBar().setTitle(video.getStream().getTitle());
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        youTubePlayer = player;

        if (!wasRestored) {
            player.cueVideo(video.getStream().getGamers_mobile_android_id());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this, RQS_ErrorDialog).show();
        } else {
            Toast.makeText(this,
                    "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        youTubePlayerFragment.initialize(API_KEY, this);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (youTubePlayer!=null) {
            youTubePlayer.release();
        }
    }
}
