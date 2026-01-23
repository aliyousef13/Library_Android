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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.BooksAdapters;
import models.AllBooks;

public class RecycelerViewBooks extends AppCompatActivity {

    RecyclerView recyclerBooks;
    BooksAdapters adapter;
    ArrayList<AllBooks> booklist;

    Button btn_LookBooks, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyceler_view_books);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerBooks = findViewById(R.id.recyclerBooks);
        btn_LookBooks = findViewById(R.id.btn_LookBooks);
        btnBack = findViewById(R.id.btnBackRegister);

        //  Grid أغلفة كتب
        recyclerBooks.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerBooks.setHasFixedSize(true);

        //  ArrayList (AllBooks)
        booklist = new ArrayList<>();

        // ✅ بيانات مؤقتة (اسم + صورة فقط)
        booklist.add(new AllBooks(1, "Operating System", R.drawable.operating));
        booklist.add(new AllBooks(2, "Information Security", R.drawable.libraryinformation));
        booklist.add(new AllBooks(3, "Web Security", R.drawable.libraryweb));
        booklist.add(new AllBooks(4, "Network Security", R.drawable.network));
        booklist.add(new AllBooks(5, "Malware Analysis", R.drawable.malware));
        booklist.add(new AllBooks(6, "Digital Forensics", R.drawable.digital));
        booklist.add(new AllBooks(7, "Cloud Security", R.drawable.cloud));
        booklist.add(new AllBooks(8, "Ethical Hacking", R.drawable.hacking));
        booklist.add(new AllBooks(9, "Database Security", R.drawable.database));

        adapter = new BooksAdapters(RecycelerViewBooks.this, booklist);
        recyclerBooks.setAdapter(adapter);

        //  Look at some books -> CategoriesDetail
        btn_LookBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lookb = new Intent(RecycelerViewBooks.this, CategoriesDetail.class);
                startActivity(lookb);
                finish();
            }
        });

        //  Back -> يرجع على Register (MainActivity2)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac = new Intent(RecycelerViewBooks.this, MainActivity2.class);
                startActivity(bac);
                finish();
            }
        });
    }
}
