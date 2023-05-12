package org.m2i.atelier06_http_async.async;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.m2i.atelier06_http_async.adapters.ProductAdapter;
import org.m2i.atelier06_http_async.model.Product;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsTasks extends AsyncTask<String,Integer,String> {
    // URL : https://rapidapi.com/letscrape-6bRBa3QguO5/api/real-time-product-search/

    RecyclerView productsList;
    Context context;
    List<Product> products;

    public NewsTasks(RecyclerView productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder response = new StringBuilder();
        try {
            URL requestUrl = new URL("https://real-time-product-search.p.rapidapi.com/search?q=Nike%20shoes&country=us&language=en"); // init connection
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection(); // open connection
            conn.setConnectTimeout(15000); // set connection timeout
            conn.setReadTimeout(10000); // set read timeout
            conn.setRequestMethod("GET"); // set request method
            conn.setDoInput(true); // set input status
            conn.setRequestProperty("X-RapidAPI-Key","549cce3e1dmsh0a2bf8002ca43d4p126286jsn5c6264ca98a0");
            conn.setRequestProperty("X-RapidAPI-Host","real-time-product-search.p.rapidapi.com");
            conn.connect(); // connect to the API
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){ // check if the connection is ok
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // read String from API
                String line ;
                while ((line = br.readLine())!=null){
                    response.append(line); // append to String
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response.toString();
    }

    @Override
    protected void onPostExecute(String response) {
        products = new ArrayList<>();
        try {
            JSONObject resp = new JSONObject(response);
            JSONArray data = resp.getJSONArray("data");
            for(int i=0; i<data.length(); i++){
                JSONObject product_json = data.getJSONObject(i);
                Product product = new Product();
                if(product_json.has("product_id")) product.setId(product_json.getString("product_id"));
                if(product_json.has("product_description")) product.setDescription(product_json.getString("product_description"));
                if(product_json.has("product_title")) product.setTitle(product_json.getString("product_title"));
                if(product_json.has("product_rating") && !product_json.isNull("product_rating")) product.setRating(product_json.getDouble("product_rating"));
                if(product_json.has("product_num_reviews") && !product_json.isNull("product_num_reviews")) product.setNum_reviews(product_json.getInt("product_num_reviews"));
                JSONArray images = product_json.getJSONArray("product_photos");
                for(int j=0; j<images.length();j++)
                {
                    product.getImages().add(images.getString(j));
                }
                JSONObject attributes = product_json.getJSONObject("product_attributes");
                if(attributes.has("Department")) product.getAttributes().setDepartment(attributes.getString("Department"));
                if(attributes.has("Size")) product.getAttributes().setSize(attributes.getString("Size"));
                if(attributes.has("Material")) product.getAttributes().setMaterial(attributes.getString("Material"));
                if(attributes.has("Features")) product.getAttributes().setFeatures(attributes.getString("Features"));
                if(attributes.has("Closure Style")) product.getAttributes().setClosureStyle(attributes.getString("Closure Style"));
                if(attributes.has("Style")) product.getAttributes().setStyle(attributes.getString("Style"));
                JSONObject offer = product_json.getJSONObject("offer");
                product.setPrice(offer.getString("price"));
                products.add(product);
            }

            productsList.setLayoutManager(new LinearLayoutManager(context));
            ProductAdapter adapter = new ProductAdapter(products);
            productsList.setAdapter(adapter);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

