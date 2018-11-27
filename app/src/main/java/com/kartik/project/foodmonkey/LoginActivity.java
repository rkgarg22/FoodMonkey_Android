package com.kartik.project.foodmonkey;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kartik.project.foodmonkey.API.FoodMonkeyAppService;
import com.kartik.project.foodmonkey.API.ServiceGenerator;
import com.kartik.project.foodmonkey.ApiEntity.CustomerSignUpEntity;
import com.kartik.project.foodmonkey.ApiEntity.LoginEntity;
import com.kartik.project.foodmonkey.ApiObject.CustomerSignUpObject;
import com.kartik.project.foodmonkey.ApiResponse.CustomerSignUpResponse;
import com.kartik.project.foodmonkey.ApiResponse.LoginCustomerResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import Infrastructure.AppCommon;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kartik.project.foodmonkey.API.ServiceGenerator.API_BASE_URL;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.signUpText)
    TextView signUpText;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.showPassword)
    ImageView showPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.login_button)
    LoginButton fbBtn;

    CallbackManager callbackManager;

    private GoogleSignInOptions gso;

    private GoogleApiClient mGoogleApiClient;

    private int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestScopes(new Scope(Scopes.PROFILE))
                .requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.login));
        setSignUpText();
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }

    boolean showPasswordFlag = false;

    @OnClick(R.id.showPassword)
    void setShowPassword() {
        if (showPasswordFlag) {
            showPasswordFlag = false;
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            showPasswordFlag = true;
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    @OnClick(R.id.loginButton)
    void setLoginButton() {
        if (validations()) {
            loginAccount(AppCommon.getInstance(this).getDeviceToken(),
                    email.getText().toString().trim(),
                    password.getText().toString().trim());
        }

//        startActivity(new Intent(this,HomeActivity.class));
    }

    private void setSignUpText() {
        SpannableString spannableString = new SpannableString(getString(R.string.dontHaveAccount));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
            }
        };
        spannableString.setSpan(clickableSpan, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpText.setText(spannableString);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.forgotPassword)
    void setForgotPassword() {
        startActivity(new Intent(this, ForgetPasswordActvity.class));
    }

    @OnClick(R.id.signUpText)
    void setSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, 200);
    }

    boolean validations() {
        boolean flag = true;
        String email = this.email.getText().toString();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, getString(R.string.pleaseEnterEmail), Toast.LENGTH_LONG).show();
            flag = false;
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.pleaseEnterPassword), Toast.LENGTH_LONG).show();
            flag = false;
        }
        return flag;
    }

    Call call;

    void loginAccount(String tokenKey, String email, String password) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(LoginActivity.this).isConnectingToInternet(LoginActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            LoginEntity loginEntity = new LoginEntity(tokenKey, email, password, "App","Direct");
            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.getLoginApi(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        LoginCustomerResponse loginCustomerResponse = (LoginCustomerResponse) response.body();
                        if (response.body() != null) {
                            if (loginCustomerResponse.getCode() != null) {
                                if (loginCustomerResponse.getCode().equals("200")) {
                                    AppCommon.getInstance(LoginActivity.this).setCustomerID(String.valueOf(
                                            loginCustomerResponse.getCustomerDetails().get(0).getCustomerId()));
                                    AppCommon.getInstance(LoginActivity.this).setFirstName(
                                            loginCustomerResponse.getCustomerDetails().get(0).getFirstName().trim());
                                    AppCommon.getInstance(LoginActivity.this).setSurName(
                                            loginCustomerResponse.getCustomerDetails().get(0).getSurName().trim());
                                    AppCommon.getInstance(LoginActivity.this).setMiddleName(
                                            loginCustomerResponse.getCustomerDetails().get(0).getMiddleIntial().trim());
                                    AppCommon.getInstance(LoginActivity.this).setGender(
                                            loginCustomerResponse.getCustomerDetails().get(0).getGender().trim());
                                    AppCommon.getInstance(LoginActivity.this).setEmailAddress(
                                            loginCustomerResponse.getCustomerDetails().get(0).getEmail().trim());
                                    AppCommon.getInstance(LoginActivity.this).setMobileNumber(
                                            loginCustomerResponse.getCustomerDetails().get(0).getMobile().trim());
                                    AppCommon.getInstance(LoginActivity.this).setDateOfBirth(
                                            loginCustomerResponse.getCustomerDetails().get(0).getDOB().trim());
                                    AppCommon.getInstance(LoginActivity.this).setProfilePic("http://food-monkey.com"
                                            + loginCustomerResponse.getCustomerDetails().get(0).getImageLink().trim());
                                    AppCommon.getInstance(LoginActivity.this).setStatus(
                                            loginCustomerResponse.getCustomerDetails().get(0).getStatus());
                                    AppCommon.getInstance(LoginActivity.this).setStripeCustID(
                                            loginCustomerResponse.getCustomerDetails().get(0).getStripeCustomerId());
                                    AppCommon.getInstance(LoginActivity.this).setIsUserLogIn(true);

                                    Intent intent = getIntent();
                                    intent.putExtra("data", true);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                } else {
                                    AppCommon.showDialog(LoginActivity.this, loginCustomerResponse.getMessage());
                                }
//                            User user = registrationResponse.getUserEntity();

//                            try {
//                                AppCommon.getInstance(HomeActivity.this).setUserLatitude(Double.parseDouble(latitude));
//                                AppCommon.getInstance(HomeActivity.this).setUserLongitude(Double.parseDouble(longitude));
//                            } catch (Exception e) {
//
//                            }
                            } else {
                                AppCommon.showDialog(LoginActivity.this, loginCustomerResponse.getMessage());
                            }
                        }
                    } else {
                        AppCommon.showDialog(LoginActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(LoginActivity.this, "No resturant found");
                    } else {
                        AppCommon.showDialog(LoginActivity.this, getResources().getString(R.string.network_error));

                    }
                }
            });
        } else {
            AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                Intent intent = getIntent();
                intent.putExtra("data", true);
                setResult(RESULT_OK, intent);
                finish();
            } else if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                //Calling a new function to handle signin
                handleSignInResult(result);
            } else {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }

        }
    }


    void callingSignUpApi(String tokenKey, final String firstName, final String middleName, final String surName,
                          final String gender, final String email, final String mobile, final String dateOfBirth,
                          String password, String profilePic, String channelCalling, String loginType, String socailID) {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        if (AppCommon.getInstance(LoginActivity.this).isConnectingToInternet(LoginActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            //  final String token = myFirebaseInstanceIDService.getDeviceToken();
            CustomerSignUpEntity customerSignUpEntity = new CustomerSignUpEntity(tokenKey, firstName, middleName, surName, gender,
                    email, mobile, dateOfBirth, password, profilePic, channelCalling, loginType, socailID);

            FoodMonkeyAppService foodMonkeyAppService = ServiceGenerator.createService(FoodMonkeyAppService.class);
            call = foodMonkeyAppService.customerSignUp(customerSignUpEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.GONE);
                        CustomerSignUpResponse customerSignUpResponse = (CustomerSignUpResponse) response.body();
                        if (customerSignUpResponse.getCode().equals("200")) {
                            CustomerSignUpObject customerSignUpObject = customerSignUpResponse.getCustomerDetail().get(0);

                            AppCommon.getInstance(LoginActivity.this).setCustomerID(customerSignUpObject.getCustomerId());
                            AppCommon.getInstance(LoginActivity.this).setFirstName(customerSignUpObject.getFirstName());
                            AppCommon.getInstance(LoginActivity.this).setSurName(customerSignUpObject.getSurName());
                            AppCommon.getInstance(LoginActivity.this).setMiddleName(customerSignUpObject.getMiddleIntial());
                            AppCommon.getInstance(LoginActivity.this).setGender(customerSignUpObject.getGender());
                            AppCommon.getInstance(LoginActivity.this).setEmailAddress(customerSignUpObject.getEmail());
                            AppCommon.getInstance(LoginActivity.this).setMobileNumber(customerSignUpObject.getMobile());
                            AppCommon.getInstance(LoginActivity.this).setDateOfBirth(customerSignUpObject.getdOB());
                            AppCommon.getInstance(LoginActivity.this).setProfilePic("http://food-monkey.com" + customerSignUpResponse.getCustomerDetail().get(0).getImageLink());
                            AppCommon.getInstance(LoginActivity.this).setStripeCustID(customerSignUpObject.getStripeCustomerId());
                            AppCommon.getInstance(LoginActivity.this).setReferCode(customerSignUpObject.getReferCode());
//                            AppCommon.getInstance(LoginActivity.this).setStatus(
//                                    loginCustomerResponse.getCustomerDetails().get(0).getStatus());
                            AppCommon.getInstance(LoginActivity.this).setIsUserLogIn(true);
                            Intent intent = getIntent();
                            intent.putExtra("data", true);
                            setResult(RESULT_OK, intent);
                            finish();

                            Toast.makeText(LoginActivity.this, customerSignUpResponse.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            AppCommon.showDialog(LoginActivity.this, customerSignUpResponse.getMessage());
                        }
                    } else {
                        AppCommon.showDialog(LoginActivity.this, getString(R.string.serverError));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    progressBar.setVisibility(View.GONE);
                    if (t instanceof JsonSyntaxException) {
                        AppCommon.showDialog(LoginActivity.this, "Something Went wrong ..Please try again.");
                    } else {
                        AppCommon.showDialog(LoginActivity.this, getResources().getString(R.string.network_error));
                    }
                }
            });
        } else {
            AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
            AppCommon.showDialog(this, getResources().getString(R.string.network_error));
        }
    }

    @OnClick(R.id.facebookBtn)
    void setOnFb() {
        authorizeFaceBook();
    }

    @OnClick(R.id.googleBtn)
    void setOnGoogleBtn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    /*--------------------------------Facebook SetUp -------------------------*/
    void authorizeFaceBook() {
//        fbBtn.performClick();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        fbBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    requestingFacebookData(loginResult.getAccessToken());
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void requestingFacebookData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                JSONObject json = response.getJSONObject();
                System.out.println("Json data :" + json);
                if (json != null) {
                    try {
                        if (json.getString("email").equals("") || json.getString("email").equals(null)) {
                            AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Email not Found! Please Use Normal Signup");
                        } else {
                            String facebookId = json.getString("id");
                            String fbEmail = json.getString("email");
                            String firstName = json.getString("name");
//                            String birthday = json.getString("birthday");
                            String uri = "https://graph.facebook.com/" + facebookId + "/picture?type=normal";
                            URL profilePictureUrl = new URL("https://graph.facebook.com/" + facebookId + "/picture?type=normal");

                            //  String gender = json.getString("gender");
                            //  int password = AppCommon.getInstance(Login_and_Register_Activity.this).rendomPassword();
//                            register(firstName, "", fbEmail, "", "", facebookId, "facebook", uri, "");
                            callingSignUpApi(AppCommon.getInstance(LoginActivity.this).getDeviceToken(), firstName, "", "S"
                                    , "", fbEmail, "", "", "", uri, "App",
                                    "Facebook", facebookId);
                        }
                    } catch (JSONException e) {
                        AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Email not Found! Please Use Normal Signup");
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    /*---------------------------------Google Sign Up Stuff-----------------------------*/

    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        Uri imageUrl = null;
        String imageStr;
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            String name = acct.getDisplayName();
            String email = acct.getEmail();
            String gen = "";
            try {
                imageUrl = acct.getPhotoUrl();
                Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
                int gender = person.getGender();
                switch (gender) {
                    case 1:
                        gen = "Female";
                        break;
                    case 0:
                        gen = "Male";
                        break;
                    case 2:
                        gen = "Either";
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String googleId = acct.getId();
            if (imageUrl == null)
                imageStr = "";
            else
                imageStr = imageUrl.toString();
//            register(name, "", email, "", "", googleId, "gmail", imageStr, gen);
            callingSignUpApi(AppCommon.getInstance(LoginActivity.this).getDeviceToken(), name, "", "S"
                    , gen, email, "", "", "", imageStr, "App",
                    "Google", googleId);

            Log.i("loginInfo", name + " " + email + " " + imageStr);


        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }
}
