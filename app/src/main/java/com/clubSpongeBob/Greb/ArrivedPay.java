package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrivedPay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived_pay);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageView imageView = view.findViewById(R.id.backNavigation);

        imageView.setVisibility(View.GONE);

        TextView name = view.findViewById(R.id.name);
        name.setText("Arrived and pay");
        TextView amountView = this.findViewById(R.id.amountTextView);
        amountView.setText(String.format("RM%.2f", CommonUtils.getSelectedDriver().getTotalDistance()/1000 * 0.5 ));

        this.findViewById(R.id.payButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.getSelectedDriver().setTotalDistance(0);
                CommonUtils.getSelectedDriver().setTotalDuration(0);
                CommonUtils.getSelf().setStatus(0);
                FirebaseUtils.updateCustomer(CommonUtils.getSelf());
                startActivity(new Intent(view.getContext(), ThankYou.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
}