package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Payment_methode2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_methode2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView pal = findViewById(R.id.texBack_pal);
        Button paynow2 = findViewById(R.id.btnPayNow2);


  pal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent pay  = new Intent(Payment_methode2.this,Payment_methode.class);
          startActivity(pay);
      }
  });

  paynow2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent now2 = new Intent(Payment_methode2.this, Payment_successful.class);
          startActivity(now2);
      }
  });
    }
}