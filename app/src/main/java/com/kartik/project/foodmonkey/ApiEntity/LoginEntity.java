package com.kartik.project.foodmonkey.ApiEntity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kartikeya on 16/10/2018.
 */

public class LoginEntity {
    @SerializedName("TokenKey")
    String tokenKey;

    @SerializedName("Email")
    String email;

    @SerializedName("Password")
    String password;

    @SerializedName("CallingChannel")
    String callingChannel;

    @SerializedName("LoginType")
    String loginType;

    public LoginEntity(String tokenKey, String email, String password, String callingChannel, String loginType) {
        this.tokenKey = tokenKey;
        this.email = email;
        this.password = password;
        this.callingChannel = callingChannel;
        this.loginType = loginType;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCallingChannel() {
        return callingChannel;
    }

    public void setCallingChannel(String callingChannel) {
        this.callingChannel = callingChannel;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
