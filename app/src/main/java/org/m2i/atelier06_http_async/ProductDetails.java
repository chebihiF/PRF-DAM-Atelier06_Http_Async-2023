package org.m2i.atelier06_http_async;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.m2i.atelier06_http_async.model.Product;

public class ProductDetails extends AppCompatActivity {

    TextView product_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");
        product_info = findViewById(R.id.product_info);
        product_info.setText(product.getTitle());
    }
}