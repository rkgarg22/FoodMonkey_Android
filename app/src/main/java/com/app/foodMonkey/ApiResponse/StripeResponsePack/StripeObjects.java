package com.app.foodMonkey.ApiResponse.StripeResponsePack;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartikeya on 04/01/2019.
 */

public class StripeObjects {
    @SerializedName("id")
    private String id;
    @SerializedName("object")
    private String object;
    @SerializedName("amount")
    private Integer amount;
    @SerializedName("amount_refunded")
    private Integer amountRefunded;
    @SerializedName("application")
    private Object application;
    @SerializedName("application_fee")
    private Object applicationFee;
    @SerializedName("balance_transaction")
    private String balanceTransaction;
    @SerializedName("captured")
    private Boolean captured;
    @SerializedName("created")
    private Integer created;
    @SerializedName("currency")
    private String currency;
    @SerializedName("customer")
    private String customer;
    @SerializedName("description")
    private String description;
    @SerializedName("destination")
    private Object destination;
    @SerializedName("dispute")
    private Object dispute;
    @SerializedName("failure_code")
    private Object failureCode;
    @SerializedName("failure_message")
    private Object failureMessage;
    @SerializedName("fraud_details")
    private List<Object> fraudDetails = null;
    @SerializedName("invoice")
    private Object invoice;
    @SerializedName("livemode")
    private Boolean livemode;
    @SerializedName("metadata")
    private List<Object> metadata = null;
    @SerializedName("on_behalf_of")
    private Object onBehalfOf;
    @SerializedName("order")
    private Object order;
    @SerializedName("outcome")
    private OutcomeObject outcome;
    @SerializedName("paid")
    private Boolean paid;
    @SerializedName("payment_intent")
    private Object paymentIntent;
    @SerializedName("receipt_email")
    private Object receiptEmail;
    @SerializedName("receipt_number")
    private Object receiptNumber;
    @SerializedName("refunded")
    private Boolean refunded;
    @SerializedName("refunds")
    private RefundsObjects refunds;
    @SerializedName("review")
    private Object review;
    @SerializedName("shipping")
    private Object shipping;
    @SerializedName("source")
    private SourceObjects source;
    @SerializedName("source_transfer")
    private Object sourceTransfer;
    @SerializedName("statement_descriptor")
    private Object statementDescriptor;
    @SerializedName("status")
    private String status;
    @SerializedName("transfer_group")
    private Object transferGroup;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(Integer amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public Object getApplication() {
        return application;
    }

    public void setApplication(Object application) {
        this.application = application;
    }

    public Object getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(Object applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(String balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public Boolean getCaptured() {
        return captured;
    }

    public void setCaptured(Boolean captured) {
        this.captured = captured;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDestination() {
        return destination;
    }

    public void setDestination(Object destination) {
        this.destination = destination;
    }

    public Object getDispute() {
        return dispute;
    }

    public void setDispute(Object dispute) {
        this.dispute = dispute;
    }

    public Object getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(Object failureCode) {
        this.failureCode = failureCode;
    }

    public Object getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(Object failureMessage) {
        this.failureMessage = failureMessage;
    }

    public List<Object> getFraudDetails() {
        return fraudDetails;
    }

    public void setFraudDetails(List<Object> fraudDetails) {
        this.fraudDetails = fraudDetails;
    }

    public Object getInvoice() {
        return invoice;
    }

    public void setInvoice(Object invoice) {
        this.invoice = invoice;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public List<Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Object> metadata) {
        this.metadata = metadata;
    }

    public Object getOnBehalfOf() {
        return onBehalfOf;
    }

    public void setOnBehalfOf(Object onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    public OutcomeObject getOutcome() {
        return outcome;
    }

    public void setOutcome(OutcomeObject outcome) {
        this.outcome = outcome;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Object getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(Object paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public Object getReceiptEmail() {
        return receiptEmail;
    }

    public void setReceiptEmail(Object receiptEmail) {
        this.receiptEmail = receiptEmail;
    }

    public Object getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Object receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public RefundsObjects getRefunds() {
        return refunds;
    }

    public void setRefunds(RefundsObjects refunds) {
        this.refunds = refunds;
    }

    public Object getReview() {
        return review;
    }

    public void setReview(Object review) {
        this.review = review;
    }

    public Object getShipping() {
        return shipping;
    }

    public void setShipping(Object shipping) {
        this.shipping = shipping;
    }

    public SourceObjects getSource() {
        return source;
    }

    public void setSource(SourceObjects source) {
        this.source = source;
    }

    public Object getSourceTransfer() {
        return sourceTransfer;
    }

    public void setSourceTransfer(Object sourceTransfer) {
        this.sourceTransfer = sourceTransfer;
    }

    public Object getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(Object statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getTransferGroup() {
        return transferGroup;
    }

    public void setTransferGroup(Object transferGroup) {
        this.transferGroup = transferGroup;
    }
}
