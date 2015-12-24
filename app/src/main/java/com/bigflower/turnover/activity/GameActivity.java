package com.bigflower.turnover.activity;

import android.animation.LayoutTransition;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigflower.turnover.R;
import com.bigflower.turnover.View.TurnOverGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button gameButton;
    private GridLayout gameLayout;
    private TextView timeTv;
    private TurnOverGame[] turnOverGame = new TurnOverGame[8];
    private List<Integer> gameCardNumber = new ArrayList<>();

    // 翻转的到底是哪个？
    private TurnOverGame oneCard, otherCard;

    private boolean isGaming;
    private int pressTimes;
    private int leftImgPair = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViews();
        initCardNumber();
        initGameCard();
    }

    private void initViews() {
        gameButton = (Button) findViewById(R.id.game_button);
        gameLayout = (GridLayout) findViewById(R.id.game_cardLayout);
        timeTv = (TextView) findViewById(R.id.game_timeText);

    }

    /**
     * 把{1,1,2,2,3,3,4,4}，随机分配给8个数
     */
    private void initCardNumber() {
        Integer[] array = {1, 1, 2, 2, 3, 3, 4, 4};
        gameCardNumber = Arrays.asList(array);
        Collections.shuffle(gameCardNumber);
    }

    /**
     * 创建8个卡片，并添加到layout中。
     * 每个卡片有一个固定的number，且不同的number有着不同的图片
     */
    private void initGameCard() {
        int width = 160;
        for (int i = 0; i < 8; i++) {
            turnOverGame[i] = new TurnOverGame(this,
                    R.layout.game_poslayout, R.layout.game_neglayout, number2Res(i));
            turnOverGame[i].setOnTOGameListener(gameListeners);
            gameLayout.addView(turnOverGame[i], width, width);
        }
    }

    /**
     * 不同的number ， 对应不同的图片
     *
     * @param index
     * @return
     */
    private int number2Res(int index) {
        int number = gameCardNumber.get(index);
        if (number == 1)
            return R.mipmap.img_game1;
        else if (number == 2)
            return R.mipmap.img_game2;
        else if (number == 3)
            return R.mipmap.img_game3;
        else
            return R.mipmap.img_game4;
    }

    public void GameButtonCLick(View v) {
        if (isGaming) {
            isGaming = false;
            turnOverReset();
        } else {
            isGaming = true;
            turnOverAll();
            pressTimes = 0;
            leftImgPair = 4;
        }
    }

    private void turnOverReset() {
        gameLayout.removeAllViews();
        initCardNumber();
        initGameCard();
    }

    private void turnOverAll() {
        for (int i = 0; i < 8; i++) {
            turnOverGame[i].turnOver();
            turnOverGame[i].setClickable(true);
        }
    }


    /**
     * card的监听器，判断较多
     */
    TurnOverGame.OnTOGameListener gameListeners = new TurnOverGame.OnTOGameListener() {
        @Override
        public void OnClick(TurnOverGame v) {
            pressTimes++;
            if (oneCard == null) {
                oneCard = v;
            } else {
                otherCard = v;
                if (oneCard.getImageRes() != otherCard.getImageRes()) {
                    oneCard.turnOver();
                    otherCard.turnOver();
                } else {
                    oneCard.setClickable(false);
                    otherCard.setClickable(false);
                    leftImgPair--;
                    // 配对成功
                    if (leftImgPair == 0) {
                        isGaming = false ;
                        timeTv.setText("成功\n点击了" + pressTimes + "次");
                    }
                }
                oneCard = otherCard = null;
            }
        }
    };

}
