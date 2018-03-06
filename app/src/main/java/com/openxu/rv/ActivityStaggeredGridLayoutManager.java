package com.openxu.rv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.openxu.rv.adapter.CommandRecyclerStaggeredAdapter;
import com.openxu.rv.adapter.ViewHolder;
import com.openxu.rv.divider.MyGridDividerDecoration;

import java.util.ArrayList;
import java.util.List;

public class ActivityStaggeredGridLayoutManager extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;
    private List<Integer> itemLayouts;
    private CommandRecyclerStaggeredAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        for(int i=1;i<=50;i++){
            dataList.add("item--"+i);
        }
        itemLayouts = new ArrayList<>();
        itemLayouts.add(R.layout.item_staggered_1);
        itemLayouts.add(R.layout.item_staggered_2);

        recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager
                (2, OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new CommandRecyclerStaggeredAdapter<String>
                (this, itemLayouts, dataList) {
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
