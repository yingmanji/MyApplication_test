package com.example.a0_0.myapplication_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_ActList extends Activity{
    String[]items={"北京市","上海市","广州市","深圳市","西安市"};
    ArrayList<String> arrayList=new ArrayList(30);
    ListView llistView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__act_list);
        reset();
        llistView=(ListView)findViewById(R.id.lv);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        llistView.setAdapter(arrayAdapter);
    }
    protected void reset()
    {
        arrayList.clear();
        for (String s :items
                ) {
            arrayList.add(s);}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                arrayList.add(items[0]);
//                llistView.setAdapter(arrayAdapter);
                break;
            case R.id.reset:
                reset();
//                llistView.setAdapter(arrayAdapter);
                break;
            case R.id.delete:
                if(arrayList.size()>5) {
                    arrayList.remove(arrayList.size() - 1);
                }
                break;
            case R.id.about:
                Toast.makeText(this,"1513335 谢恒亮",Toast.LENGTH_SHORT).show();
                break;
        }
        llistView.setAdapter(arrayAdapter);
        return super.onOptionsItemSelected(item);
    }
}
