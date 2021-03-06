package com.gut.follower.utility;



import com.gut.follower.model.Account;
import com.gut.follower.model.GutLocation;
import com.gut.follower.model.Track;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface JConductorService {

    @POST("/register")
    Call<Account> register(@Body Account account);

    @POST("/login")
    Call<Account> login(@Body Account account);

    @GET("/rest/track/{id}")
    Call<Track> getTrack(@Path("id") String id);

    @DELETE("/rest/track/{id}")
    Call<ResponseBody> deleteTrack(@Path("id") String id);

    @GET("/rest/track")
    Call<List<Track>> getTracks();

    @POST("/rest/track")
    Call<Track> postTrack(@Body GutLocation location,
                          @Query("activity") String activity);

    @POST("/rest/track/{id}")
    Call<Track> postLocation( @Path("id") String id,
                              @Body GutLocation location,
                              @Query("finished") boolean finished);

    @POST("/rest/account")
    Call<Account> postAccountInfo(@Body Account account);

    @GET("/rest/account")
    Call<Account> getAccountInfo();

    @DELETE("/rest/account")
    Call<ResponseBody> deleteAccount();

}
