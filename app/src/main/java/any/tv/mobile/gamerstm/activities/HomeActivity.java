package any.tv.mobile.gamerstm.activities;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import any.tv.mobile.gamerstm.Application;
import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.adapters.HomeCategoriesAdapter;
import any.tv.mobile.gamerstm.helpers.ActivityHelper;
import any.tv.mobile.gamerstm.helpers.ViewHelper;
import any.tv.mobile.gamerstm.models.Slider;
import any.tv.mobile.gamerstm.models.Video;
import any.tv.mobile.gamerstm.models.VideoCategory;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class HomeActivity extends BaseActivity {
    private SliderLayout topSlider;
    private ListView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        topSlider = (SliderLayout) findViewById(R.id.slider);
        categories = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final List<VideoCategory> test = new ArrayList<VideoCategory>();
        final VideoCategory featured = new VideoCategory("Featured", "http://cdn2.gamers.tm/user_avatars/57991bc5-18de-483d-807e-bd17710ad77a.jpg");
        final VideoCategory popular = new VideoCategory("Popular", "http://cdn2.gamers.tm/user_avatars/57991bc5-18de-483d-807e-bd17710ad77a.jpg");
        final VideoCategory latest = new VideoCategory("Latest", "http://cdn2.gamers.tm/user_avatars/57991bc5-18de-483d-807e-bd17710ad77a.jpg");

        test.add(featured);
        test.add(latest);
        test.add(popular);

        Call<List<Video>> featuredVideos =  ((Application) getApplication()).getGamersService().getVideos("ftd", 6);
        featuredVideos.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Response<List<Video>> response) {
                test.get(0).setVideos(response.body());
                ViewHelper.setListViewHeightBasedOnItems(categories);
            }

            @Override
            public void onFailure(Throwable t) {
                //Log.d("SPLASHACTIVITY", "ERROR LA " + t.getMessage());
            }
        });

        Call<List<Video>> popularVideos =  ((Application) getApplication()).getGamersService().getVideos("popular", 6);
        popularVideos.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Response<List<Video>> response) {
                test.get(1).setVideos(response.body());
                ViewHelper.setListViewHeightBasedOnItems(categories);
            }

            @Override
            public void onFailure(Throwable t) {
                //Log.d("SPLASHACTIVITY", "ERROR LA " + t.getMessage());
            }
        });

        Call<List<Video>> latestVideos =  ((Application) getApplication()).getGamersService().getVideos("latest", 6);
        latestVideos.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Response<List<Video>> response) {
                test.get(2).setVideos(response.body());
                ViewHelper.setListViewHeightBasedOnItems(categories);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("SPLASHACTIVITY", "ERROR LA " + t.toString());
            }
        });

        HomeCategoriesAdapter adapter = new HomeCategoriesAdapter(c, R.layout.home_category, test);

        categories.setAdapter(adapter);
        categories.setDividerHeight(0);

        ViewHelper.setListViewHeightBasedOnItems(categories);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void getData() {
        /*List<Slider> sliders = Slider.find(Slider.class, "1");

        if (sliders.size() > 0) {
            displaySlider(sliders);
        }*/

        Call<List<Slider>> sliders =  ((Application) getApplication()).getGamersService().sliders();
        sliders.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Response<List<Slider>> response) {
                displaySlider(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void displaySlider(List<Slider> sliders) {
        HashMap<String, String> images = new HashMap<String, String>();
        Iterator<Slider> iterator = sliders.iterator();

        while(iterator.hasNext()) {
            Slider current = iterator.next();
            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView
                    .description(current.getDescription())
                    .image(current.getImage_small())
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putSerializable("self", current);

            topSlider.addSlider(textSliderView);
        }

        topSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        topSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        topSlider.setDuration(2000);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shareButton) {
            final EditText editText = new EditText(this);
            prompt("Message", editText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("I ARE CLICKED", "" + editText.getText().toString());
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
    }
}
