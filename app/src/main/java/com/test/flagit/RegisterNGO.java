package com.test.flagit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class RegisterNGO extends AppCompatActivity {
    EditText name, email, address, pass;
    String nameStr, emailStr, addressStr, passStr;
    Button signup;
    String codeSent;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ngo);
        getSupportActionBar().hide();
        name = findViewById(R.id.name_inv);
        email = findViewById(R.id.email_inv);
        address = findViewById(R.id.address);
        pass = findViewById(R.id.pass_inv);

        signup = findViewById(R.id.sign_btn);
        mAuth = FirebaseAuth.getInstance();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameStr = name.getText().toString();
                emailStr = email.getText().toString();
                addressStr = address.getText().toString();
                passStr = pass.getText().toString();


                if(!nameStr.equals("") || !emailStr.equals("") || !addressStr.equals("") || !passStr.equals("")
                )
                {
                    Intent i = new Intent(RegisterNGO.this,VerifyPhone.class);

                    i.putExtra("name",nameStr);
                    i.putExtra("email",emailStr);
                    i.putExtra("address",addressStr);
                    i.putExtra("pass",passStr);

                    startActivity(i);
                    finish();



                }

            }

        });












    }


}
