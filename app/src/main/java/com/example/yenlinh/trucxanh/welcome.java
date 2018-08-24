package com.example.yenlinh.trucxanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class welcome extends AppCompatActivity {
    ImageView wel_img;
    Intent my_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        wel_img = (ImageView) findViewById(R.id.welcome);
        my_intent = new Intent(this, Level.class);
        wel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(my_intent);
            }
        });
    }
}
