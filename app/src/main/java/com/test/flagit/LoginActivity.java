package com.test.flagit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button regBtn, signIn;
    EditText userId,pass;
    String userIdStr, passStr;
    DatabaseReference ngoDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        regBtn = findViewById(R.id.reg_ngo_btn);
        signIn = findViewById(R.id.login_btn);
        userId = findViewById(R.id.user_field);
        pass = findViewById(R.id.pass_field);
        ngoDB = FirebaseDatabase.getInstance().getReference("ngo");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userIdStr = userId.getText().toString();
                passStr = pass.getText().toString();

                if(!userIdStr.equals("") || !passStr.equals("")){
                    if (userIdStr.startsWith("NGO")){
                        //check database for NGO
                        ngoDB.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot as : dataSnapshot.getChildren()){
                                    NgoDetails dt = as.getValue(NgoDetails.class);
                                    if(dt.getNgoID().equals(userIdStr)){
                                        if(dt.getPass().equals(passStr)){
                                            Intent i = new Intent(LoginActivity.this,NGOActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });
                    }
                    else if(userIdStr.startsWith("INV")){
                        //check databse for INVESTIGATOR

                        Intent i = new Intent(LoginActivity.this,InvestigatorScreen.class);
                        startActivity(i);
                        finish();
                    }


                }

            }
        });


        //register button onclick
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(LoginActivity.this,RegisterNGO.class);
                startActivity(i)
                ;

            }
        });
    }
}
