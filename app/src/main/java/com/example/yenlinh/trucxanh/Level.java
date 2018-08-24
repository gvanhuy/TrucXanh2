package com.example.yenlinh.trucxanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Level extends AppCompatActivity {

    Intent myItent;
    ImageView a[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        init();
        for( int i = 0; i<3; i++){
            onClick(a[i]);
            Toast.makeText(getApplication(), String.valueOf(i), Toast.LENGTH_LONG).show();
        }

    }

    public  void init() {
        a = new ImageView[3];
        a[0] = (ImageView) findViewById(R.id.level_1);
        a[1] = (ImageView) findViewById(R.id.level_2);
        a[2] = (ImageView) findViewById(R.id.level_3);
       // myItent = new Intent(this, GameScence.class);
    }

    public void onClick(ImageView a){
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(myItent);
            }
        });
    }

}
