package com.openxu.rv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openxu.rv.adapter.CommandRecyclerAdapter;
import com.openxu.rv.adapter.ViewHolder;
import com.openxu.rv.divider.MyGridDividerDecoration;

import java.util.ArrayList;
import java.util.List;

public class ActivityGridLayoutManager extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;
    private CommandRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        for(int i=1;i<=50;i++){
            dataList.add("item--"+i);
        }

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager mGridtManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mGridtManager);
        adapter = new CommandRecyclerAdapter<String>
                (this, R.layout.item, dataList) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item, str);
            }
            @Override
            public void onItemClick(String str, int position) {
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyGridDividerDecoration(this));

    }
}
