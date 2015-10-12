package any.tv.mobile.gamerstm.helpers;

import android.net.Uri;
import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Created by adin234 on 28/09/2015.
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request(),
                newRequest;

        URI urlReqeust = request.uri();
        Uri newUrlRequest = new Uri.Builder()
                .scheme(request.isHttps() ? "https" : "http")
                .encodedAuthority(urlReqeust.getRawAuthority())
                .path(urlReqeust.getPath())
                .query(urlReqeust.getQuery())
                .appendQueryParameter("android", "1")
                .build();

        newRequest = request.newBuilder().url(new URL(newUrlRequest.toString())).build();


        long t1 = System.nanoTime();
        Log.d("INTERCEPTOR", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), newRequest.headers()));

        Response response = chain.proceed(newRequest);

        long t2 = System.nanoTime();
        Log.d("INTERCEPTOR", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}