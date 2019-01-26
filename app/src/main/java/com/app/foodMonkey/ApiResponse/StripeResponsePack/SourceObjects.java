package com.app.foodMonkey.ApiResponse.StripeResponsePack;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartikeya on 04/01/2019.
 */

public class SourceObjects {
    @SerializedName("id")
    private String id;
    @SerializedName("object")
    private String object;
    @SerializedName("address_city")
    private Object addressCity;
    @SerializedName("address_country")
    private Object addressCountry;
    @SerializedName("address_line1")
    private Object addressLine1;
    @SerializedName("address_line1_check")
    private Object addressLine1Check;
    @SerializedName("address_line2")
    private Object addressLine2;
    @SerializedName("address_state")
    private Object addressState;
    @SerializedName("address_zip")
    private Object addressZip;
    @SerializedName("address_zip_check")
    private Object addressZipCheck;
    @SerializedName("brand")
    private String brand;
    @SerializedName("country")
    private String country;
    @SerializedName("customer")
    private String customer;
    @SerializedName("cvc_check")
    private Object cvcCheck;
    @SerializedName("dynamic_last4")
    private Object dynamicLast4;
    @SerializedName("exp_month")
    private Integer expMonth;
    @SerializedName("exp_year")
    private Integer expYear;
    @SerializedName("fingerprint")
    private String fingerprint;
    @SerializedName("funding")
    private String funding;
    @SerializedName("last4")
    private String last4;
    @SerializedName("metadata")
    private List<Object> metadata = null;
    @SerializedName("name")
    private Object name;
    @SerializedName("tokenization_method")
    private Object tokenizationMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Object getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(Object addressCity) {
        this.addressCity = addressCity;
    }

    public Object getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(Object addressCountry) {
        this.addressCountry = addressCountry;
    }

    public Object getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(Object addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Object getAddressLine1Check() {
        return addressLine1Check;
    }

    public void setAddressLine1Check(Object addressLine1Check) {
        this.addressLine1Check = addressLine1Check;
    }

    public Object getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(Object addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Object getAddressState() {
        return addressState;
    }

    public void setAddressState(Object addressState) {
        this.addressState = addressState;
    }

    public Object getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(Object addressZip) {
        this.addressZip = addressZip;
    }

    public Object getAddressZipCheck() {
        return addressZipCheck;
    }

    public void setAddressZipCheck(Object addressZipCheck) {
        this.addressZipCheck = addressZipCheck;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Object getCvcCheck() {
        return cvcCheck;
    }

    public void setCvcCheck(Object cvcCheck) {
        this.cvcCheck = cvcCheck;
    }

    public Object getDynamicLast4() {
        return dynamicLast4;
    }

    public void setDynamicLast4(Object dynamicLast4) {
        this.dynamicLast4 = dynamicLast4;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public List<Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Object> metadata) {
        this.metadata = metadata;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getTokenizationMethod() {
        return tokenizationMethod;
    }

    public void setTokenizationMethod(Object tokenizationMethod) {
        this.tokenizationMethod = tokenizationMethod;
    }
}
