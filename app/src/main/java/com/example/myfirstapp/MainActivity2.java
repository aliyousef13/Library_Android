package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });





        EditText userN = findViewById(R.id.etUserName);
        EditText email_1 = findViewById(R.id.etEmail);
        EditText password_1 = findViewById(R.id.etPassword);

        Button register = findViewById(R.id.btnRegister_1);
        Button google = findViewById(R.id.but_google);

        TextView l=findViewById(R.id.tv_login);





        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ll=new Intent(MainActivity2.this,Login.class);
                startActivity(ll);
            }
        });



email_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this,"Email@123",Toast.LENGTH_SHORT).show();

            }
        });
password_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this,"*******",Toast.LENGTH_SHORT).show();

            }
        });



register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(MainActivity2.this,RecycelerViewBooks.class);
                startActivity(reg);
            }
        });



google.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent goog = new Intent(MainActivity2.this, Google.class);
        startActivity(goog);
    }
});










    }
}