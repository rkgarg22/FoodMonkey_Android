package com.kartik.project.foodmonkey.API;

import com.kartik.project.foodmonkey.ApiEntity.AddFeedBackEnitity;
import com.kartik.project.foodmonkey.ApiEntity.AddToCardEntity;
import com.kartik.project.foodmonkey.ApiEntity.AddTokenEntity;
import com.kartik.project.foodmonkey.ApiEntity.BrainTreeEntity;
import com.kartik.project.foodmonkey.ApiEntity.CardListingEntity;
import com.kartik.project.foodmonkey.ApiEntity.CommonEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustAddAddressEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustProfileEditEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerEditAddressEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerHomeEntity;
import com.kartik.project.foodmonkey.ApiEntity.CustomerSignUpEntity;
import com.kartik.project.foodmonkey.ApiEntity.LoginEntity;
import com.kartik.project.foodmonkey.ApiEntity.OrderCheckOutEntity;
import com.kartik.project.foodmonkey.ApiEntity.ResturantListEnity;
import com.kartik.project.foodmonkey.ApiResponse.AddPaymentsEntity;
import com.kartik.project.foodmonkey.ApiResponse.AddToCardResponse;
import com.kartik.project.foodmonkey.ApiResponse.BrainTreePaymentResponse;
import com.kartik.project.foodmonkey.ApiResponse.ChangePassForgotResp;
import com.kartik.project.foodmonkey.ApiResponse.CommonResponse;
import com.kartik.project.foodmonkey.ApiResponse.CuisineListResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerAddressResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerDetailResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerHomeResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerOrderListResponse;
import com.kartik.project.foodmonkey.ApiResponse.CustomerSignUpResponse;
import com.kartik.project.foodmonkey.ApiResponse.GetBrainTreeResponse;
import com.kartik.project.foodmonkey.ApiResponse.ListCustAddResponse;
import com.kartik.project.foodmonkey.ApiResponse.LoginCustomerResponse;
import com.kartik.project.foodmonkey.ApiResponse.OrderCheckOutResponse;
import com.kartik.project.foodmonkey.ApiResponse.OrderStatusResponse;
import com.kartik.project.foodmonkey.ApiResponse.PaymentMethodResponse;
import com.kartik.project.foodmonkey.ApiResponse.RestDetailMenuResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantFeedBackResponse;
import com.kartik.project.foodmonkey.ApiResponse.ResturantListResponse;
import com.kartik.project.foodmonkey.ApiResponse.SendOTPEmailResponse;
import com.kartik.project.foodmonkey.ApiResponse.SendingOTP_Response;
import com.kartik.project.foodmonkey.ApiResponse.StripeAPIResponse;
import com.kartik.project.foodmonkey.ApiResponse.VaildNumberResponse;
import com.stripe.net.StripeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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

//    @POST("api/customer/card_list.php")// not used
//    Call<CardListingResponse> CardListing(@Body CardListingEntity cardListingEntity);// token and customer ID only needed
//
//    @POST("api/customer/add_paymentmethod.php")
//    Call<CardListingResponse> AddPaymentMethods(@Body AddPaymentsEntity addPaymentsEntity);// token and customer ID only needed

    @POST("api/customer/add_paymentmethod.php")
    Call<PaymentMethodResponse> AddPaymentMethods(@Body AddPaymentsEntity addPaymentsEntity);// token and customer ID only needed

    @POST("api/customer/customer_profile_edit.php")
    Call<CommonResponse> ProfileEdit(@Body CustProfileEditEntity custProfileEditEntity);// token and customer ID only needed

    @POST("api/braintree/braintree_token.php")
    Call<GetBrainTreeResponse> GetBrainTreeToken(@Body AddTokenEntity addTokenEntity);// token  only needed

    @POST("api/braintree/braintree_payment.php")
    Call<BrainTreePaymentResponse> GetBrainTreePayment(@Body BrainTreeEntity brainTreeEntity);// token  only needed

    @POST("api/customer/customer_details.php")
    Call<CustomerDetailResponse> GetCustomerDetail(@Body CommonEntity commonEntity);

    @POST("api/customer/customer_feedback.php")
    Call<CommonResponse> AddCustomerFeedBack(@Body AddFeedBackEnitity addFeedBackEnitity); // Not Implemented Yet...

    @POST("api/customer/add_card.php")
    Call<AddToCardResponse> AddToCard(@Body AddToCardEntity addToCardEntity);

    @POST("api/customer/card_list.php")
    Call<AddToCardResponse> CardListing(@Body CardListingEntity cardListingEntity);// token and customer ID only needed

    @POST("api/customer/pay_with_card.php")
    Call<StripeAPIResponse> payWithStripAPI(@Body HashMap<String,String>hashMap);

    @POST("api/customer/customer_order_status.php")
// not used
    Call<OrderStatusResponse> CustomerOrderStatus(@Body HashMap<String, String> hashMap);// token and customer ID only needed

    @POST("api/customer/customer_edit_address.php")
    Call<CommonResponse> CustomerEditAddress(@Body CustomerEditAddressEntity customerEditAddressEntity);// token and customer ID only needed

    @POST("api/customer/send_mobile_otp.php")
    Call<SendingOTP_Response> SendingOTPRequest(@Body HashMap<String,String> hashMap);// token and customer ID only needed

    @POST("api/customer/verify_mobile_otp.php")
    Call<CommonResponse> RecieveOTPRequest(@Body HashMap<String,String> hashMap);// token and customer ID only needed

    @POST("api/customer/forget_change_password.php")
    Call<ChangePassForgotResp> ForgetChangePassword(@Body HashMap<String,String> hashMap);

    @POST("api/customer/forget_password.php")
    Call<VaildNumberResponse> VerifyValidEmailNumber(@Body HashMap<String,String> hashMap);

    @POST("api/customer/send_email_otp.php")
    Call<SendOTPEmailResponse> SendEmailOTP_API(@Body HashMap<String,String> hashMap);

    @POST("api/resturant/Customer_Order_list.php")
    Call<CustomerOrderListResponse> CustomerOrderList(@Body HashMap<String,String> hashMap);

}
