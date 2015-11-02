package any.tv.mobile.gamerstm.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.paging.gridview.PagingGridView;

import java.util.ArrayList;
import java.util.List;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.adapters.GamesGridAdapter;
import any.tv.mobile.gamerstm.adapters.VideoInfiniteAdapter;
import any.tv.mobile.gamerstm.models.Game;
import any.tv.mobile.gamerstm.models.Video;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class GameVideosActivity extends BaseActivity {
    public int PAGE = 1, LIMIT = 12;

    private Game game;
    PagingGridView gridView;
    VideoInfiniteAdapter adapter;

    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_videos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (PagingGridView) findViewById(R.id.pagingGridView);
        gridView.setHasMoreItems(true);

        adapter = new VideoInfiniteAdapter(this, new ArrayList<Video>());

        game = (Game) getIntent().getExtras().getBundle("bundle").getSerializable("game");

        getSupportActionBar().setTitle(game.getGame_name().get(0).getTranslation()+ " " + "Videos");
    }

    @Override
    public void onResume() {
        super.onResume();

        gridView.setAdapter(adapter);

        gridView.setPagingableListener(new PagingGridView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                if (!isLoading) {
                    Call<List<Video>> call = ((Application) getApplication()).getGamersService().getGameVideo(game.getGame_id(), PAGE, LIMIT);

                    call.enqueue(new Callback<List<Video>>() {
                        @Override
                        public void onResponse(Response<List<Video>> response) {
                            if (response.body().size() < 1) {
                                gridView.onFinishLoading(false, null);
                            } else {
                                gridView.onFinishLoading(true, response.body());
                                ++PAGE;
                            }

                            isLoading = false;
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            isLoading = false;
                        }
                    });
                }
            }
        });
    }
}
