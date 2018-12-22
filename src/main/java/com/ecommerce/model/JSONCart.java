package com.ecommerce.model;

import java.util.List;

public class JSONCart {

    private double invoice;
    private List<Items> items;

    public JSONCart() {
        this.invoice = 0.0;

    }

    public JSONCart(double invoice, List<Items> items) {
        this.invoice = invoice;
        this.items = items;
    }

    public double getInvoice() {
        return invoice;
    }

    public JSONCart setInvoice(double invoice) {
        this.invoice = invoice;
        return this;
    }

    public List<Items> getItems() {
        return items;
    }

    public JSONCart setItems(List<Items> items) {
        this.items = items;
        return this;
    }

    public JSONCart calculateInvoice() {
        items.forEach(this::addPrice);
        return this;
    }

    private void addPrice(Items e) {
        this.invoice += e.getPrice();
    }
}
