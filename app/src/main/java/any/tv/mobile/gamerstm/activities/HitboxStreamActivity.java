package any.tv.mobile.gamerstm.activities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.squareup.okhttp.ResponseBody;

import java.net.URLEncoder;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.helpers.CustomMediaController;
import any.tv.mobile.gamerstm.models.Streamer;
import retrofit.Response;

public class HitboxStreamActivity extends AppCompatActivity {
    Streamer stream;
    WebView chatView;
    VideoView streamView;
    Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitbox_stream);

        final CustomMediaController mc = new CustomMediaController(this);

        mc.setListener(new CustomMediaController.OnMediaControllerInteractionListener() {
            @Override
            public void onRequestFullScreen() {
                DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
                android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) streamView.getLayoutParams();
                params.width =  metrics.widthPixels;
                params.height = metrics.heightPixels;
                params.leftMargin = 0;
                streamView.setLayoutParams(params);

            }
        });

        app = (Application) getApplication();

        streamView = ((VideoView) findViewById(R.id.hitboxStream));
        chatView = ((WebView) findViewById(R.id.hitboxChat));
        streamView.setMediaController(mc);

        WebSettings webSettings = chatView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        stream = (Streamer) getIntent().getExtras().getBundle("bundle").getSerializable("stream");

        getSupportActionBar().setTitle(stream.getStream().getDescription());

        new Thread() {
            @Override
            public void run() {
                try {
                    Response<ResponseBody> _response = app.getHitboxService()
                            .getPlaylist(stream.getStream().getGamers_mobile_android_id()).execute();

                    ResponseBody streamedBody = _response.body();

                    String[] test = streamedBody.string().split("\n");
                    //medium quality
                    final String toPlay = test[5];

                    HitboxStreamActivity.this.runOnUiThread(new Runnable() {
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

        chatView.loadUrl(stream.getStream().getChat_url());
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
