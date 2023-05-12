package org.m2i.atelier06_http_async;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.m2i.atelier06_http_async.async.NewsTasks;

public class MainActivity extends AppCompatActivity {

    RecyclerView productsList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsList = findViewById(R.id.product_list);
        NewsTasks news = new NewsTasks(productsList, this);
        news.execute();
    }
}