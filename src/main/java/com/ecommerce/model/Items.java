package com.ecommerce.model;

/**
 * Model bean class to hold all the information about Items.
 * @author Harshit Sharma
 */
public class Items {

    private String id;
    private String title;
    private String description;
    private Integer price;
    private Short itemCount;
    private String imageUrl;
    private boolean suspended;
    private String soldTo;

    public Items() {

    }

    public Items(String title, String description, Integer price, Short itemCount, String imageUrl, boolean suspended, String soldTo) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
        this.imageUrl = imageUrl;
        this.suspended = suspended;
        this.soldTo = soldTo;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public Items setSuspended(boolean suspended) {
        this.suspended = suspended;
        return this;
    }

    public String getId() {
        return id;
    }

    public Items setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Items setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Items setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Items setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Short getItemCount() {
        return itemCount;
    }

    public Items setItemCount(Short itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Items setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(String soldTo) {
        this.soldTo = soldTo;
    }
}
