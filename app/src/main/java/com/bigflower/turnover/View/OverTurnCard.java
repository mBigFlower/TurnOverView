package com.bigflower.turnover.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigflower.turnoverview.OverTurnView;


/**
 * Created by Bigflower on 2015/12/6.
 */
public class OverTurnCard extends OverTurnView {

    private TextView mTextView ;
    private Button button1, button2 ;

    public OverTurnCard(Context context) {
        super(context);

    }

    public OverTurnCard(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public OverTurnCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setmTextView(String text){
        mTextView.setText(text);
    }

    /**
     * 在控件初始化的时候即执行
     * @param posView
     */
    @Override
    public void posShow(View posView) {
        super.posShow(posView);
        mTextView = (TextView) posView.findViewById(com.bigflower.turnoverview.R.id.posLayout_text2);
    }

    /**
     * 当反转后，才执行，此时再加载反面的布局及获取控件
     * @param negView
     */
    @Override
    public void negShow(View negView) {
        super.negShow(negView);
        button1 = (Button) negView.findViewById(com.bigflower.turnoverview.R.id.negLayout_bt1);
        button2 = (Button) negView.findViewById(com.bigflower.turnoverview.R.id.negLayout_bt2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了Button1", Toast.LENGTH_LONG).show();
                turnOver();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了Button2", Toast.LENGTH_LONG).show();
                turnOver();
            }
        });
    }
}
