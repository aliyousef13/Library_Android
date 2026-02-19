package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import fragments.Cyber_Fragment;

public class MainCyber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_cyber);

        // لمنع إضافة Fragment مرة ثانية عند دوران الشاشة
        if (savedInstanceState != null) return;

        Intent intent = getIntent();


        Bundle args = new Bundle();
        args.putString("title", intent.getStringExtra("title"));
        args.putFloat("price", intent.getFloatExtra("price", 0f));
        args.putFloat("rating", intent.getFloatExtra("rating", 4f));
        args.putInt("imageRes", intent.getIntExtra("imageRes", 0));

        // Fragment object
        Cyber_Fragment cyberFragment = new Cyber_Fragment();
        cyberFragment.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fm, cyberFragment); // replace أفضل من add
        ft.commit();
    }
}
