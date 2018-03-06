package com.openxu.rv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openxu.rv.adapter.CommandRecyclerAdapter;
import com.openxu.rv.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ActivityHorizontal extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        for(int i=1;i<=50;i++){
            dataList.add("item--"+i);
        }

        recyclerView = findViewById(R.id.recyclerView);
        // 设置布局管理器
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>
                (this, R.layout.item_horizontal, dataList) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item, str);
            }
            @Override
            public void onItemClick(String str, int position) {
            }
        };
        recyclerView.setAdapter(adapter);
        // 设置Item之间间隔样式
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.HORIZONTAL));

    }
}
