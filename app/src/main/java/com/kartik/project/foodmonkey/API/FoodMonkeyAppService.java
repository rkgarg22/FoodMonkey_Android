package com.kartik.project.foodmonkey.API;

import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerSignUpEntity;
import com.kartik.project.foodmonkey.ApiEntity.ResturantListEnity;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerSignUpResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kartikeya on 01/10/2018.
 */

public interface FoodMonkeyAppService {

    @POST("customer/add_token.php")
    Call<CommonResponse> addTokenAPI(@Body AddTokenEntity addTokenEntity);

    @POST("customer/customer_signup.php")
    Call<CustomerSignUpResponse> customerSignUp(@Body CustomerSignUpEntity customerSignUpEntity);

//    @POST("customer/customer_login.php")
//    Call<CommonResponse> customerLogin(@Body CustomerLoginEntity customerLoginEntity)

//    @POST("customer/customer_profile_edit.php")
//    Call<CommonResponse> customerProfileEdit(@Body CustomerSignUpEntity customerSignUpEntity)

    @POST("resturant/resturant_list.php")
    Call<ResturantListResponse> resturantList(@Body ResturantListEnity resturantListEntity);

//    @POST("resturant/resturant_list.php")
//    Call<CommonIntResponse> resturantList(@Body ResturantListEnity resturantListEntity);

//    @POST("resturant/list_menu_subcategory.php")
//    Call<CommonIntResponse> resturantList(@Body ResturantListEnity resturantListEntity);


}
