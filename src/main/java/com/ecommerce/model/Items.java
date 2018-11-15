package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "items")
public class Items {

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("price")
    private Integer price;

    @Field("item_count")
    private Short itemCount;

    @Field("image_url")
    private String imageUrl;

    public Items() {

    }

    public Items(String title, String description, Integer price, Short itemCount, String imageUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
        this.imageUrl = imageUrl;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Short getItemCount() {
        return itemCount;
    }

    public void setItemCount(Short itemCount) {
        this.itemCount = itemCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
