package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import database.BooksDbHelper;
import models.AllBooks;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView img;
    TextView tvName;

    Button btnSaveToSqlite, btnOpenSaved, btnBack;

    BooksDbHelper db;

    String bookName;
    int bookImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        img = findViewById(R.id.detailsImg);
        tvName = findViewById(R.id.detailsName);

        btnSaveToSqlite = findViewById(R.id.btnSaveToSqlite);
        btnOpenSaved = findViewById(R.id.btnOpenSaved);
        btnBack = findViewById(R.id.btnBack);

        db = new BooksDbHelper(this);

        //  نستقبل من adapter
        bookName = getIntent().getStringExtra("book_name");
        bookImg = getIntent().getIntExtra("book_img", 0);

        if (bookName == null || bookName.trim().isEmpty()) {
            finish();
            return;
        }

        tvName.setText(bookName);
        if (bookImg != 0) img.setImageResource(bookImg);

        btnSaveToSqlite.setOnClickListener(v -> {
            db.insertOrReplace(new AllBooks(0, bookName, bookImg));
            Toast.makeText(this, "Saved to SQLite", Toast.LENGTH_SHORT).show();
        });

        btnOpenSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(BookDetailsActivity.this , SavedBooksActivity.class);
                startActivity(open);
            }
        });



        btnBack.setOnClickListener(v -> finish());
    }
}
