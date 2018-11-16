package com.kartik.project.foodmonkey.API;

import com.kartik.project.foodmonkey.ApiEntity.AddToCardEntity;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.CardListingEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerHomeEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerSignUpEntity;
import com.kartik.project.foodmonkey.ApiEntity.LoginEntity;
import com.kartik.project.foodmonkey.ApiEntity.OrderCheckOutEntity;
import com.kartik.project.foodmonkey.ApiEntity.ResturantListEnity;
import com.kartik.project.foodmonkey.ApiEntity.SignUpEntity;
import com.kartik.project.foodmonkey.ApiResponse.AddPaymentsEntity;
import com.kartik.project.foodmonkey.ApiResponse.AddToCardResponse;
import com.kartik.project.foodmonkey.ApiResponse.CardListingResponse;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;
import com.kartik.project.foodmonkey.ApiResponse.CuisineListResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerAddressResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerHomeResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerSignUpResponse;
import com.kartik.project.foodmonkey.ApiResponse.ListCustAddResponse;
import com.kartik.project.foodmonkey.ApiResponse.LoginCustomerResponse;
import com.kartik.project.foodmonkey.ApiResponse.OrderCheckOutResponse;
import com.kartik.project.foodmonkey.ApiResponse.RestDetailMenuResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantFeedBackResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantListResponse;
import com.kartik.project.foodmonkey.ApiResponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kartikeya on 01/10/2018.
 */

public interface FoodMonkeyAppService {

    @POST("api/customer/add_token.php")
    Call<CommonResponse> addTokenAPI(@Body AddTokenEntity addTokenEntity);

    @POST("api/customer/customer_signup.php")
    Call<CustomerSignUpResponse> customerSignUp(@Body CustomerSignUpEntity customerSignUpEntity);

    @POST("api/resturant/home_restaurant.php")
    Call<CustomerHomeResponse> HomeResturantAPI(@Body CustomerHomeEntity customerHomeEntity);

    @POST("api/resturant/list_cuisines.php")
    Call<CuisineListResponse> ListCuisinesAPI(@Body AddTokenEntity addTokenEntity);

    @POST("api/resturant/resturant_list.php")
    Call<ResturantListResponse> resturantListAPI(@Body ResturantListEnity resturantListEntity);

    @POST("api/resturant/restaurant_details.php")
    Call<RestDetailMenuResponse> getResturanetDetailAndMenus(@Body ResturantListEnity resturantListEntity);

    @POST("api/customer/customer_login.php")
    Call<LoginCustomerResponse> getLoginApi(@Body LoginEntity loginEntity);

    @POST("api/resturant/rest_feedback_list.php")
    Call<ResturantFeedBackResponse> getResturentFeedBack(@Body AddTokenEntity addTokenEntity);

    @POST("api/customer/customer_new_address.php")
    Call<CustomerAddressResponse> AddCustomerNewAddress(@Body CustAddAddressEntity custAddAddressEntity);

    @POST("api/customer/customer_address.php")
    Call<ListCustAddResponse> ListCustomerAddress(@Body CustAddAddressEntity custAddAddressEntity);

    @POST("api/customer/order_checkout.php")
    Call<OrderCheckOutResponse> OrderCheckOut(@Body OrderCheckOutEntity custAddAddressEntity);

//    http://food-monkey.com/api/customer/add_card.php

//    @POST("api/customer/add_card.php")// not used
//    Call<AddToCardResponse> AddToCard(@Body AddToCardEntity addToCardEntity);

//    @POST("api/customer/card_list.php")// not used
//    Call<CardListingResponse> CardListing(@Body CardListingEntity cardListingEntity);// token and customer ID only needed

    @POST("api/customer/add_paymentmethod.php")
    Call<CardListingResponse> AddPaymentMethods(@Body AddPaymentsEntity addPaymentsEntity);// token and customer ID only needed


}
