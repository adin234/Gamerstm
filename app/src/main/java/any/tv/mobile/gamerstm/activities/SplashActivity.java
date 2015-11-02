package any.tv.mobile.gamerstm.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;
import java.util.List;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.helpers.ActivityHelper;
import any.tv.mobile.gamerstm.models.Slider;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class SplashActivity extends ActionBarActivity {
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        c = this;
        Log.d("SPLASHACTIVITY", "WILL ENQUEUE");
        Call<List<Slider>> sliders =  ((Application) getApplication()).getGamersService().sliders();
        sliders.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Response<List<Slider>> response) {
                /*Iterator<Slider> i = response.body().iterator();

                Slider.deleteAll(Slider.class);

                while(i.hasNext()) {
                    i.next().save();
                }*/

                ActivityHelper.startActivity(HomeActivity.class, c);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("SPLASHACTIVITY", "ERROR LA " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
