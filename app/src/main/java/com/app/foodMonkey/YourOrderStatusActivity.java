package com.app.foodMonkey;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourOrderStatusActivity extends AppCompatActivity {

    @BindView(R.id.left)
    ImageView left;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    /*---- for reject, Accepted...  */

    @BindView(R.id.orderRejectAccept)
    LinearLayout orderRejectAccept;

    @BindView(R.id.orderStatusText1)
    TextView orderStatusText1;

    @BindView(R.id.orderStatusText2)
    TextView orderStatusText2;

    @BindView(R.id.requestLayout)
    RelativeLayout requestLayout;

    @BindView(R.id.thanksLayout)
    LinearLayout thanksLayout;

    /*=== */
    @BindView(R.id.orderOpen)
    LinearLayout orderOpen;

    @BindView(R.id.callFeedBackBtn)
    TextView callFeedBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order_status);
        ButterKnife.bind(this);

        left.setVisibility(View.VISIBLE);
        toolbarText.setText(getString(R.string.yourOrder));
        if (getIntent()!=null)
        {
            String orderStatus=getIntent().getStringExtra("orderStatus");
            final String restID=getIntent().getStringExtra("restID");
            final String phoneNumber=getIntent().getStringExtra("phoneNumber");

            if (orderStatus.equals("Reject")){
                orderOpen.setVisibility(View.GONE);
                orderRejectAccept.setVisibility(View.VISIBLE);
                orderStatusText1.setText(getString(R.string.orderIsRejected));
                orderStatusText2.setText("Order\nrejected!");
                callFeedBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callingPhoneToRest(phoneNumber);
                    }
                });
            }
            else if (orderStatus.equals("Open")){
                orderOpen.setVisibility(View.VISIBLE);
                orderRejectAccept.setVisibility(View.GONE);
                callFeedBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callingPhoneToRest(phoneNumber);
                    }
                });

            } else if (orderStatus.equals("Delivered")){
                requestLayout.setVisibility(View.GONE);
                orderOpen.setVisibility(View.GONE);
                orderRejectAccept.setVisibility(View.VISIBLE);
                thanksLayout.setVisibility(View.VISIBLE);
                callFeedBackBtn.setText(getString(R.string.giveFeedback));
                callFeedBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YourOrderStatusActivity.this, FeedbackActivity.class);
                        intent.putExtra("restID",restID);
                        startActivity(intent);
                    }
                });
            }
        }

    }

    private void callingPhoneToRest(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phoneNumber));
        startActivity(intent);
    }

    @OnClick(R.id.left)
    void setLeft() {
        onBackPressed();
    }
}
