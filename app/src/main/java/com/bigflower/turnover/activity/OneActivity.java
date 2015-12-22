package com.bigflower.turnover.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bigflower.turnover.R;
import com.bigflower.turnover.View.TurnOverCard;

public class OneActivity extends AppCompatActivity {

    TurnOverCard turnOverCard ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        turnOverCard = (TurnOverCard) findViewById(R.id.one_turnOver);
    }

}
