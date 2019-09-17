package com.jiyun.pinglun;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_img)
    ImageView mainImg;
    @BindView(R.id.main_ctbar)
    CollapsingToolbarLayout mainCtbar;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppbar;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.main_rlv)
    RecyclerView mainRlv;
    private ArrayList<itemBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        //布局管理器
        mainRlv.setLayoutManager(new LinearLayoutManager(this));

        //空集合
        list = new ArrayList<>();

        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));
        list.add(new itemBean("123","123","12312312323132123111111111111111111111111111111231321321"));


        MyAdapter myAdapter = new MyAdapter(this,list);



        myAdapter.notifyDataSetChanged();


    }
}
