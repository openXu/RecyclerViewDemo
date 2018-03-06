package com.openxu.rv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openxu.rv.adapter.CommandRecyclerAdapter;
import com.openxu.rv.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        dataList.add("基本使用--竖向VERTICAL");
        dataList.add("基本使用--横向HORIZONTAL");
        dataList.add("基本使用--item间隔样式");
        dataList.add("高级使用--自定义间隔样式");
        dataList.add("基本使用--动画");
        dataList.add("高级使用--自定义动画");
        dataList.add("基本使用--网格样式GridLayoutManager");
        dataList.add("基本使用--瀑布流样式StaggeredGridLayoutManager");

        recyclerView = findViewById(R.id.recyclerView);
        // ①. 设置布局管理器
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //②. 设置adapter
        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>
                (this, R.layout.item_vertical, dataList) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item, str);
            }
            @Override
            public void onItemClick(String str, int position) {
                onClick(str, position);
            }
        };
        recyclerView.setAdapter(adapter);
        //3. 设置Item添加和移除的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //4. 设置Item之间间隔样式
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
    }

    private void onClick(String str, int position){
        switch (position){
            case 0:
                startActivity(new Intent(this, ActivityVertical.class));
                break;
            case 1:
                startActivity(new Intent(this, ActivityHorizontal.class));
                break;
            case 2:
                startActivity(new Intent(this, ActivityItemDivider.class));
                break;
            case 3:
                startActivity(new Intent(this, ActivityItemDividerCustom.class));
                break;
            case 4:
                startActivity(new Intent(this, ActivityAnim.class));
                break;
            case 5:
                startActivity(new Intent(this, ActivityAnimCustom.class));
                break;
            case 6:
                startActivity(new Intent(this, ActivityGridLayoutManager.class));
                break;
            case 7:
                startActivity(new Intent(this, ActivityStaggeredGridLayoutManager.class));
                break;
        }
    }

}
