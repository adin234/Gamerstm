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

        String[] queries = urlReqeust.getRawQuery() != null ? urlReqeust.getRawQuery().split("&") : null;
        Uri.Builder builder = new Uri.Builder()
                .scheme(request.isHttps() ? "https" : "http")
                .encodedAuthority(urlReqeust.getRawAuthority())
                .path(urlReqeust.getRawPath())
                //.query(urlReqeust.getRawQuery())
                .appendQueryParameter("android", "1");

        if (queries != null) {
            for (String query : queries) {
                String[] values = query.split("=");
                builder.appendQueryParameter(values[0], values.length > 1 ? values[1] : null);
            }
        }

        Uri newUrlRequest = builder.build();

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