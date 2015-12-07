# TurnOverView



## Introduce
点击反转的控件

## Screenshots

![](http://7xjizl.com1.z0.glb.clouddn.com/makepointGifturnover1.0.gif)

## Usage

### TurnOverView 

负责加载正反两面的布局， 并实现点击翻转。 使用ViewStub避免多余的布局加载（因为反面不一定会出现）

### TurnOverCard

我们需要继承TurnOverView 来实现对事件的操作与监听，如：

- 正面的内容更改
- 背面的Button点击监听

继承TurnOverView时，我们通过重写父类的posShow和negShow方法，来获取我们的正反布局中的控件，用例如下：

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

[具体使用请戳我！戳我！我！](https://github.com/mBigFlower/TurnOverView/blob/master/app/src/main/java/com/bigflower/turnover/View/OverTurnCard.java)

### Layout

既然是控件，就需要在布局中调用，目前还不支持直接在java文件中添加，布局如下：

	<com.bigflower.turnover.View.OverTurnCard xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:id="@+id/item_overTurn"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    app:negLayout="@layout/turnover_neglayout"
	    app:posLayout="@layout/turnover_poslayout">

	</com.bigflower.turnover.View.OverTurnCard>

没啥特殊的，就是注意添加 negLayout和posLayout

## Next

从Gif中也能看出，我把这个控件用在了GridView中，那么问题来了。怎么做到，仅允许出现一个翻转呢？敬请期待2.0


