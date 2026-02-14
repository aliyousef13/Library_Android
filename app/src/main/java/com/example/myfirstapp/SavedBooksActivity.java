package com.example.myfirstapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.BooksAdapters;
import database.BooksDbHelper;
import models.AllBooks;

public class SavedBooksActivity extends AppCompatActivity {

    RecyclerView recycler;
    Button back;

    BooksAdapters adapter;
    BooksDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_books);

        recycler = findViewById(R.id.recyclerSaved);
        back = findViewById(R.id.btnBackSaved);

        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        recycler.setHasFixedSize(true);

        db = new BooksDbHelper(this);

        ArrayList<AllBooks> list = db.getAllBooks();
        adapter = new BooksAdapters(this, list);
        recycler.setAdapter(adapter);

        back.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setBooklist(db.getAllBooks());
    }
}
