package com.eesaaphilips.interviewproject1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eesaaphilips.interviewproject1.adapters.ProductsAdapter;
import com.eesaaphilips.interviewproject1.database.MainViewModel;
import com.eesaaphilips.interviewproject1.database.ProductDatabase;
import com.eesaaphilips.interviewproject1.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String URL_JSON = "https://limitless-forest-98976.herokuapp.com/";
    private RequestQueue requestQueue;
    private ProductsAdapter mAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mAdapter = new ProductsAdapter(this);
        recyclerView.setAdapter(mAdapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        parseJSON();

        viewModel.getmAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                mAdapter.UpdateList(products);
            }
        });
    }

    /**
     * Parses JSON using {@link com.android.volley.toolbox.Volley}
     * Creates new {@link com.eesaaphilips.interviewproject1.model.Product} using retrieved information
     */
    private void parseJSON() {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_JSON, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //successfully connected to host
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        int id = data.getInt("id");
                        String name = data.getString("name");
                        String description = data.getString("productDescription");
                        int price = (Integer) data.get("price");
                        JSONObject imageObject = data.getJSONObject("image");
                        String imageURL = imageObject.getString("link").replace("http", "https");
                        int width = imageObject.getInt("width");
                        int height = imageObject.getInt("height");

//                        create new Product using the information
                        Product product = new Product(id, name, description, price, imageURL, width, height);
                        viewModel.insert(product);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error connecting to host
                error.printStackTrace();
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    @Override
    protected void onDestroy() {
        ProductDatabase.destroyInstance();
        super.onDestroy();
    }
}