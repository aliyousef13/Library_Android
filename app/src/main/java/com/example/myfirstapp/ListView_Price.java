package com.example.myfirstapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.PriceAdapter;
import api.AppController;
import models.Price;

public class ListView_Price extends AppCompatActivity {

    ListView lv;
    PriceAdapter adapter;
    ArrayList<Price> priceArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_price);

        lv = findViewById(R.id.lv_price);


        priceArrayList = new ArrayList<>();

        //
        priceArrayList.add(new Price(1, "OS Security", 0f, 0f, R.drawable.os));
        priceArrayList.add(new Price(2, "Web Security", 0f, 0f, R.drawable.web4));
        priceArrayList.add(new Price(3, "Information Security", 0f, 0f, R.drawable.libraryinformation));
        priceArrayList.add(new Price(4, "Network Security", 0f, 0f, R.drawable.network));
        priceArrayList.add(new Price(5, "Malware Analysis", 0f, 0f, R.drawable.malware));
        priceArrayList.add(new Price(6, "Digital Forensics", 0f, 0f, R.drawable.digital));
        priceArrayList.add(new Price(7, "Cloud Security", 0f, 0f, R.drawable.cloud));
        priceArrayList.add(new Price(8, "Ethical Hacking", 0f, 0f, R.drawable.hacking));
        priceArrayList.add(new Price(9, "Database Security", 0f, 0f, R.drawable.database));

        adapter = new PriceAdapter(ListView_Price.this, priceArrayList);
        lv.setAdapter(adapter);


        loadPriceAndRatingFromApi();
    }

    private void loadPriceAndRatingFromApi() {
        String url = AppController.link;


          JsonObjectRequest req = new JsonObjectRequest( Request.Method.GET,   url,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray products = response.getJSONArray("products");

                            int count = Math.min(products.length(), priceArrayList.size());

                            for (int i = 0; i < count; i++) {
                                JSONObject jo = products.getJSONObject(i);

                                float price  = (float) jo.optDouble("price", 0.0);
                                float rating = (float) jo.optDouble("rating", 0.0);


                                priceArrayList.get(i).setPrice(price);
                                priceArrayList.get(i).setRating(rating);
                            }

                            adapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ListView_Price.this,
                                    "API parsing error (kept manual data)",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListView_Price.this,
                                "API failed (kept manual data)",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );


        AppController.getInstance().addToRequestQueue(req, "price_api");
    }

}
