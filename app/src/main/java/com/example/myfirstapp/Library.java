package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Library extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_library);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button ordernow1= findViewById(R.id.osOrderButton);
        Button ordernow2= findViewById(R.id.webOrderButton);
        Button ordernow3= findViewById(R.id.infoOrderButton);

        Button back4 = findViewById(R.id.but_14);


        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ls = new Intent(Library.this,CategoriesDetail.class);
                startActivity(ls);

            }
        });
        ordernow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy1 = new Intent(Library.this, ListView_Price.class);
                startActivity(buy1);
            }
        });

        ordernow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy2 = new Intent(Library.this,ListView_Price.class);
                startActivity(buy2);
            }
        });

        ordernow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy3 = new Intent(Library.this,ListView_Price.class);
                startActivity(buy3);
            }
        });



    }
}