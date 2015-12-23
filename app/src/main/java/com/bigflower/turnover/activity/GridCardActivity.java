package com.bigflower.turnover.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.bigflower.turnover.R;
import com.bigflower.turnover.View.DividerItemDecoration;
import com.bigflower.turnover.adapter.OverTurnAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridCardActivity extends AppCompatActivity {

    private OverTurnAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private List<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_card);

        initRecyclerView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 12; i++) {
            data.add(" " + i);
        }
        mAdapter.setItems(data);
    }


    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter = new OverTurnAdapter(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL_LIST));
    }
}
