package com.test.flagit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PopulateData extends AppCompatActivity {
    EditText name,loc,crime;
    Button sub,add;
    String imgDecodableString;
    ImageView img;
    DatabaseReference traffickers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populate_data);
        sub = findViewById(R.id.sub_btn);
        name = findViewById(R.id.nametr);
        loc = findViewById(R.id.loc);
        crime = findViewById(R.id.crime);
        add = findViewById(R.id.plus);
        img = findViewById(R.id.photo);

        traffickers = FirebaseDatabase.getInstance().getReference("traffickers");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String loc1 = loc.getText().toString();
                String crime1 = crime.getText().toString();
                String id = traffickers.push().getKey();
                Trafficker tf = new Trafficker(name1,loc1,crime1,imgDecodableString,false);
                traffickers.child(id).setValue(tf);
                Intent i = new Intent(PopulateData.this,AddTrafficker.class);
                startActivity(i);
                finish();




            }
        });


    }
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,1);

    }

    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 1:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    img.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

            }
    }


    }