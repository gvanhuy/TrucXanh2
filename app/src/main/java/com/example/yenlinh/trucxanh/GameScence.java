package com.example.yenlinh.trucxanh;

import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

public class GameScence extends AppCompatActivity {
    ImageView img[];
    TextView txt1;
    int check[] ;
    int flag = 0;
    int current;
    int score;
    String idacc;
    boolean ischeck[];
    String user_score;
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long startTime = 60 * 1000;
    private final long interval = 1 * 1000;
    private MyDatabase database = new MyDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_scence);

        init();

        setUpValue(check, 9);

        onClick(img[0],0);
        onClick(img[1],1);
        onClick(img[2],2);
        onClick(img[3],3);
        onClick(img[4],4);
        onClick(img[5],5);
        onClick(img[6],6);
        onClick(img[7],7);
        onClick(img[8],8);
        txt1 = (TextView) findViewById(R.id.text1);
        txt1.setText( user_score);


       /* countDownTimer = new MyCountDownTimer(startTime, interval);
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
        } else {
            //  countDownTimer.cancel();
            //  timerHasStarted = false;
        }*/


    }

    void init(){
        check = new int[9];
        img = new ImageView[9];
        ischeck = new boolean[9];
        score = 0;


        img[0] = (ImageView) findViewById(R.id.img_1);
        img[1] = (ImageView) findViewById(R.id.img_2);
        img[2] = (ImageView) findViewById(R.id.img_3);
        img[3] = (ImageView) findViewById(R.id.img_4);
        img[4] = (ImageView) findViewById(R.id.img_5);
        img[5] = (ImageView) findViewById(R.id.img_6);
        img[6] = (ImageView) findViewById(R.id.img_7);
        img[7] = (ImageView) findViewById(R.id.img_8);
        img[8] = (ImageView) findViewById(R.id.img_9);


        for(int i = 0; i<9; i++){
            img[i].setVisibility(View.VISIBLE);
        }

        for ( int i = 0; i<9; i++){
            check [i] = -1;
            ischeck[i] = false;
        }

        Intent myIntent = getIntent();
        Bundle mypackage = myIntent.getBundleExtra("AccInFo");

        idacc = mypackage.getString("id");

        database.open();
        user_score = database.getSCore("linh","123");
        database.close();
    }

    void setUpValue ( int a[], int n){
        Random r = new Random();
        int inew = 0;
        Vector <Integer> v = new Vector();
        int i ;
        for ( i = 0; i < n; ){
            inew = r.nextInt(n);
            if(!v.contains(inew)){
                i++;
                v.add(inew);
            }
        }

        inew = 0;
        i = 0;

        if (n / 2 != 0){
            check[v.indexOf(i)] = inew;
            inew++;
            i++;
        }

        while (i < n)
        {
            check[v.indexOf(i)] = inew;
            check[v.indexOf(i+1)] = inew;
            i = i + 2;
            inew++;

        }


    }

    void onClick(ImageView a, final int i){
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag ++;

                if((check[i] == 0)&&(ischeck[i]==false)) {
                    score += 20;
                    txt1.setText(String.valueOf(score));
                    img[current].setVisibility(View.VISIBLE);
                    img[i].setVisibility(View.INVISIBLE);
                    ischeck[i] = true;
                    flag = 0;
                    return;
                }




                if(flag == 2){

                    if((check[current] == check[i])&&(current!=i)){
                        img[i].setVisibility(View.INVISIBLE);
                        img[current].setVisibility(View.INVISIBLE);


                        score += 5;


                        txt1.setText(String.valueOf(score));

                        ischeck[i] = ischeck [current] = true;

                    }else {
                        img[i].setVisibility(View.VISIBLE);
                        img[current].setVisibility(View.VISIBLE);
                    }// khuc nay nguoc lai chua dung
                    // newy check true het  thi restar game
                    // count so o mo trung
                    // het gio se  pop up high score
                    // mot nut pause
                    // database luu ten ng choi va hien len bang diem
                    // lam sao tao 2 mode choi ?de xc
                    //dua game scene ve signlen ton
                    // on create o start on pause?

                    flag = 0;


                }

                current = i;

                isDone();
            }
        });

    }

    void isDone(){
        int flag = 0;
        for( int i = 0; i <9 ; i++){
            if(ischeck[i]){
               flag ++;
            }
        }
        if(flag==9){
            score += 100;
            txt1.setText("Chuc mung ban da thang tong so diem ban co la : " + String.valueOf(score));
            database.open();
            database.setScore(idacc, String.valueOf(score));
            database.close();
        }



    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            txt1.setText("Time's up!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            txt1.setText("" + millisUntilFinished / 1000);
        }
    }
}


