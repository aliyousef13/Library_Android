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

    Button backLib, orderNowAll;

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

        backLib= findViewById(R.id.backLib);
        orderNowAll= findViewById(R.id.orderNowAll);


        backLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ls = new Intent(Library.this,CategoriesDetail.class);
                startActivity(ls);

            }
        });
        orderNowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy1 = new Intent(Library.this, ListView_Price.class);
                startActivity(buy1);
            }
        });


    }
}