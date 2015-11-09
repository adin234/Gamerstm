package any.tv.mobile.gamerstm.activities;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.iheartradio.m3u8.Encoding;
import com.iheartradio.m3u8.Format;
import com.iheartradio.m3u8.ParsingMode;
import com.iheartradio.m3u8.PlaylistParser;
import com.iheartradio.m3u8.data.Playlist;
import com.iheartradio.m3u8.data.PlaylistData;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.models.Stream;
import any.tv.mobile.gamerstm.models.Streamer;
import any.tv.mobile.gamerstm.models.Video;
import any.tv.mobile.gamerstm.models.twitch.TwitchToken;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class TwitchStreamActivity extends BaseActivity {
    Streamer stream;
    WebView chatView;
    VideoView streamView;
    Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitch_stream);

        final MediaController mc = new MediaController(this);

        app = ((Application) getApplication());

        streamView = ((VideoView) findViewById(R.id.twitchStream));
        chatView = ((WebView) findViewById(R.id.twitchChat));

        WebSettings webSettings = chatView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        streamView.setMediaController(mc);

        stream = (Streamer) getIntent().getExtras().getBundle("bundle").getSerializable("stream");
        chatView.loadUrl(stream.getStream().getChat_url());

        getSupportActionBar().setTitle(stream.getStream().getDescription());

        Call<TwitchToken> call = app.getTwitchService().getTwitchToken(stream.getStream().getGamers_mobile_android_id());
        call.enqueue(new Callback<TwitchToken>() {
            @Override
            public void onResponse(Response<TwitchToken> response) {
                final TwitchToken token = response.body();

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Response<ResponseBody> _response = app.getUsherTwitchService().getPlaylist(stream.getStream().getGamers_mobile_android_id(),
                                URLEncoder.encode(token.getToken(), "UTF-8"), token.getSig()).execute();

                            ResponseBody streamedBody = _response.body();

                            String[] test = streamedBody.string().split("\n");
                            //medium quality
                            final String toPlay = test[10];

                            TwitchStreamActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("IS PLAYING", toPlay);
                                    streamView.setVideoURI(Uri.parse(toPlay));
                                    streamView.requestFocus();

                                    streamView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                                        public void onPrepared(MediaPlayer mp) {
                                            streamView.start();

                                        }
                                    });
                                }
                            });
                        } catch (Exception e) { e.printStackTrace(); /* YOLO */ }
                    }
                }.start();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().show();
        }
    }
}
