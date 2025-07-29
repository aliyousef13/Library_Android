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

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button log_in = findViewById(R.id.btnLogin_1);
        Button log_back = findViewById(R.id.btnbacklogin_1);
        EditText email_2 = findViewById(R.id.etEmail_login);
        EditText password_2 = findViewById(R.id.etPassword_login);




email_2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Login.this, "Email@123", Toast.LENGTH_SHORT).show();
    }
});

password_2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Login.this,"******",Toast.LENGTH_SHORT).show();
    }
});



    log_in.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent log = new Intent(Login.this,CategoriesDetail.class);
            startActivity(log);
        }
    });



    log_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent log_ba = new Intent(Login.this,MainActivity2.class);
            startActivity(log_ba);
        }
    });





    }
}