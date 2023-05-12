package org.m2i.atelier06_http_async.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.m2i.atelier06_http_async.ProductDetails;
import org.m2i.atelier06_http_async.R;
import org.m2i.atelier06_http_async.model.Product;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> products;
    Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.department.setText(products.get(position).getAttributes().getDepartment());
        holder.rating.setRating((float) products.get(position).getRating());
        holder.price.setText(products.get(position).getPrice());
        String[] title_words = products.get(position).getTitle().split(" ");
        holder.title.setText(title_words[0]+" "+title_words[1]+" "+title_words[2]+" "+title_words[3]);
        Picasso.get().load(products.get(position).getImages().get(0)).into(holder.image);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("product", products.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView title, department, price ;
        ImageView image ;
        RatingBar rating ;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_txt);
            department = itemView.findViewById(R.id.department_txt);
            price = itemView.findViewById(R.id.price_txt);
            image = itemView.findViewById(R.id.image_val);
            rating = itemView.findViewById(R.id.rating_val);
        }
    }
}
