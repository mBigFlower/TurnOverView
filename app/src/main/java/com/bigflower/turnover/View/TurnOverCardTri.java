package com.bigflower.turnover.View;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigflower.turnover.R;
import com.bigflower.turnoverview.TurnOverViewOnly;

/**
 * Created by Bigflower on 2015/12/11.
 * <p/>
 * the card have three state, we can change their state by two button
 */
public class TurnOverCardTri extends TurnOverViewOnly {

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_ONE = 1;
    private static final int STATE_TWO = 2;
    // COLOR is one to one for STATE
    private static final int COLOR_DEFAULT = Color.BLACK;
    private static final int COLOR_ONE = Color.rgb(138, 197, 232);
    private static final int COLOR_TWO = Color.rgb(147, 224, 179);

    private int cardState = STATE_DEFAULT;

    private View leftLine ;
    private TextView mTextView1, mTextView2, mTextView3;
    private Button button1, button2, button3;

    public TurnOverCardTri(Context context) {
        super(context);
    }

    public TurnOverCardTri(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TurnOverCardTri(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在控件初始化的时候即执行
     *
     * @param posView
     */
    @Override
    public void posShow(View posView) {
        super.posShow(posView);
        mTextView1 = (TextView) posView.findViewById(R.id.posLayout_text1);
        mTextView2 = (TextView) posView.findViewById(R.id.posLayout_text2);
        mTextView3 = (TextView) posView.findViewById(R.id.posLayout_text3);
        leftLine = posView.findViewById(R.id.posLayout_line);
    }

    /**
     * 当反转后，才执行，此时再加载反面的布局及获取控件
     * this function do only once .
     *
     * @param negView
     */
    @Override
    public void negShow(View negView) {
        super.negShow(negView);
        button1 = (Button) negView.findViewById(R.id.negLayout_bt1);
        button2 = (Button) negView.findViewById(R.id.negLayout_bt2);
        button3 = (Button) negView.findViewById(R.id.negLayout_bt3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "点击了Button1", Toast.LENGTH_LONG).show();
                setStateOne();
                turnOver();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "点击了Button2", Toast.LENGTH_LONG).show();
                setStateTwo();
                turnOver();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "点击了Button2", Toast.LENGTH_LONG).show();
                setStateDefault();
                turnOver();
            }
        });

        setNegLayout();
    }

    public void initCard(String text1, String text3, int state) {
        mTextView1.setText(text1);
        mTextView3.setText(text3);

        switch (state) {
            case STATE_DEFAULT:
                setStateDefault();
                break;
            case STATE_ONE:
                setStateOne();
                break;
            case STATE_TWO:
                setStateTwo();
                break;
        }
    }

    private void setStateDefault() {
        cardState = STATE_DEFAULT;
        leftLine.setVisibility(View.INVISIBLE);
        mTextView1.setTextColor(COLOR_DEFAULT);
        mTextView2.setTextColor(COLOR_DEFAULT);
        mTextView2.setText(null);
        mTextView3.setText(null);
    }

    private void setStateOne() {
        cardState = STATE_ONE;
        leftLine.setVisibility(View.VISIBLE);
        leftLine.setBackgroundColor(COLOR_ONE);
        mTextView1.setTextColor(COLOR_ONE);
        mTextView2.setTextColor(COLOR_ONE);
        mTextView2.setText("状态Btn1");
        mTextView3.setText("descreption");
    }

    private void setStateTwo() {
        cardState = STATE_TWO;
        leftLine.setVisibility(View.VISIBLE);
        leftLine.setBackgroundColor(COLOR_TWO);
        mTextView1.setTextColor(COLOR_TWO);
        mTextView2.setTextColor(COLOR_TWO);
        mTextView2.setText("状态Btn2");
        mTextView3.setText("descreption");
    }

    /**
     * Notice!!!
     * Notice!!!
     * Notice!!!
     * <p/>
     * if you want change the negLayout's widget's state. you can do this function first, then turnOVer
     * <p/>
     * here I decide if the layout is created, if not, do nothing
     */
    public void setNegLayout() {
        // if neg's widget is null, we do this funciont in negShow() instead of here
        if (button1 == null)
            return;
        if (cardState == STATE_DEFAULT) {
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.GONE);
        } else if (cardState == STATE_ONE) {
            button3.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button1.setVisibility(View.GONE);
        } else if (cardState == STATE_TWO) {
            button1.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);
        }
    }
}
