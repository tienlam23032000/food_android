package com.tienlam.apporderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.tienlam.apporderfood.Database.SQLExecute;
import com.tienlam.apporderfood.Models.Food;

import java.util.ArrayList;

public class OrderedActivity extends AppCompatActivity {
    ListView listView_Food_Ordered;
    OrderedAdapter adapter;
    SQLExecute db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered);

        listView_Food_Ordered = findViewById(R.id.list_food_ordered);
        adapter = new OrderedAdapter(this, R.layout.list_detail_food_ordered, OrderActivity.list_food_ordered);
        listView_Food_Ordered.setAdapter(adapter);
    }

    public void form_remove(Food food) {
        OrderActivity.list_food_ordered.remove(food);
        finish();
        startActivity(getIntent());
    }
}