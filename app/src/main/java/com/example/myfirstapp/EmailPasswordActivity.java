package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String TAG = "A";

    EditText email_edi, pass_edi;
    Button signIn_but, signup_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email_password);

        mAuth = FirebaseAuth.getInstance();

        email_edi = findViewById(R.id.email_edi);
        pass_edi = findViewById(R.id.pass_edi);
        signup_but = findViewById(R.id.signup_but);
        signIn_but = findViewById(R.id.signIn_but);



        signup_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edi.getText().toString().trim();
                String pass = pass_edi.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(EmailPasswordActivity.this, "Please enter email & password", Toast.LENGTH_SHORT).show();
                    return;
                }

                createAccount(email, pass);



            }
        });



        signIn_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edi.getText().toString().trim(); // "";
                String pass = pass_edi.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(EmailPasswordActivity.this, "Please enter email & password", Toast.LENGTH_SHORT).show();
                    return;
                }


                signInAccount(email, pass);
            }
        });


    }

// singIn

    // إذا المستخدم مسجل دخول من قبل وديه على صفحة الكتب عدل
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
          //  reload();
 startActivity(new Intent(EmailPasswordActivity.this, RecycelerViewBooks.class));
 finish();
        }
    }


// Sign Up
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(EmailPasswordActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed: " +
                                            (task.getException() != null ? task.getException().getMessage() : ""),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    // signIn
    private void signInAccount(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(EmailPasswordActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            // افتح صفحة الكتب فقط بعد نجاح تسجيل الدخول

                            startActivity(new Intent(EmailPasswordActivity.this, RecycelerViewBooks.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}