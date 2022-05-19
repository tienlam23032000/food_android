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

public class OrderedAdapter extends BaseAdapter {
    private final OrderedActivity context;
    private final int layout;
    private final List<Food> list_Food;

    public OrderedAdapter(OrderedActivity context, int layout, List<Food> list_Food) {
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
        OrderedAdapter.ViewHolder holder;
        if (view == null) {
            holder = new OrderedAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_Name = (TextView) view.findViewById(R.id.txt_name);
            holder.txt_Price = (TextView) view.findViewById(R.id.txt_price);
            holder.imgDel = view.findViewById(R.id.imgDel);
            view.setTag(holder);
        } else {
            holder = (OrderedAdapter.ViewHolder) view.getTag();
        }
        Food food = list_Food.get(i);
        holder.txt_Name.setText(food.getName());
        holder.txt_Price.setText(String.valueOf(food.getPrice()));
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.form_remove(food);
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView txt_Name, txt_Price;
        ImageView imgDel;
    }
}
