package com.bigflower.turnover.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigflower.turnover.R;
import com.bigflower.turnoverview.TurnOverViewOnly;


/**
 * Created by Bigflower on 2015/12/6.
 *
 *
 */
public class TurnOverCard extends TurnOverViewOnly {

    private TextView mTextView2, mTextView3 ;
    private Button button1, button2 ;

    public TurnOverCard(Context context) {
        super(context);

    }

    public TurnOverCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public TurnOverCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOver();
            }
        });
    }


    public void setmTextView(String text){
        mTextView2.setText(text);
    }

    /**
     * 在控件初始化的时候即执行
     * @param posView
     */
    @Override
    public void posShow(View posView) {
        super.posShow(posView);
        mTextView2 = (TextView) posView.findViewById(R.id.posLayout_text2);
        mTextView3 = (TextView) posView.findViewById(R.id.posLayout_text3);
    }

    /**
     * 当反转后，才执行，此时再加载反面的布局及获取控件
     * @param negView
     */
    @Override
    public void negShow(View negView) {
        super.negShow(negView);
        button1 = (Button) negView.findViewById(R.id.negLayout_bt1);
        button2 = (Button) negView.findViewById(R.id.negLayout_bt2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了Button1", Toast.LENGTH_SHORT).show();
                mTextView2.setText("点击了Button1");
                turnOver();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了Button2", Toast.LENGTH_SHORT).show();
                mTextView2.setText("点击了Button2");
                turnOver();
            }
        });
    }
}
