package any.tv.mobile.gamerstm.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.adapters.StreamersListAdapter;
import any.tv.mobile.gamerstm.models.Stream;
import any.tv.mobile.gamerstm.models.Streamer;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class StreamersListActivity extends BaseActivity {
    private ListView streamersList;
    private StreamersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamers_list);

        streamersList = (ListView) findViewById(R.id.streamersList);
        adapter = new StreamersListAdapter(this, R.layout.stream_item, new ArrayList<Streamer>());

        streamersList.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        Call<List<Streamer>> call = ((Application) getApplication()).getGamersService().getStreamers();
        call.enqueue(new Callback<List<Streamer>>() {
            @Override
            public void onResponse(Response<List<Streamer>> response) {
                adapter.setItems(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
