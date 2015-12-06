package com.bigflower.turnover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.bigflower.turnover.R;
import com.bigflower.turnover.View.OverTurnCard;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Bigflower on 2015/11/07.
* <p/>
* 提取通讯录信息后，显示的列表的 adapter
*/
public class OverTurnAdapter extends RecyclerView.Adapter<OverTurnAdapter.OverTurnHolder> {


   private List<String> mDatas = new ArrayList<>();
   private int[] imgSize = new int[2];

   private Context context;

   public OverTurnAdapter(Context context) {
       this.context = context;
//        imgSize = Util.getScreenSize(context);
//        imgSize[0] = (imgSize[0] - DP20PX * 3) / 2;
   }

   @Override
   public OverTurnHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       final View view = LayoutInflater.from(context).inflate(R.layout.item_overturn, parent, false);
       final OverTurnHolder overTurnHolder = new OverTurnHolder(view);

       return overTurnHolder;
   }

   @Override
   public void onBindViewHolder(final OverTurnHolder holder, final int position) {
       holder.overTurnView.setmTextView(mDatas.get(position));
   }

   @Override
   public int getItemCount() {
       return mDatas.size();
   }

   private void setImageSize(ImageView image) {
       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(imgSize[0], imgSize[0] * 3 / 4);
       image.setLayoutParams(lp);
   }

   /**
    * 这里使用了RecyclerView的特性，并不是以往的加入数据后整体刷新
    *
    * @param text2 加入的图片
    */
   public void addItem(String text2) {
       if (text2 == null)
           return;
       int position = mDatas.size();
       mDatas.add(position, text2);
       notifyItemInserted(position);
   }

   /**
    * 删除的图片的位置
    *
    * @param position
    */
   public void removeItem(int position) {
       mDatas.remove(position);
       notifyItemRemoved(position);
   }

   public void setItems(List<String> strings) {
       this.mDatas.clear();
       this.mDatas.addAll(strings);
       notifyDataSetChanged();
   }

   public List<String> getItems() {
       return mDatas;
   }

   public static class OverTurnHolder extends RecyclerView.ViewHolder {

       public OverTurnCard overTurnView;

       public OverTurnHolder(View view) {
           super(view);
           overTurnView = (OverTurnCard) view.findViewById(R.id.item_overTurn);
       }

   }

}
