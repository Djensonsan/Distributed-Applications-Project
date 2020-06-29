package dev.DTOs;

import javax.persistence.Column;
import java.util.ArrayList;

public class ItemDTO {
    private Long itemId;
    private String name;
    private String description;
    private int stockQuantity;
    private String quantityUnit;
    private float price;
    private ArrayList<String> urls;

    public ItemDTO(Long itemId, String name, String description, int stockQuantity, String quantityUnit, float price, ArrayList<String> urls) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.quantityUnit = quantityUnit;
        this.price = price;
        this.urls = urls;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void addUrl(String url) {
        this.urls.add(url);
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }
}
