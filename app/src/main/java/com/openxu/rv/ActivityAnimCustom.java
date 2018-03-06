package com.openxu.rv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.openxu.rv.adapter.CommandRecyclerAdapter;
import com.openxu.rv.adapter.ViewHolder;
import com.openxu.rv.anim.MyItemAnim;

import java.util.ArrayList;
import java.util.List;

/**
 * autour : openXu
 * date : 2018/3/5 15:32
 * className : ActivityAnimCustom
 * version : 1.0
 * description : 自定义动画效果
 */
public class ActivityAnimCustom extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;
    private RecyclerView.LayoutManager mLayoutManager;
    private CommandRecyclerAdapter adapter;
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
        adapter = new CommandRecyclerAdapter<String>
                (this, R.layout.item_anim, dataList) {
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_item, str);
            }
            //点击事件，在下方添加一个item
            @Override
            public void onItemClick(String str, int position) {
                Log.i(getClass().getSimpleName(), "添加item"+position);
                adapter.addData(position+1, "新添加的item--"+(position+1));
            }
            //长按事件，删除item
            @Override
            public boolean onItemLongClick(String data, int position) {
                adapter.removeData(position);
                return true;
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
        // 设置Item添加和移除的动画
        MyItemAnim anim = new MyItemAnim();
        //设置动画持续时间
        anim.setAddDuration(1500);
        recyclerView.setItemAnimator(anim);

    }
}
