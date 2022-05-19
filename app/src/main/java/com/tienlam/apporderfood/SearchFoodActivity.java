package com.tienlam.apporderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tienlam.apporderfood.Database.SQLExecute;
import com.tienlam.apporderfood.Models.Food;

import java.util.ArrayList;

public class SearchFoodActivity extends AppCompatActivity {
    FoodAdapter adapter;
    ArrayList<Food> listFood;
    ListView listFoodSearch;
    SQLExecute db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        db = new SQLExecute(this,"finance.sqlite", null, 1);
        listFood = new ArrayList<Food>();
        EditText txt_search = (EditText) findViewById(R.id.txt_search);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        Button btn_back = (Button) findViewById(R.id.btn_back);

        btn_search.setOnClickListener((view -> {
            String name = txt_search.getText().toString();
            // Call method search by name for search
            listFoodSearch = findViewById(R.id.search_food);
            adapter = new FoodAdapter(this, null, R.layout.list_detail_food_search, searchByName(name));
            listFoodSearch.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }));

        btn_back.setOnClickListener((view -> {
            Intent it = new Intent(SearchFoodActivity.this, ListFoodActivity.class);
            startActivity(it);
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
}