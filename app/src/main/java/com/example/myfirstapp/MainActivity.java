package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->{
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button start = findViewById(R.id.star_now);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(s);
            }
        });




























//        ImageView img = findViewById(R.id.im_vie);
//        EditText et = findViewById(R.id.etBloodType);    A+
//        TextView tv = findViewById(R.id.tvChronic);      Do you have chronic disease?
//        Button bt = findViewById(R.id.btnRegister);      Register
//        TextView tvlogin = findViewById(R.id.tvLogin);



//
//        img.setImageResource(R.drawable.blood_bank);
//
//
//        et.setHintTextColor(R.color.blue);
//        et.setTextSize(20f);
//     et.setActivated(true);
//
//        tv.setText("Do you suffer from an injury?");
//
//        bt.setTextSize(22f);
//        bt.setTextColor(R.color.black);
//





        // تضع الرسالة عندما تضغط على زر معين
//bt.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//      tv.setText("Do you have chronic disease????????");
//      String r = String.valueOf(tv.getText());
//      et.setText(r); بتعرض النص الى فوق لما تضغط على الزر بدل A+
//       et.setHintTextColor(R.color.red);

//    }
//});



//tvlogin.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        // رسالة ضبابية
//
//        Toast.makeText(MainActivity.this, "soory, you must enter your email and password", Toast.LENGTH_SHORT).show();
//
//       // Toast.makeText(MainActivity.this, "dsdkg[og0dkgp]", Toast.LENGTH_LONG).show();
//
//    }
//});







    }
}