package com.bigflower.turnoverview;


import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

/**
 * Created by Bigflower on 2015/12/5.
 * <p/>
 * This is a view, which overturns when we click it
 */
public class TurnOverView extends FrameLayout implements View.OnClickListener {

    private final static int ANIM_STATE_DEFAULT = -1;
    private final static int ANIM_STATE_POS = 0;
    private final static int ANIM_STATE_POS1 = 1;
    private final static int ANIM_STATE_POS2 = 2;
    private final static int ANIM_STATE_NEG = 3;
    private final static int ANIM_STATE_NEG1 = 4;
    private final static int ANIM_STATE_NEG2 = 5;

    // XML添加的内容
    private int PosLayoutRes, NegLayoutRes;
    // 动画的时长
    private int animTime = 500;

    private View mView, posView, negView;
    private ViewStub posViewStub, negViewStub;

    private boolean isNegHide = true;
    private int animateState = ANIM_STATE_DEFAULT;

    public TurnOverView(Context context) {
        super(context);
    }

    public TurnOverView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 这个属性就用之前的 ItemView的了。 省事儿。
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OverTurn, 0, 0);

        try {
            PosLayoutRes = ta.getResourceId(R.styleable.OverTurn_posLayout, -1);
            NegLayoutRes = ta.getResourceId(R.styleable.OverTurn_negLayout, -1);
            animTime = ta.getInteger(R.styleable.OverTurn_overTurn_time, 500);
        } finally {
            ta.recycle();
        }

        if (PosLayoutRes == -1) {
            throw new NullPointerException("There is no [posLayout], please add it in xml");
        } else if (NegLayoutRes == -1) {
            throw new NullPointerException("There is no [negLayout], please add it in xml");
        }

        init();
    }

    public TurnOverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TurnOverView(Context context, int posLayoutRes, int negLayoutRes) {
        super(context);
        PosLayoutRes = posLayoutRes;
        NegLayoutRes = negLayoutRes;
        init();
    }

    private void init() {
        // 初始化点击事件监听
        this.setOnClickListener(this);
        // 初始化布局
        initLayout();
        // 初始化参数
        initParam();
    }

    private void initParam() {
        animTime = animTime / 2;
    }

    private void initLayout() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_overturn, this, true);
        posViewStub = (ViewStub) mView.findViewById(R.id.OverTurn_pos);
        negViewStub = (ViewStub) mView.findViewById(R.id.OverTurn_neg);

        posViewStub.setLayoutResource(PosLayoutRes);
        negViewStub.setLayoutResource(NegLayoutRes);
        posView = posViewStub.inflate();
        posShow(posView);
    }

    public View getPosView() {
        return posView;
    }

    public View getNegView() {
        return negView;
    }


    @Override
    public void onClick(View v) {
        turnOver();
    }

    public void turnOver() {
        if (animateState < ANIM_STATE_POS1) {
            turnToNeg();
        } else if (animateState == ANIM_STATE_NEG) {
            turnToPos();
        }
    }

    private void turnToPos() {
        animateState = ANIM_STATE_NEG1;
        this.animate().setDuration(animTime).rotationYBy(-90).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animateState == ANIM_STATE_NEG1) {
                    animateState = ANIM_STATE_NEG2;
                    animate().rotationYBy(-90);
                    posViewStub.setVisibility(VISIBLE);
                    negViewStub.setVisibility(GONE);
                } else {
                    animateState = ANIM_STATE_POS;
                    toPosEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void turnToNeg() {
        animateState = ANIM_STATE_POS1;
        this.animate().setDuration(animTime).rotationY(90)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (isNegHide) {
                            isNegHide = false;
                            negView = negViewStub.inflate();
                            negShow(negView);
                        }
                        if (animateState == ANIM_STATE_POS1) {
                            animateState = ANIM_STATE_POS2;
                            posViewStub.setVisibility(GONE);
                            negViewStub.setVisibility(VISIBLE);
                            animate().rotationYBy(90);
                        } else {
                            animateState = ANIM_STATE_NEG;
                            toNegEnd();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
    }

    public void negShow(View negView) {
        // 这个函数，哈哈，当反面第一次出现，ViewStub加载，则调用这个函数，而子类继承了这个函数后，
        // 可以在此加载反面的控件
        // 详细请看 OverTurnCard
    }

    public void posShow(View posView) {

    }

    /**
     * 下面两个，是动画结束后的操作，方便子类判断是否结束
     */
    public void toPosEnd(){

    }

    public void toNegEnd(){

    }

    public void release() {
        mView = posView = negView = null;
    }

}
