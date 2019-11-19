package com.test.flagit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InvestigatorScreen extends AppCompatActivity {

    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigator_screen);
        btn =  findViewById(R.id.srch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InvestigatorScreen.this,AddTrafficker.class);
                startActivity(i);
            }
        });
    }
}
