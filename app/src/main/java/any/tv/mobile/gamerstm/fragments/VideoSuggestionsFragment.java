package any.tv.mobile.gamerstm.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.adapters.HomeCategoriesItemAdapter;
import any.tv.mobile.gamerstm.models.Video;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideoSuggestionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoSuggestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoSuggestionsFragment extends BaseFragment {
    private List<Video> videos;
    private Video video;
    private HomeCategoriesItemAdapter adapter;
    private ListView videoList;

    public static VideoSuggestionsFragment newInstance() {
        VideoSuggestionsFragment fragment = new VideoSuggestionsFragment();
        return fragment;
    }

    public VideoSuggestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new HomeCategoriesItemAdapter(c, R.layout.home_category_item, new ArrayList<Video>());

        if (getArguments() != null && getArguments().getSerializable("video") != null) {
            video = (Video) getArguments().getSerializable("video");

            Call<Video> call = getGamersService().getVideoDetail(video.getVideo_id());
            call.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Response<Video> response) {
                    video = response.body();
                    if (video != null) {
                        Call<List<Video>> list = getGamersService().getRelatedVideos(video.getVideo_id());
                        list.enqueue(new Callback<List<Video>>() {
                            @Override
                            public void onResponse(Response<List<Video>> response) {
                                displayData(response.body());
                            }

                            @Override
                            public void onFailure(Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        ((ListView) mainView.findViewById(R.id.videoList)).setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_suggestions, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videos != null) {
            displayData(videos);
        }
    }

    public void displayData(List<Video> vids) {
        videos = vids;
        adapter.setItems(vids);
        adapter.notifyDataSetChanged();
    }
}
