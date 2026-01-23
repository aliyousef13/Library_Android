package com.example.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoriesDetail extends AppCompatActivity {

    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView back0 = findViewById(R.id.but_0);
        TextView back = findViewById(R.id.but_go);
        Button web = findViewById(R.id.but_2);
        Button inf = findViewById(R.id.but_3);
        Button os = findViewById(R.id.but_4);
        btnReset = findViewById(R.id.btnReset);


web.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent w = new Intent(CategoriesDetail.this,WebSecurity.class);
        startActivity(w);
    }
});



os.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent os = new Intent(CategoriesDetail.this,OsSecurity.class);
        startActivity(os);
    }
});

 inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inf = new Intent(CategoriesDetail.this,InformationSecurity.class);
                startActivity(inf);
    }
});


back0.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent b = new Intent(CategoriesDetail.this,RecycelerViewBooks.class);
        startActivity(b);
    }
});

back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent go = new Intent(CategoriesDetail.this,Library.class);
        startActivity(go);
    }
});

btnReset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        SharedPreferences shprefs = getSharedPreferences("Users", MODE_PRIVATE);

        SharedPreferences.Editor editor = shprefs.edit();
        editor.clear();
        editor.apply();

        Intent re = new Intent(CategoriesDetail.this, Login.class);
        startActivity(re);

    }
});


    }
}