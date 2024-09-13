package com.example.exam_5dodiyavivek3_5_24.Get3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.exam_5dodiyavivek3_5_24.Post4.PostApi4;
import com.example.exam_5dodiyavivek3_5_24.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetApi3 extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter2 adapter2;
    List<Model2> Products;

    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_api3);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetApi3.this, PostApi4.class);
                startActivity(intent);
            }
        });

        Products = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter2 = new Adapter2(Products,this);
        recyclerView.setAdapter(adapter2);

        getJsonData();
    }

    private void getJsonData(){
        String URL = "https://reqres.in/api/register";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", "onResponse: " + response);
                try {
                    if (response.has("data")) { // Check if "data" key exists in response
                        JSONArray productsArray = response.getJSONArray("data"); // Assuming "data" contains an array of products
                        Log.d("TAG", "onResponse: " + productsArray);

                        for (int index = 0; index < productsArray.length(); index++){
                            JSONObject product = productsArray.getJSONObject(index);

                            Model2 v = new Model2();

                            v.setId("ID : "+product.getString("id"));
                            v.setName("Name : "+product.getString("name"));
                            v.setYear("Year : "+product.getString("year"));
                            v.setColor("Color : "+product.getString("color"));
                            v.setPantone_value("Pantone_value : "+product.getString("pantone_value"));

                            Products.add(v);
                        }
                        adapter2.notifyDataSetChanged();
                    } else {
                        Log.d("TAG", "onResponse: No 'data' key found in response");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(objectRequest);
    }


}