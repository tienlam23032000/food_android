package com.tienlam.apporderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ordered = (Button) findViewById(R.id.btn_ordered);
        Button btn_order = (Button) findViewById(R.id.btn_order);
        Button btn_food = (Button) findViewById(R.id.btn_food);
        Button btn_volley = findViewById(R.id.btn_volley);

        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListFoodActivity.class);
                startActivity(it);
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(it);
            }
        });
        btn_ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, OrderedActivity.class);
                startActivity(it);
            }
        });
        btn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(it);
            }
        });
    }
}