package org.m2i.atelier06_http_async.async;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    List<Product> products;

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
                product.setId(product_json.getString("product_id"));
                product.setDescription(product_json.getString("product_description"));
                product.setTitle(product_json.getString("product_title"));
                product.setRating(product_json.getDouble("product_rating"));
                product.setNum_reviews(product_json.getInt("product_num_reviews"));
                JSONArray images = product_json.getJSONArray("product_photos");
                for(int j=0; j<images.length();j++)
                {
                    product.getImages().add(images.getString(j));
                }
                JSONObject attributes = product_json.getJSONObject("product_attributes");
                product.getAttributes().setDepartment(attributes.getString("Department"));
                product.getAttributes().setSize(attributes.getString("Size"));
                product.getAttributes().setMaterial(attributes.getString("Material"));
                product.getAttributes().setFeatures(attributes.getString("Features"));
                product.getAttributes().setClosureStyle(attributes.getString("Closure Style"));
                product.getAttributes().setStyle(attributes.getString("Style"));
                JSONObject offer = product_json.getJSONObject("offer");
                product.setPrice(offer.getString("price"));
                products.add(product);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

