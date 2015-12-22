package com.bigflower.turnover.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigflower.turnover.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void OneClick(View v){
        startActivity(new Intent(this, OneActivity.class));
    }

    public void GridClick(View v){
        startActivity(new Intent(this, GridCardActivity.class));
    }
}