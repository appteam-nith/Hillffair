package com.appteamnith.hillffair;

/**
 * Created by Jatin on 9/11/2016.
 */

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface APIINTERFACE {

    @GET("api/{email}/{password}")

    Call<Login> authenticate(@Path("email") String email, @Path("password") String password);

    @POST("api/{email}/{password}/{rollno}/{name}")

    Call<Login> registration(@Path("email") String email, @Path("password") String password,@Path("rollno") String Rollno,@Path("name") String name );




}


