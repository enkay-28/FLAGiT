package com.test.flagit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VerifyPhone extends AppCompatActivity {
    DatabaseReference ngoDB;
    EditText otp,mobile;
    Button verify,send;
    FirebaseAuth mAuth;
    String codeSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        getSupportActionBar().hide();
        ngoDB = FirebaseDatabase.getInstance().getReference("ngo");
        otp=findViewById(R.id.otp_field);
        mAuth = FirebaseAuth.getInstance();
        verify=findViewById(R.id.verify_btn);
        mobile=findViewById(R.id.mob_field);
        send=findViewById(R.id.send_btn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtp();
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!otp.getText().toString().equals("")) {

                    verifyOtp();
                }
            }});





    }

    private void sendOtp(){

        String mobileStr;
        if(mobile.getText().toString().isEmpty()){
            mobile.setError("Enter Mobile");
            mobile.requestFocus();
            return;
        }

        mobileStr = mobile.getText().toString();
        if(mobileStr.length()!=10){
            mobile.setError("Enter 10 digits");
            mobile.requestFocus();

        }
        else {


            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mobileStr,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                        }

                        @Override
                        public void onVerificationFailed(FirebaseException e) {

                        }

                        @Override
                        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                            super.onCodeSent(s, forceResendingToken);
                            codeSent = s;
                            otp.requestFocus();


                        }
                    });
    }}
    private void verifyOtp(){

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent
                , otp.getText().toString());

        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            register();
                            // ...
                        } else {
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(getApplicationContext(),"Wrong OTP entered",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }

    private void register() {
        int min = 100000,max = 1000000;
        int rand1 = (int) ((Math.random() * ((max - min) + 1)) + min);
        Intent i = getIntent();
        String ngoID = "NGO"+ String.valueOf(rand1);
        NgoDetails dt = new NgoDetails(i.getStringExtra("name"),ngoID,i.getStringExtra("email")
                ,i.getStringExtra("pass"),i.getStringExtra("address"),mobile.getText().toString());

        ngoDB.child(ngoID).setValue(dt);

        Intent it = new Intent(VerifyPhone.this, RegConfirm.class);
        it.putExtra("ngoid",ngoID);

        startActivity(it);
        finish();
    }



}
