package any.tv.mobile.gamerstm.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import any.tv.mobile.gamerstm.R;
import any.tv.mobile.gamerstm.models.Video;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link VideoDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoDetailFragment extends BaseFragment {
    private Video video;

    public static VideoDetailFragment newInstance() {
        VideoDetailFragment fragment = new VideoDetailFragment();
        return fragment;
    }

    public VideoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable("video") != null) {
            video = (Video) getArguments().getSerializable("video");

            Call<Video> call = getGamersService().getVideoDetail(video.getVideo_id());
            call.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Response<Video> response) {
                    displayData(response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (video != null) {
            displayData(video);
        }
    }

    public void displayData(Video vid) {
        video = vid;
        ((TextView) mainView.findViewById(R.id.videoDetail)).setText(""+vid.getDescription());
    }
}
