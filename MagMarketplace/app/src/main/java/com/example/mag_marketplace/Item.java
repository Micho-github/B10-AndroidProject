package com.example.mag_marketplace;
public class Item {
    private String ownerName;
    private String itemName;
    private String description;
    private String category;
    private String subcategory;
    private double price;
    private String city;
    private int ImageResourceId;

    // Constructor
    public Item(String ownerName, String itemName, String description, String category, String subcategory, double price, String city, int ImageResourceId) {
        this.ownerName = ownerName;
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.price = price;
        this.city = city;
        this.ImageResourceId = ImageResourceId;
    }
    public Item(){};

    // Getters and setters

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setItemDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int  getImageResourceId() {
        return ImageResourceId;
    }

    public void setImageResourceId(int ImageResourceId) {
        this.ImageResourceId = ImageResourceId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ownerName='" + ownerName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", price=" + price +
                ", city='" + city + '\'' +
                ", imagePath='" + ImageResourceId + '\'' +
                '}';
    }

    // You can add other methods as needed
}
