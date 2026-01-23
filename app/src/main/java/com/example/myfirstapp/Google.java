package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Google extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_google);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText goog_email = findViewById(R.id.et_GoogleEmail);
        EditText goog_pass = findViewById(R.id.et_GooglePassword);
        Button but_goog = findViewById(R.id.btn_GoogleLogin);
        Button but_bac = findViewById(R.id.but_backg);



        goog_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Google.this, "Email@123", Toast.LENGTH_SHORT).show();
            }
        });

        goog_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Google.this,"******",Toast.LENGTH_SHORT).show();
            }
        });



    but_goog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent bu_go = new Intent(Google.this,RecycelerViewBooks.class);
            startActivity(bu_go);
        }
    });


        but_bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bu_ba = new Intent(Google.this,MainActivity2.class);
                startActivity(bu_ba);
            }
        });



    }
}