package com.app.foodMonkey.API;

import com.app.foodMonkey.ApiEntity.AddFeedBackEnitity;
import com.app.foodMonkey.ApiEntity.AddToCardEntity;
import com.app.foodMonkey.ApiEntity.AddTokenEntity;
import com.app.foodMonkey.ApiEntity.BrainTreeEntity;
import com.app.foodMonkey.ApiEntity.CardListingEntity;
import com.app.foodMonkey.ApiEntity.CommonEntity;
import com.app.foodMonkey.ApiEntity.CustAddAddressEntity;
import com.app.foodMonkey.ApiEntity.CustProfileEditEntity;
import com.app.foodMonkey.ApiEntity.CustomerEditAddressEntity;
import com.app.foodMonkey.ApiEntity.CustomerHomeEntity;
import com.app.foodMonkey.ApiEntity.CustomerSignUpEntity;
import com.app.foodMonkey.ApiEntity.LoginEntity;
import com.app.foodMonkey.ApiEntity.OrderCheckOutEntity;
import com.app.foodMonkey.ApiEntity.ResturantListEnity;
import com.app.foodMonkey.ApiResponse.AddPaymentsEntity;
import com.app.foodMonkey.ApiResponse.AddToCardResponse;
import com.app.foodMonkey.ApiResponse.BrainTreePaymentResponse;
import com.app.foodMonkey.ApiResponse.ChangePassForgotResp;
import com.app.foodMonkey.ApiResponse.CommonResponse;
import com.app.foodMonkey.ApiResponse.CouponResponse;
import com.app.foodMonkey.ApiResponse.CuisineListResponse;
import com.app.foodMonkey.ApiResponse.CustomerAddressResponse;
import com.app.foodMonkey.ApiResponse.CustomerDetailResponse;
import com.app.foodMonkey.ApiResponse.CustomerHomeResponse;
import com.app.foodMonkey.ApiResponse.CustomerOrderListResponse;
import com.app.foodMonkey.ApiResponse.CustomerSignUpResponse;
import com.app.foodMonkey.ApiResponse.GetBrainTreeResponse;
import com.app.foodMonkey.ApiResponse.HelpResponse;
import com.app.foodMonkey.ApiResponse.ListCustAddResponse;
import com.app.foodMonkey.ApiResponse.LoginCustomerResponse;
import com.app.foodMonkey.ApiResponse.OrderCheckOutResponse;
import com.app.foodMonkey.ApiResponse.OrderStatusResponse;
import com.app.foodMonkey.ApiResponse.PaymentMethodResponse;
import com.app.foodMonkey.ApiResponse.ReferByMobileResponse;
import com.app.foodMonkey.ApiResponse.RestDetailMenuResponse;
import com.app.foodMonkey.ApiResponse.ResturantFeedBackResponse;
import com.app.foodMonkey.ApiResponse.ResturantListResponse;
import com.app.foodMonkey.ApiResponse.SendOTPEmailResponse;
import com.app.foodMonkey.ApiResponse.SendingOTP_Response;
import com.app.foodMonkey.ApiResponse.StripeResponsePack.StripeAPIResponse;
import com.app.foodMonkey.ApiResponse.VaildNumberResponse;

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

    @POST("api/customer/refer_by_mobile.php")
    Call<ReferByMobileResponse> ReferByMobile(@Body HashMap<String,String> hashMap);

    @POST("api/customer/direct_forget_password.php")
    Call<ReferByMobileResponse> ChangePasswordAPI(@Body HashMap<String,String> hashMap); // response is same as referByMobile

    @POST("api/resturant/restaurant_faq.php")
    Call<HelpResponse> getHelp(@Body HashMap<String,String> hashMap); // response is same as referByMobile

    @POST("api/customer/check_coupon.php")
    Call<CouponResponse> CouponAPI(@Body HashMap<String,String> hashMap); // response is same as referByMobile

}
