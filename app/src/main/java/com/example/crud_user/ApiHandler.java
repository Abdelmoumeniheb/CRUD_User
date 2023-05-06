package com.example.crud_user;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiHandler {
    @GET("webservices/list.php")
    Call<List<User>> getAllUsers();
    @FormUrlEncoded
    @POST("webservices/insert.php")
    Call <User> insertUser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String emails
    );
    @FormUrlEncoded
    @POST("webservices/update.php")
    Call <User> UpdatetUser(
            @Field("id") int id,
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String emails
    );
    @FormUrlEncoded
    @POST("webservices/delete.php")
    Call <User> deleteUser(
            @Field("id") int id
    );
}
