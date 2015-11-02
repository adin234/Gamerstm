package any.tv.mobile.gamerstm.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.paging.gridview.PagingGridView;

import java.util.ArrayList;
import java.util.List;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.adapters.GamesGridAdapter;
import any.tv.mobile.gamerstm.models.Game;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class GamesListActivity extends BaseActivity {
    public int PAGE = 1, LIMIT = 12;

    PagingGridView gridView;
    GamesGridAdapter adapter;

    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        gridView = (PagingGridView) findViewById(R.id.pagingGridView);
        gridView.setHasMoreItems(true);

        adapter = new GamesGridAdapter(this, new ArrayList<Game>());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String gameId = ((Game) parent.getItemAtPosition(position)).getGame_id()


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        gridView.setAdapter(adapter);

        gridView.setPagingableListener(new PagingGridView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                Log.d("TEST CALLS", "CALLED");
                if (!isLoading) {
                    Log.d("TEST CALLS", "INSIDE");
                    isLoading = true;
                    Call<List<Game>> queue = ((Application) getApplication()).getGamersService().getGamesList("list", PAGE, LIMIT);
                    queue.enqueue(new Callback<List<Game>>() {
                        @Override
                        public void onResponse(Response<List<Game>> response) {
                            if (response.body().size() < 1) {
                                gridView.onFinishLoading(false, null);
                            } else {
                                gridView.onFinishLoading(true, response.body());
                                ++PAGE;
                            }

                            Log.d("TEST CALLS", "RETURNED");

                            isLoading = false;
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            gridView.onFinishLoading(false, null);
                            //isLoading = false;
                        }
                    });
                }
            }
        });

    }

}
