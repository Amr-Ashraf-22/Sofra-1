package com.elatienda.kaytamarka.sofra.data.api;

import com.elatienda.kaytamarka.sofra.data.model.display_restaurants.DisplayRestaurants;
import com.elatienda.kaytamarka.sofra.data.model.general_response.GeneralResponse;
import com.elatienda.kaytamarka.sofra.data.model.general_response_not_paginated.GeneralResponseNotPaginated;
import com.elatienda.kaytamarka.sofra.data.model.login.Login;
import com.elatienda.kaytamarka.sofra.data.model.new_password.NewPassword;
import com.elatienda.kaytamarka.sofra.data.model.register.ClientRegister;
import com.elatienda.kaytamarka.sofra.data.model.register.RestaurantRegister;
import com.elatienda.kaytamarka.sofra.data.model.reset_password.ResetPassword;
import java.io.File;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // General Response

    @GET("cities")
    Call<GeneralResponse> getCitiesNotPaginated();

    @GET("regions")
    Call<GeneralResponse> getRegionsNotPaginated(@Query("city_id") int cityId);

    // Client User Cycle

    @POST("client/login")
    @FormUrlEncoded
    Call<Login> onClientLogin(@Field("email") String clientEmail,
                           @Field("password") String clientPassword);

    @POST("client/sign-up")
    @FormUrlEncoded
    Call<ClientRegister> onClientRegister(@Field("name") String clientName,
                                          @Field("email") String clientEmail,
                                          @Field("password") String clientPassword,
                                          @Field("password_confirmation") String clientPasswordConfirmation,
                                          @Field("phone") String clientPhone,
                                          @Field("region_id") int clientRegionId,
                                          @Field("profile_image") File clientProfileImage); // Uri , Url , String

    @POST("client/reset-password")
    @FormUrlEncoded
    Call<ResetPassword> onClientResetPassword(@Field("email") String clientEmail);

    @POST("client/new-password")
    @FormUrlEncoded
    Call<NewPassword> onClientNewPassword(@Field("code") int clientCode,
                                          @Field("password") String clientPassword,
                                          @Field("password_confirmation") String clientPasswordConfirmation);

    // Restaurant User Cycle

    @POST("restaurant/login")
    @FormUrlEncoded
    Call<Login> onRestaurantLogin(@Field("email") String restaurantEmail,
                              @Field("password") String restaurantPassword);

    @POST("client/sign-up")
    @FormUrlEncoded
    Call<RestaurantRegister> onRestaurantRegister(@Field("name") String restaurantName,
                                                  @Field("email") String restaurantEmail,
                                                  @Field("password") String restaurantPassword,
                                                  @Field("password_confirmation") String restaurantPasswordConfirmation,
                                                  @Field("phone") String restaurantPhone,
                                                  @Field("whatsapp") String restaurantWhatsapp,
                                                  @Field("region_id") int restaurantRegionId,
                                                  @Field("delivery_cost") int restaurantDeliveryCost, // double , float
                                                  @Field("minimum_charger") int restaurantMinimumCharger, // double , float
                                                  @Field("photo") File restaurantPhoto, // Uri , Url , String
                                                  @Field("delivery_time") int restaurantDeliveryTime); // double , float

    @POST("restaurant/reset-password")
    @FormUrlEncoded
    Call<ResetPassword> onRestaurantResetPassword(@Field("email") String restaurantEmail);

    @POST("restaurant/new-password")
    @FormUrlEncoded
    Call<NewPassword> onRestaurantNewPassword(@Field("code") int restaurantCode,
                                          @Field("password") String restaurantPassword,
                                          @Field("password_confirmation") String restaurantPasswordConfirmation);

    // Home Client Cycle

    @GET("restaurants")
    Call<DisplayRestaurants> getRestaurants(@Query("page") int page);

    @GET("restaurants")
    Call<DisplayRestaurants> getRestaurantsFilter(@Query("keyword") String keyword,
                                            @Query("region_id") int regionId);

}