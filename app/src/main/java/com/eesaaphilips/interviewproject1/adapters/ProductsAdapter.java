package com.eesaaphilips.interviewproject1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eesaaphilips.interviewproject1.DescriptionActivity;
import com.eesaaphilips.interviewproject1.R;
import com.eesaaphilips.interviewproject1.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.customViewHolder> {
    RequestOptions options;
    private List<Product> mProducts = Collections.emptyList();
    private Context mContext;

    public ProductsAdapter(Context context) {
        mContext = context;
        options = new RequestOptions()
                .centerInside();
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_row, viewGroup, false);
        return new customViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final customViewHolder holder, final int i) {
        if (mProducts != null) {
            holder.name_tv.setText(mProducts.get(i).getName());
            holder.price_tv.setText("$" + Integer.toString(mProducts.get(i).getPrice()));
            Glide.with(mContext).load(mProducts.get(i).getImageUrl()).apply(options).into(holder.imageThumbnail);
            final Product product = mProducts.get(i);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startDetailActivity(product);
                }
            });
        } else {
            holder.name_tv.setText("Error loading object");
        }
    }

    private void startDetailActivity(Product product) {
        Intent intent = new Intent(mContext, DescriptionActivity.class);
        intent.putExtra(DescriptionActivity.PRODUCT_ID, product.getId());
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        return 0;
    }

    public void UpdateList(List<Product> newList) {
        mProducts = newList;
        notifyDataSetChanged();
    }

    public class customViewHolder extends RecyclerView.ViewHolder {
        public TextView name_tv, price_tv;
        public ImageView imageThumbnail;

        public customViewHolder(View view) {
            super(view);
            name_tv = view.findViewById(R.id.name_tv);
            price_tv = view.findViewById(R.id.price_tv);
            imageThumbnail = view.findViewById(R.id.image_thumbnail);
        }
    }
}