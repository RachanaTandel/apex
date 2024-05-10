package com.apex.model;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
	private int orderID;
	private String itemName;
	private Date orderDate;
	private int quantity;
	
    public Order(int orderID2, String itemName2, Date orderDate2, int quantity2) {
		orderID = orderID2;
		itemName = itemName2;
		orderDate = orderDate2;
		quantity = quantity2;
	}

	public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	

}
