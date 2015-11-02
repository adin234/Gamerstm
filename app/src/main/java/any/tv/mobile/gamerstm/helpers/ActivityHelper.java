package any.tv.mobile.gamerstm.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import any.tv.mobile.gamerstm.activities.BaseActivity;

/**
 * Created by adin234 on 10/19/15.
 */
public class ActivityHelper {
    public static void startActivity(Class a, Context c, Boolean stacked, Bundle bundle) {
        if (!a.getName().equals(c.getClass().getName()) || !isSingleton(c.getClass().getName())) {
            Intent i = new Intent(c, a);
            if (!stacked) {
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            }

            i.putExtra("bundle", bundle);

            c.startActivity(i);
        }
    }

    public static void startActivity(Class a, Context c, Bundle bundle) {
        startActivity(a, c, true, bundle);
    }

    public static void startActivity(Class a, Context c) {
        startActivity(a, c, true, null);
    }

    public static boolean isSingleton(String classname) {
        switch (classname) {
            case "any.tv.mobile.gamerstm.activities.HomeActivity":
            case "any.tv.mobile.gamerstm.activities.GamesListActivity":
                return true;
            default:
                return false;
        }
    }
}
