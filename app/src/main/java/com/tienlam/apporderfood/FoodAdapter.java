package com.tienlam.apporderfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tienlam.apporderfood.Models.Food;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private final ListFoodActivity list;
    private final SearchFoodActivity search;
    private final int layout;
    private final List<Food> list_Food;
    public FoodAdapter(SearchFoodActivity search, ListFoodActivity list, int layout, List<Food> list_Food ) {
        this.list = list;
        this.layout = layout;
        this.list_Food = list_Food;
        this.search = search;
    }
    @Override
    public int getCount() {
        return this.list_Food.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            if (search == null) {
                LayoutInflater inflater = (LayoutInflater) list.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);
                holder.txt_Name = (TextView) view.findViewById(R.id.txt_name);
                holder.txt_Price = (TextView) view.findViewById(R.id.txt_price);
                holder.imgEdit = view.findViewById(R.id.imgEdit);
                holder.imgDelete = view.findViewById(R.id.imgDelete);
                view.setTag(holder);
            } else {
                LayoutInflater inflater = (LayoutInflater) search.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(layout, null);
                holder.txt_Name = (TextView) view.findViewById(R.id.txt_name);
                holder.txt_Price = (TextView) view.findViewById(R.id.txt_price);
                holder.imgEdit = view.findViewById(R.id.imgEdit);
                holder.imgDelete = view.findViewById(R.id.imgDelete);
                view.setTag(holder);
            }
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // thiet lap gia tri cho item
        Food food = list_Food.get(i);

        //  lay ve thong tin cua ban ghi
        int id = food.getId();
        String name = food.getName();
        int price = food.getPrice();
        // Day du Lieu
        holder.txt_Name.setText(name);
        holder.txt_Price.setText(String.valueOf(price));
        //Execute Btn Edit in List Food
        if(list != null){
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.form_edit(id, name, price);
                }
            });
            //Execute Btn Delete in List Food
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.form_del(id);
                }
            });
        }
        return view;
    }

    private class ViewHolder {
        TextView txt_Name, txt_Price;
        ImageView imgEdit, imgDelete;
    }


}
