package com.example.foodhub.Common;

public class ItemReference {

    private Item item;
    private int quantity;
    private String notes;

    public ItemReference(Item item, int quantity, String notes) {
        this.item = item;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }

}
