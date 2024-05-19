package com.IT342.DeliverYey.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tblmenu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "staff_id")
    private int staffId;
    private String name;
    private String description;
    private float rate;

    private String size;
    private int price;

    public MenuEntity() {
    }

    public MenuEntity(int menuId, int staffId, String name, String description, float rate, String size, int price) {
        this.menuId = menuId;
        this.staffId = staffId;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.size = size;
        this.price = price;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
