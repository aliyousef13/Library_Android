package com.example.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
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


    EditText etEmail_login, etPassword_login;
    SharedPreferences sharedPref;
    String username,password,user1,pass1;

    Button btnLogin_1 , btnbacklogin_1;

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


        etEmail_login = findViewById(R.id.etEmail_login);
        etPassword_login = findViewById(R.id.etPassword_login);
        btnLogin_1 = findViewById(R.id.btnLogin_1);
        btnbacklogin_1 = findViewById(R.id.btnbacklogin_1);

        sharedPref = getSharedPreferences("Users",MODE_PRIVATE);
        username = sharedPref.getString("username","");
        password = sharedPref.getString("password","");




        etEmail_login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Login.this, "Email@123", Toast.LENGTH_SHORT).show();
    }
});

        etPassword_login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Login.this,"******",Toast.LENGTH_SHORT).show();
    }
});

        if (!username.isEmpty() && !password.isEmpty()) {
            Intent cond = new Intent(Login.this, CategoriesDetail.class);
            startActivity(cond);
            finish();
            return;
        }


        btnLogin_1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            user1 = etEmail_login.getText().toString();
            pass1 = etPassword_login.getText().toString();

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username" , user1);
            editor.putString("password" , pass1);
            editor.apply();



            Intent log = new Intent(Login.this,RecycelerViewBooks.class);
            startActivity(log);
        }
    });



        btnbacklogin_1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent log_ba = new Intent(Login.this,MainActivity2.class);
            startActivity(log_ba);
        }
    });





    }
}