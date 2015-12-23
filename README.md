# TurnOverView


## Introduce
点击反转的控件

## Screenshots

![](http://7xjizl.com1.z0.glb.clouddn.com/turnover3.gif)

## Base
### TurnOverView 

负责加载正反两面的布局， 并实现点击翻转。 使用ViewStub避免多余的布局加载（因为反面不一定会出现）
你只需要(1)继承这个类，(2)添加你正反两个布局即可。因为布局可以自定义，所以逻辑要你自己在新类里写哈。

## Usage
### 1. TurnOverCard 

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

既然是控件，就需要在布局中调用，布局如下：

	<com.bigflower.turnover.View.OverTurnCard xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    android:id="@+id/item_overTurn"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    app:negLayout="@layout/turnover_neglayout"
	    app:posLayout="@layout/turnover_poslayout">

	</com.bigflower.turnover.View.OverTurnCard>

注意添加 negLayout和posLayout
（ps:目前还不支持直接在java文件中添加）

## 2. GridView+TurnOverView

使用 TurnOverViewOnly 并在 adapter里稍加处理，可实现图中的，仅仅存在一个卡片可翻转（点击屏幕其他位置后，已反转的收回）

### Layout

 布局不变，同上。

### TurnOverCardTri

详情如图GridActivity，实现三选一的反转处理。代码就不在这里贴了。[具体使用请戳我！戳我！我！](https://github.com/mBigFlower/TurnOverView/blob/master/app/src/main/java/com/bigflower/turnover/View/TurnOverCardTri.java)



