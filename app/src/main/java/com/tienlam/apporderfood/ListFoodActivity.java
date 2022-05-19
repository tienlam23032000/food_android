package com.tienlam.apporderfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tienlam.apporderfood.Database.SQLExecute;
import com.tienlam.apporderfood.Models.Food;

import java.util.ArrayList;

public class ListFoodActivity extends AppCompatActivity {
    ListView listView_Food;
    ArrayList<Food> list_food;
    FoodAdapter adapter;
    SQLExecute db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        createDatabase();
        listView_Food = findViewById(R.id.list_food);
        adapter = new FoodAdapter(null,this, R.layout.list_detail_food, get_Foods());
        listView_Food.setAdapter(adapter);
    }

    private void createDatabase() {
        //tao databse co ten la finance voi version = 1
        db = new SQLExecute(this,"finance.sqlite", null, 1);
        //tao bang
        db.executeSQL("CREATE TABLE IF NOT EXISTS food(id INTEGER primary key autoincrement, name varchar(200), price int)");
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
    private void form_add() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_add_food);
        EditText name = dialog.findViewById(R.id.txt_name);
        EditText price = dialog.findViewById(R.id.txt_price);
        Button btn_add = dialog.findViewById(R.id.btn_add);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        //
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate
                if (name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Can you enter name food!", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                    return;
                }
                if (price.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Can you enter price food!", Toast.LENGTH_SHORT).show();
                    price.requestFocus();
                    return;
                }
                //Execute
                db.executeSQL("insert into food(name, price) values('" + name.getText().toString() + "', " + Integer.parseInt(price.getText().toString()) + ")");
                Toast.makeText(getApplicationContext(), "Add Done!", Toast.LENGTH_SHORT).show();

                //dong hop thoai
                dialog.dismiss();
                //Refresh list
                finish();
                startActivity(getIntent());
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void form_edit(int id, String name, int price) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_edit_food);
        EditText txt_name = dialog.findViewById(R.id.txt_name);
        EditText txt_price = dialog.findViewById(R.id.txt_price);
        Button btn_edit = dialog.findViewById(R.id.btn_edit);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        //Get Name and Price
        txt_name.setText(name);
        txt_price.setText(String.valueOf(price));
        //
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate
                if (txt_name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Can you enter name food!", Toast.LENGTH_SHORT).show();
                    txt_name.requestFocus();
                    return;
                }
                if (txt_price.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Can you enter price food!", Toast.LENGTH_SHORT).show();
                    txt_price.requestFocus();
                    return;
                }

                //Execute
                db.executeSQL("UPDATE food SET name='"+txt_name.getText().toString()+"', price="+Integer.parseInt(txt_price.getText().toString())+" WHERE id = "+id+"");
                Toast.makeText(getApplicationContext(), "Edit Done!", Toast.LENGTH_SHORT).show();
                //Close
                dialog.dismiss();
                //Refresh list
                finish();
                startActivity(getIntent());
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void form_del(int id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Alert");
        dialog.setMessage("Can you sure ?");

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.executeSQL("DELETE FROM food WHERE id = "+id+"");
                Toast.makeText(getApplicationContext(), "Delete Done!", Toast.LENGTH_SHORT).show();
                //Refresh list
                finish();
                startActivity(getIntent());
            }
        });

        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_food, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.btn_addFood){
            form_add();
        } else if (item.getItemId() == R.id.btn_search) {
            Intent it = new Intent(ListFoodActivity.this, SearchFoodActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }



}