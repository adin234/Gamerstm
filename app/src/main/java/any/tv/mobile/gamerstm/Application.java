package any.tv.mobile.gamerstm;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.orm.SugarContext;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Modifier;

import any.tv.mobile.gamerstm.helpers.LoggingInterceptor;
import any.tv.mobile.gamerstm.modules.CommentsService;
import any.tv.mobile.gamerstm.modules.GamersService;
import okio.Buffer;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by adin234 on 21/09/2015.
 */
public class Application extends android.app.Application {
    //protected RestAdapter restAdapter;
    private GamersService gamersService;
    private CommentsService commentsService;

    public Application() {
        super();

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());

        Retrofit gamersRetrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.13.249:3000/mobile_api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gamersService = gamersRetrofit.create(GamersService.class);

        Retrofit commentsRetrofit = new Retrofit.Builder()
                .baseUrl("http://beta.gamers.tm:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create()))
                .build();

        commentsService = commentsRetrofit.create(CommentsService.class);
    }

    public GamersService getGamersService() {
        return gamersService;
    }

    public CommentsService getCommentsService() {
        return commentsService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
