package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.Item;

import java.math.BigDecimal;
import java.util.*;

public class ItemsByCategory {
    private String itemsC;
    private String category;

    public ItemsByCategory() {
    }

    public ItemsByCategory(String itemsC, String category) {
        this.itemsC = itemsC;
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public String getItemsC() {
        return this.itemsC;
    }

    public void setItems(String items) {
        this.itemsC = itemsC;
    }
//    private String category;
//    private String itemName;
//    private BigDecimal itemPrice;
//    private BigDecimal sumPricebyCatgeory;
//
//    public ItemsByCategory() {
//    }
//
//    public ItemsByCategory(String category, String itemName, BigDecimal itemPrice) {
//        this.category = category;
//        this.itemName = itemName;
//        this.itemPrice = itemPrice;
//    }
//
//    public ItemsByCategory(String itemName, BigDecimal itemPrice) {
//        this.itemName = itemName;
//        this.itemPrice = itemPrice;
//    }
//
//    public ItemsByCategory(String itemName, BigDecimal itemPrice, BigDecimal sumPricebyCatgeory) {
//        this.itemName = itemName;
//        this.itemPrice = itemPrice;
//        this.sumPricebyCatgeory = sumPricebyCatgeory;
//    }
//
//    public ItemsByCategory(String category, String itemName, BigDecimal itemPrice, BigDecimal sumPricebyCatgeory) {
//        this.category = category;
//        this.itemName = itemName;
//        this.itemPrice = itemPrice;
//        this.sumPricebyCatgeory = sumPricebyCatgeory;
//    }
//
//    public BigDecimal getSumPricebyCatgeory() {
//        return this.sumPricebyCatgeory;
//    }
//
//    public void setSumPricebyCatgeory(BigDecimal sumPricebyCatgeory) {
//        this.sumPricebyCatgeory = sumPricebyCatgeory;
//    }
//
//    public String getCategory() {
//        return this.category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getItemName() {
//        return this.itemName;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public BigDecimal getItemPrice() {
//        return this.itemPrice;
//    }
//
//    public void setItemPrice(BigDecimal itemPrice) {
//        this.itemPrice = itemPrice;
//    }
}
