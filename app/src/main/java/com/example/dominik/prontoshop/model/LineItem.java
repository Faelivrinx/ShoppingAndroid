package com.example.dominik.prontoshop.model;

public class LineItem extends Product
{
    private int quantity;

    public LineItem(Product product, int quantity){
        super(product);
        this.setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumPrice()
    {
        return getSalePrice() * quantity;
    }

}
