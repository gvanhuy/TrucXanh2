package com.example.yenlinh.trucxanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Logup extends AppCompatActivity {

    EditText edt3, edt4;
    Button btn3;
    Intent myIntent;
    private MyDatabase database = new MyDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);

        edt3 = (EditText) findViewById(R.id.editText3);
        edt4 = (EditText) findViewById(R.id.editText4);
        btn3 = (Button)findViewById(R.id.button3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.open();
                boolean already = database.checkUser(edt3.getText().toString());
                if(!already){
                    database.createData(edt3.getText().toString(), edt4
                            .getText().toString());

                    boolean check = database.kiemTraLogin(edt3.getText().toString(), edt4
                            .getText().toString());

                    database.close();

                    if(check){
                        Toast.makeText(Logup.this,"Success, moi ban dang nhap" + check, Toast.LENGTH_LONG).show();
                        myIntent = new Intent(Logup.this, Login.class);
                        startActivity(myIntent);
                    }else{
                        Toast.makeText(Logup.this,"Fail, co loi khi dang ki" + check, Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Logup.this,"Fail, ten dang nhap da co", Toast.LENGTH_LONG).show();
                }




            }
        });

    }
}
