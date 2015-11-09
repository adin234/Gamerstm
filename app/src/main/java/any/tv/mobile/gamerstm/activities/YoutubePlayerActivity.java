package any.tv.mobile.gamerstm.activities;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.adapters.VideoPageTabAdapter;
import any.tv.mobile.gamerstm.fragments.VideoCommentsFragment;
import any.tv.mobile.gamerstm.fragments.VideoDetailFragment;
import any.tv.mobile.gamerstm.fragments.VideoSuggestionsFragment;
import any.tv.mobile.gamerstm.models.Video;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class YoutubePlayerActivity extends BaseActivity implements
        YouTubePlayer.OnInitializedListener {
    public String API_KEY;

    private static final int RQS_ErrorDialog = 1;

    private YouTubePlayer youTubePlayer;
    private YouTubePlayerFragment youTubePlayerFragment;
    private Video video;

    private String[] tabs = { "Details", "Comments", "Suggestions" };

    private ViewPager pager;
    private FragmentPagerItemAdapter adapter;
    private SmartTabLayout viewPagerTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        API_KEY = getResources().getString(R.string.GOOGLE_API_KEY);

        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(API_KEY, this);

        video = (Video) getIntent().getExtras().getBundle("bundle").getSerializable("video");

        pager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Details", VideoDetailFragment.class, new Bundler().putSerializable("video", video).get())
                .add("Comments", VideoCommentsFragment.class, new Bundler().putSerializable("video", video).get())
                .add("Suggestions",  VideoSuggestionsFragment.class, new Bundler().putSerializable("video", video).get())
                .create());

        pager.setAdapter(adapter);
        viewPagerTab.setViewPager(pager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(video.getVideo_title());


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        youTubePlayer = player;

        if (!wasRestored) {
            player.cueVideo(video.getVideo_id());
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("THIS IS STUPID", "NO?");
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().show();
        }
    }
}
