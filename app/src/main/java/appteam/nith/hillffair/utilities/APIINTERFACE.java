package appteam.nith.hillffair.utilities;

import appteam.nith.hillffair.activities.ClubActivity;
import appteam.nith.hillffair.activities.EventActivity;
import appteam.nith.hillffair.activities.UploadNewsFeedActivity;
import appteam.nith.hillffair.models.BattleDayModel;
import appteam.nith.hillffair.models.ClubModel2;
import appteam.nith.hillffair.models.Dislike;
import appteam.nith.hillffair.models.ForgotPassword;
import appteam.nith.hillffair.models.LeaderBoardModel;
import appteam.nith.hillffair.models.Likecount;
import appteam.nith.hillffair.models.Login;
import appteam.nith.hillffair.models.NewsfeedModel;
import appteam.nith.hillffair.models.ProfileDataModel;
import appteam.nith.hillffair.models.QuizQuestionsModel;
import appteam.nith.hillffair.models.Register;
import appteam.nith.hillffair.models.SendPassword;
import appteam.nith.hillffair.models.UpdateScoreModel;
import appteam.nith.hillffair.models.UserScoreResponse;

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
    Call<Register> register(@Field("name") String name, @Field("email") String email, @Field("pwd") String pwd, @Field("nitian") boolean nitian, @Field("rollno") String rollno, @Field("phone") String phone);


    @FormUrlEncoded
    @POST("newsfeed/post")
    Call<UploadNewsFeedActivity.UploadResponse> uploadNews(@Field("title") String title, @Field("description") String description, @Field("uId") String userId, @Field("uName") String userName);


    @GET("newsfeed/all")
    Call<NewsfeedModel> getAllNews(@Query("from") String from,@Query("uId") String userId);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileDataModel> profileBasicInfo(@Field("id") String id);


    @GET("club")
    Call<EventActivity.ClubResponse> getAllClub();

    @GET("club/{club_name}")
    Call<ClubModel2> getClubInfo(@Path("club_name") String club_name);

    @GET("newsfeed/user")
    Call<NewsfeedModel> getAllUserNews(@Query("from") String from, @Query("uId") String id);

    @FormUrlEncoded
    @POST("profile/score")
    Call<UserScoreResponse> getUserScore(@Field("id") String id);

    @FormUrlEncoded
    @POST("quiz/questions_new123")
    Call<QuizQuestionsModel> getQuiz(@Field("id") String id);

    @FormUrlEncoded
    @POST("quiz/score")
    Call<UpdateScoreModel> updateScore(@Field("id") String id, @Field("score") int score);

    @GET("quiz/leaderboard")
    Call<LeaderBoardModel> getLeaderBoard(@Query("from") String from);

    @FormUrlEncoded
    @POST("password/forgot")
    Call<ForgotPassword> forgotPassword(@Field("email") String email);



    @FormUrlEncoded
    @POST("password/new")
    Call<SendPassword> sendPassword(@Field("id") String id, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("newsfeed/like")
    Call<Likecount>likecount(@Field("id") String id,@Field("uId") String userId);


    @FormUrlEncoded
    @POST("newsfeed/like")
    Call<Dislike>dislike(@Field("id") String id,@Field("uId") String userId);

    @GET("events/special")
    Call<BattleDayModel> getSpecialEvents();


    @GET("events/special/event")
    Call<ClubActivity.BattleResponseEvent> getEventData(@Query("id") String id);

}


