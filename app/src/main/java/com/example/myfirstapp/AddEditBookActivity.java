package com.example.myfirstapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import database.BooksDbHelper;
import models.AllBooks;

public class AddEditBookActivity extends AppCompatActivity {

    EditText etName;
    Spinner spImg;
    Button btnSave, btnCancel;

    BooksDbHelper db;

    // صور تختار منها
    int[] imgValues = {
            R.drawable.operating, R.drawable.libraryinformation, R.drawable.libraryweb,
            R.drawable.network, R.drawable.malware, R.drawable.digital,
            R.drawable.cloud, R.drawable.hacking, R.drawable.database
    };
    String[] imgLabels = {
            "Operating", "Information", "Web",
            "Network", "Malware", "Digital",
            "Cloud", "Hacking", "Database"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);



        db = new BooksDbHelper(this); //فتح قاعدة البيانات

        etName = findViewById(R.id.etBookName);
        spImg = findViewById(R.id.spBookImg);
        btnSave = findViewById(R.id.btnSaveBook);
        btnCancel = findViewById(R.id.btnCancelBook);

        spImg.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, imgLabels));


        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Enter book name", Toast.LENGTH_SHORT).show();
                return;
            }

            int img = imgValues[spImg.getSelectedItemPosition()];
            db.insertOrReplace(new AllBooks(0, name, img));
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
