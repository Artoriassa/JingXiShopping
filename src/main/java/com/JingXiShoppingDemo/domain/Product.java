package com.JingXiShoppingDemo.domain;

public class Product {
    private String sku;
    private String name;
    private String category;
    private String imageUrl;
    private double price;
    private int stock;

    public Product(String sku, String name, String category, String imageUrl, double price, int stock) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
    }

    // Getters and setters

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}