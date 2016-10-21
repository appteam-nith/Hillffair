package appteam.nith.hillffair.utilities;

import appteam.nith.hillffair.application.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jatin on 9/13/2016.
 */
public class Utils {

    public static boolean checkData(String string) {
        return !string.isEmpty() && string.trim().length() != 0;
    }

    // Created A Static Retrofit Service Method For Getting reference to the retrofit service method

    public  static APIINTERFACE getRetrofitService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        oBuilder.addNetworkInterceptor(loggingInterceptor);
        oBuilder.connectTimeout(15l, TimeUnit.SECONDS);
        oBuilder.readTimeout(15l,TimeUnit.SECONDS);
// code to add cache in retrofit

        oBuilder.cache(new Cache(new File(MyApplication.getAppContext().getCacheDir(),"cache"),10*1024*1024));
        oBuilder.addInterceptor(new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (new Connection(MyApplication.getAppContext()).isInternet()) {
                    request = request.newBuilder().header("Cache-Control", "public, max-age=" + 36000).build();
                } else {
                    request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                }
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api-hillfair-2k16.herokuapp.com/api/app/").addConverterFactory(GsonConverterFactory.create()).client(oBuilder.build()).build();
        APIINTERFACE service = retrofit.create(APIINTERFACE.class);
        return service;
    }
}


