package com.tienlam.apporderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tienlam.apporderfood.Database.SQLExecute;
import com.tienlam.apporderfood.Models.Food;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ListView listView_Food_Order;
    OrderAdapter adapter;
    SQLExecute db;
    // tao ra mot array moi chua du lieu cua array food
    public static ArrayList<Food> list_food_ordered = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        db = new SQLExecute(this,"finance.sqlite", null, 1);
        listView_Food_Order = findViewById(R.id.list_food_order);
        adapter = new OrderAdapter(this, R.layout.list_detail_food_order, get_Foods());
        listView_Food_Order.setAdapter(adapter);

        EditText txt_search = (EditText) findViewById(R.id.txt_search);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_search.setOnClickListener((view -> {
            String name = txt_search.getText().toString();
            listView_Food_Order = findViewById(R.id.list_food_order);
            adapter = new OrderAdapter(this, R.layout.list_detail_food_order, searchByName(name));
            listView_Food_Order.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }));

        btn_back.setOnClickListener((view -> {
            listView_Food_Order = findViewById(R.id.list_food_order);
            adapter = new OrderAdapter(this, R.layout.list_detail_food_order, get_Foods());
            listView_Food_Order.setAdapter(adapter);
        }));
    }
    //Get data by id
    public ArrayList<Food> searchByName(String name){
        Cursor rs = db.searchData(name);
        ArrayList<Food> foods = new ArrayList<>();
        if (rs != null)
            rs.moveToFirst();
        for (int i=0; i<rs.getCount(); i++){
            rs.moveToPosition(i);
            foods.add(new Food(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getInt(2)
            ));
        }
        rs.close();
        return foods;
    }

    public ArrayList<Food> get_Foods(){
        Cursor rs = db.retrieveData("SELECT * FROM food");
        ArrayList<Food> foods = new ArrayList<>();
        // moving our cursor to first position.
        if (rs.moveToFirst()) {
            do {
                foods.add(new Food(
                        rs.getInt(0),
                        rs.getString(1),
                        rs.getInt(2)
                ));
            } while (rs.moveToNext());
        }
        rs.close();
        return foods;
    }

    public void form_choose(int id, String name, int price) {
        Intent it = new Intent(OrderActivity.this, OrderedActivity.class);
        list_food_ordered.add(new Food(id, name, price));
        startActivity(it);
    }
}