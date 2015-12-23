package com.bigflower.turnover.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigflower.turnover.R;
import com.bigflower.turnoverview.TurnOverView;

/**
 * Created by Administrator on 2015/12/23.
 */
public class TurnOverGame extends TurnOverView {

    private OnTOGameListener mInterface ;

    private int imageRes;
    private ImageView imageView;

    public TurnOverGame(Context context) {
        super(context);
    }

    public TurnOverGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TurnOverGame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TurnOverGame(Context context, int posLayout, int negLayout, int imageRes) {
        super(context, posLayout, negLayout);
        this.imageRes = imageRes ;
        imageView.setImageResource(imageRes);
        init();
    }

    private void init() {
        this.setPadding(6, 6, 6, 6);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void toPosEnd() {
        super.toPosEnd();
        if(mInterface != null)
            mInterface.OnClick(this);
    }

    @Override
    public void toNegEnd() {
        super.toNegEnd();
    }

    @Override
    public void negShow(View negView) {
        super.negShow(negView);
    }

    @Override
    public void posShow(View posView) {
        super.posShow(posView);
        imageView = (ImageView) posView.findViewById(R.id.gamePos_img);
    }


    public int getImageRes(){
        return imageRes ;
    }


    public void setOnTOGameListener(OnTOGameListener listener){
        mInterface = listener ;
    }
    public interface OnTOGameListener{
        void OnClick(TurnOverGame v);
    }
}
