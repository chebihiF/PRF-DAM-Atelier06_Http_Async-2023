package org.m2i.atelier06_http_async;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.m2i.atelier06_http_async.model.Product;

public class ProductDetails extends AppCompatActivity {

    Product product;
    TextView title_txt, department_txt, size_txt, material_txt, feature_txt, closure_style_txt, style_txt, price_txt, description_txt;
    ImageView image_val, image_val1, image_val2, image_val3, image_val4;
    RatingBar rating_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");
    }

    @Override
    protected void onStart() {
        super.onStart();
        title_txt = findViewById(R.id.title_txt);
        description_txt = findViewById(R.id.description_txt);
        department_txt = findViewById(R.id.department_txt);
        style_txt = findViewById(R.id.style_txt);
        closure_style_txt = findViewById(R.id.closure_style_txt);
        feature_txt = findViewById(R.id.features_txt);
        size_txt = findViewById(R.id.size_txt);
        material_txt = findViewById(R.id.material_txt);
        price_txt = findViewById(R.id.price_txt);
        rating_txt = findViewById(R.id.rating_val);
        image_val = findViewById(R.id.image_val);
        image_val1 = findViewById(R.id.image_v1);
        image_val2 = findViewById(R.id.image_v2);
        image_val3 = findViewById(R.id.image_v3);
        image_val4 = findViewById(R.id.image_v4);

        if(product.getTitle() != null) title_txt.setText(product.getTitle());
        if(product.getDescription() != null) description_txt.setText(description_txt.getText());
        if(product.getPrice() != null) price_txt.setText(product.getPrice());
        if(product.getAttributes().getDepartment() != null) department_txt.setText(product.getAttributes().getDepartment());
        if(product.getAttributes().getFeatures() != null) feature_txt.setText(product.getAttributes().getFeatures());
        if(product.getAttributes().getClosureStyle() != null) closure_style_txt.setText(product.getAttributes().getClosureStyle());
        if(product.getAttributes().getSize() != null) size_txt.setText(product.getAttributes().getSize());
        if(product.getAttributes().getStyle() != null) style_txt.setText(product.getAttributes().getStyle());
        if(product.getAttributes().getMaterial() != null) material_txt.setText(product.getAttributes().getMaterial());
        rating_txt.setRating((float) product.getRating());
        Picasso.get().load(product.getImages().get(0)).into(image_val);
        if(product.getImages().size()>1) Picasso.get().load(product.getImages().get(1)).into(image_val1);
        if(product.getImages().size()>2) Picasso.get().load(product.getImages().get(2)).into(image_val2);
        if(product.getImages().size()>3) Picasso.get().load(product.getImages().get(3)).into(image_val3);
        if(product.getImages().size()>4) Picasso.get().load(product.getImages().get(4)).into(image_val4);



    }
}