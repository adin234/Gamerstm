package any.tv.mobile.gamerstm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import any.tv.mobile.gamerstm.fragments.VideoDetailFragment;

/**
 * Created by adin234 on 10/26/15.
 */
public class VideoPageTabAdapter extends FragmentPagerAdapter {
    public VideoPageTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:

            case 1:

            case 2:
            default:
                return VideoDetailFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}
