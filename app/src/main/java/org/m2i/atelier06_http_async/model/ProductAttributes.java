package org.m2i.atelier06_http_async.model;

import java.io.Serializable;

public class ProductAttributes implements Serializable {
    private String department ;
    private String size ;
    private String material;
    private String features ;
    private String closureStyle;
    private String style ;

    public ProductAttributes() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getClosureStyle() {
        return closureStyle;
    }

    public void setClosureStyle(String closureStyle) {
        this.closureStyle = closureStyle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public ProductAttributes(String department, String size, String material, String features, String closureStyle, String style) {
        this.department = department;
        this.size = size;
        this.material = material;
        this.features = features;
        this.closureStyle = closureStyle;
        this.style = style;
    }
}
