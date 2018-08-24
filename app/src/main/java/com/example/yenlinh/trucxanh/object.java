package com.example.yenlinh.trucxanh;

/**
 * Created by yenlinh on 10/26/2016.
 */

import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import static java.security.AccessController.getContext;

public class object extends AppCompatActivity {
    private int m_id;
    ImageView m_img;
    private  boolean check;

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    public void setM_img(int name) {
        m_img = (ImageView) findViewById(name);
    }

    public ImageView getM_img() {
        return m_img;
    }

    public object(){
        check = false;
    }

    public void setM_id(int id){
        this.m_id = id;
    }

    public  int getM_id(){
        return this.m_id;
    }


}
