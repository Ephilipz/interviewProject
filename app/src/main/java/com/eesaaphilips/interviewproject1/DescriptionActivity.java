package com.eesaaphilips.interviewproject1;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eesaaphilips.interviewproject1.database.DescriptionViewModel;
import com.eesaaphilips.interviewproject1.model.Product;

public class DescriptionActivity extends AppCompatActivity {

    public static final String PRODUCT_ID = "product id";
    private TextView name_tv, price_tv, descr_tv;
    private ImageView imageView;
    private DescriptionViewModel viewModel;
    private FloatingActionButton fab;

    /**
     * @param savedInstanceState previously saved instance bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //assign widgets from view
        name_tv = findViewById(R.id.name_tv);
        price_tv = findViewById(R.id.price_tv);
        imageView = findViewById(R.id.image);
        descr_tv = findViewById(R.id.description_tv);
        fab = findViewById(R.id.back_fab);

        //floating action button click operations
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button is clicked
                //return to MainActivity.java and end current activity
                startActivity(new Intent(DescriptionActivity.this, MainActivity.class));
                //remove current activity from stack
                finish();
            }
        });

        //the id for the product passed from the adapter class
        int id = getIntent().getExtras().getInt(PRODUCT_ID);

        //instantiates viewmodel using the DescriptionViewModel class
        viewModel = ViewModelProviders.of(this).get(DescriptionViewModel.class);

        //retrieve product using passed id
        Product product = viewModel.loadData(id);
        Log.i("PRODUCT", String.valueOf(product == null));

        //checks if product was retrieved
        if (product != null) {
            setTitle(product.getName());

            name_tv.setText(product.getName());
            price_tv.setText(String.format("$%s", String.valueOf(product.getPrice())));
            Glide.with(DescriptionActivity.this).load(product.getImageUrl()).into(imageView);
            descr_tv.setText(product.getDescription());
        } else {
            //notifies the user that the product retrieval was unsuccessful
            Toast.makeText(this, "Error retrieving product information", Toast.LENGTH_SHORT).show();
        }

    }

}
