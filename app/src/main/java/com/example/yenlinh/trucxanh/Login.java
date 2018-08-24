package com.example.yenlinh.trucxanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1, b2;
    Intent myIntend;
    private MyDatabase database = new MyDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Login.this,"Home", Toast.LENGTH_SHORT).show();

                database.open();
                boolean check = database.kiemTraLogin(ed1.getText().toString(), ed2.getText().toString());
                String getId = database.getId(ed1.getText().toString(), ed2.getText().toString());
                database.close();

                if(check){
                  //  Toast.makeText(Login.this,"Log in success", Toast.LENGTH_SHORT).show();

                    // chuyen sang choi game
                    myIntend  = new Intent(Login.this, GameScence.class);

                    Bundle mybundle = new Bundle();
                    mybundle.putString("acc",getId);
                    myIntend.putExtra("AccInFo", mybundle);
                    startActivity(myIntend);

                    startActivity(myIntend);

                }else{
                    Toast.makeText(Login.this,"Log in fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this,"Log up", Toast.LENGTH_LONG).show();

                myIntend  = new Intent(Login.this, Logup.class);
                startActivity(myIntend);
            }
        });

    }
}
