package com.jiyun.qqkandianview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //这是个自定义VIEW
    private MyDragGridLayout myDragGridLayout, myDisDragGridLayout;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //初始化ID
        myDragGridLayout = findViewById(R.id.drag_layout);
        myDisDragGridLayout = findViewById(R.id.dis_drag_layout);



        //上半部分可滑动
        //设置可滑动
        myDragGridLayout.setIsDragAble(true);
        mList = new ArrayList<>();

        //添加数据
        mList.add("孙尚香");
        mList.add("安琪拉");
        mList.add("兰陵王");
        mList.add("花木兰");
        mList.add("孙悟空");
        mList.add("姜子牙");
        mList.add("王昭君");
        mList.add("杨玉环");
        mList.add("程咬金");
        mList.add("超级兵");
        myDragGridLayout.setItems(mList);




        //下半部分不可滑动
        //设置不可滑动
        myDisDragGridLayout.setIsDragAble(false);
        final List<String> list = new ArrayList<>();
        //给集合添加数据
        list.add("妲己");
        list.add("亚瑟");
        list.add("典韦");
        list.add("露娜");
        list.add("凯");
        list.add("后羿");
        list.add("盘古");
        list.add("女娲");
        list.add("李白");
        list.add("苏烈");
        list.add("梦琪");
        list.add("荆轲");
        list.add("韩信");
        list.add("项羽");
        list.add("张飞");
        list.add("关羽");
        list.add("刘备");
        list.add("刘禅");
        list.add("虞姬");
        myDisDragGridLayout.setItems(list);


        ////////////////////////////////上部分点击事件//////////////////////////////////////////////////

        myDragGridLayout.setmItemClickListener(new OnItemClickListenner() {
            @Override
            public void getDragableItemView(View view) {
                if (view instanceof TextView){
                    String text = ((TextView) view).getText().toString();
                    mList.remove(text);
                    myDragGridLayout.removeView(view);
                    list.add(text);
                    myDisDragGridLayout.addItems(text);
                    Log.e("GAT","点击了上面部分");
                }
            }

            @Override
            public void getDisDragableItemView(View view) {
            }
        });




        ////////////////////////////////下部分点击事件//////////////////////////////////////////////////
        myDisDragGridLayout.setmItemClickListener(new OnItemClickListenner() {
            @Override
            public void getDragableItemView(View view) {
            }

            @Override
            public void getDisDragableItemView(View view) {
                Log.e("GAT","点击了下面部分");
                if (view instanceof TextView) {
                    String text = ((TextView) view).getText().toString();
                    list.remove(text);
                    myDisDragGridLayout.removeView(view);
                    mList.add(text);
                    myDragGridLayout.addItems(text);
                }
            }
        });
    }
}
