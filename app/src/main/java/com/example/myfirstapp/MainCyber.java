package com.example.myfirstapp;

import android.os.Bundle;
import android.widget.FrameLayout;

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
        FrameLayout fram = findViewById(R.id.fm);
        // Fragment object
        Cyber_Fragment cyberFragment = new Cyber_Fragment();
        // Get the FragmentManager and start a Transaction
        FragmentManager myFragmentManager = getSupportFragmentManager();

        //Fragment transaction and begin transaction
        FragmentTransaction myFragmentTransaction = myFragmentManager.beginTransaction();
        myFragmentTransaction.add(R.id.fm,cyberFragment);
        myFragmentTransaction.commit();








    }
}