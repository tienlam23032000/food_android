package com.tienlam.apporderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        final Button btn_get = findViewById(R.id.btn_getdata);
        final Button btn_getImg = findViewById(R.id.btn_getimg);
        btn_get.setOnClickListener(view -> {
            callJsonArrayRequest();
        });

        btn_getImg.setOnClickListener(view -> {
            callImage();
        });
    }
    private void callJsonArrayRequest() {
        final TextView txt_data = findViewById(R.id.txt_data);
        String url = "https://jsonkeeper.com/b/G97Q";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(VolleyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    for(int i = 0; i<response.length(); i++) {
                        JSONObject object = (JSONObject) response.get(i);
                        txt_data.append(object.getString("id") + " " +
                                " " + object.getString("name") + " " +object.getString("price") + "\n");
                    }
                } catch (Exception ex) {
                    Toast.makeText(VolleyActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }
    private void callImage() {
        final ImageView txt_img = findViewById(R.id.txt_img);
        String url = "https://images.unsplash.com/photo-1652811232177-e4ccfc75dd25?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1472";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                txt_img.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt_img.setImageResource(R.mipmap.ic_launcher);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(imageRequest);
    }
}