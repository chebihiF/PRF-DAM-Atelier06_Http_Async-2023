package org.m2i.atelier06_http_async;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.m2i.atelier06_http_async.async.NewsTasks;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsTasks news = new NewsTasks();
        news.execute();
    }
}