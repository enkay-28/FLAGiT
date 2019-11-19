package com.test.flagit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class RegConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reg_confirm);
        String ngoID = getIntent().getStringExtra("ngoid");
        TextView idMsg = findViewById(R.id.id_msg);
        idMsg.setText("Your ID is "+ngoID);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(RegConfirm.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);

    }
}
