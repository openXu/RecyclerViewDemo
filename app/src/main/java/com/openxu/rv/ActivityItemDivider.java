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

/**
 * autour : openXu
 * date : 2018/3/5 15:32
 * className : ActivityItemDivider
 * version : 1.0
 * description : RecyclerView设置item间隔样式
 */
public class ActivityItemDivider extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        for(int i=1;i<=30;i++){
            dataList.add("item--"+i);
        }

        recyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>
                (this, R.layout.item_divider, dataList) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item, str);
            }
            @Override
            public void onItemClick(String str, int position) {
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
    }
}
