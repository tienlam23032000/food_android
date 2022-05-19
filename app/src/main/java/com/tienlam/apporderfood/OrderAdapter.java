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

public class OrderAdapter extends BaseAdapter {
    private final OrderActivity context;
    private final int layout;
    private final List<Food> list_Food;

    public OrderAdapter(OrderActivity context, int layout, List<Food> list_Food) {
        this.context = context;
        this.layout = layout;
        this.list_Food = list_Food;
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
        OrderAdapter.ViewHolder holder;
        if (view == null) {
            holder = new OrderAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_Name = (TextView) view.findViewById(R.id.txt_name);
            holder.txt_Price = (TextView) view.findViewById(R.id.txt_price);
            holder.imgAdd = view.findViewById(R.id.imgAdd);
            view.setTag(holder);
        } else {
            holder = (OrderAdapter.ViewHolder) view.getTag();
        }
        Food food = list_Food.get(i);
        holder.txt_Name.setText(food.getName());
        holder.txt_Price.setText(String.valueOf(food.getPrice()));
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.form_choose(food.getId(), food.getName(), food.getPrice());
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView txt_Name, txt_Price;
        ImageView imgAdd;
    }
}
