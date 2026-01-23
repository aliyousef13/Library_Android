package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Payment_methode extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_methode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        TextView visa = findViewById(R.id.texBack_visa);
        RadioButton paypal = findViewById(R.id.radioPaypal);
        Button paynow = findViewById(R.id.btn_PayNow);


        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vis = new Intent(Payment_methode.this, ListView_Price.class);
                startActivity(vis);
            }


        });

     paypal.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent pay = new Intent(Payment_methode.this,Payment_methode2.class);
             startActivity(pay);
         }
     });

     paynow.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent now = new Intent(Payment_methode.this, Payment_successful.class);
             startActivity(now);
         }
     });

    }
}