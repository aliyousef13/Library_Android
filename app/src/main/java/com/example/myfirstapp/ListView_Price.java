package com.example.myfirstapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import adapters.PriceAdapter;
import models.Price;

public class ListView_Price extends AppCompatActivity {
  ListView lv; // اول شيئ تم تعريفه
  PriceAdapter adapter;
  ArrayList<Price> priceArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_list_view_price);
        lv = findViewById(R.id.lv_price);   // اول شيئ تم تعريفه
        priceArrayList = new ArrayList<>();

        priceArrayList.add(new Price(1,"OS Security",4,650f,R.drawable.os));
        priceArrayList.add(new Price(2,"Web Security",4,800f,R.drawable.web4));
        priceArrayList.add(new Price(3,"Information Security",4,750f,R.drawable.libraryinformation));

        adapter = new PriceAdapter(ListView_Price.this , priceArrayList);
        lv.setAdapter(adapter);



    }
}