package org.m2i.atelier06_http_async.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id ;
    private String title ;
    private String description ;
    private List<String> images;
    private double rating;
    private int num_reviews;
    private String price ;
    private ProductAttributes attributes;

    public Product() {
        images = new ArrayList<>();
        attributes = new ProductAttributes();
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ProductAttributes attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }
}
