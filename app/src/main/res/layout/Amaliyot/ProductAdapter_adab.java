package com.example.mashinalioqitish.Amaliyot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mashinalioqitish.R;

import java.util.List;

public class ProductAdapter_adab      extends RecyclerView.Adapter<ProductAdapter_adab.ProductViewHolder>{




    private Context mCtx;

    //we are storing all the products in a list
    private List<Product_amal> productList;

    //getting the context and product list with constructor
    public ProductAdapter_adab(Context mCtx, List<Product_amal> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductAdapter_adab.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_amal, null);
        return new ProductAdapter_adab.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter_adab.ProductViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //getting the product of the specified position
        final Product_amal product = productList.get(position);

        //binding the data with the viewholder views

        holder.textViewTitle.setText(product.getTitle());


        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Pdf_amal.class);
                i.putExtra("title",productList.get(position).getTitle());
                i.putExtra("product",productList.get(position).getTitle());
                i.putExtra("link",productList.get(position).getLink());
                mCtx.startActivity(i);

            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;
        CardView cardView;
        public ProductViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_amal);// card intial
            textViewTitle = itemView.findViewById(R.id.textViewTitle_amal);
            imageView = itemView.findViewById(R.id.imageView_amal);
        }
    }
}
