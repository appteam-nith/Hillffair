package com.appteamnith.hillffair.utilities;

import com.appteamnith.hillffair.activities.EventActivity;
import com.appteamnith.hillffair.activities.UploadNewsFeedActivity;

import com.appteamnith.hillffair.models.QuizQuestionsModel;
import com.appteamnith.hillffair.models.UpdateScoreModel;
import com.appteamnith.hillffair.models.UserScoreResponse;
import com.appteamnith.hillffair.models.ClubModel2;
import com.appteamnith.hillffair.models.Login;
import com.appteamnith.hillffair.models.ProfileDataModel;
import com.appteamnith.hillffair.models.Register;
import com.appteamnith.hillffair.models.NewsfeedModel;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jatin on 9/11/2016.
 */

public interface APIINTERFACE {

    @FormUrlEncoded
    @POST("login")
    Call<Login> login(@Field("email") String email, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("register")
    Call<Register> register (@Field("name")String name, @Field("email")String email, @Field("pwd")String pwd, @Field("nitian")boolean nitian, @Field("rollno")String rollno, @Field("phone")String phone);


    @FormUrlEncoded
    @POST("newsfeed/post")
    Call<UploadNewsFeedActivity.UploadResponse> uploadNews(@Field("title") String title, @Field("description") String description, @Field("uId") String userId, @Field("uName") String userName);


    @GET("newsfeed/all")
    Call<NewsfeedModel> getAllNews(@Query("from")String from);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileDataModel> profileBasicInfo(@Field("id") String id);


    @GET("club")
    Call<EventActivity.ClubResponse> getAllClub();

    @GET("club/{club_name}")
    Call<ClubModel2> getClubInfo(@Path("club_name") String club_name);

    @GET("newsfeed/user")
    Call<NewsfeedModel> getAllUserNews(@Query("from") String from, @Query("id") String id);

    @FormUrlEncoded
    @POST("profile/score")
    Call<UserScoreResponse> getUserScore(@Field("id") String id);

    @FormUrlEncoded
    @POST("quiz/questions")
    Call<QuizQuestionsModel> getQuiz(@Field("id") String id);

    @FormUrlEncoded
    @POST("quiz/score")
    Call<UpdateScoreModel> updateScore(@Field("id") String id, @Field("score") int score);

}


