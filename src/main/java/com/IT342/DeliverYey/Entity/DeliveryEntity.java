package com.IT342.DeliverYey.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbldelivery")
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "sid")
    @JsonIgnore
    private StudentEntity sid;
    private String location;
    private String paymentMethod;// Gcash or COD
    private boolean status;
    private String note;
    private String staffName;
    private String contactInfo;
    private String orderDate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "tblitems",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<MenuEntity> item = new ArrayList<>();

    public DeliveryEntity() {
    }

    public DeliveryEntity(Long deliveryId, String location, String paymentMethod, boolean status, String note, String staffName, String contactInfo, String orderDate, List<MenuEntity> item, StudentEntity sid) {
        this.deliveryId = deliveryId;
        this.location = location;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.note = note;
        this.staffName = staffName;
        this.contactInfo = contactInfo;
        this.orderDate = orderDate;
        this.item = item;
        this.sid = sid;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<MenuEntity> getItem() {
        return item;
    }

    public void setItem(List<MenuEntity> item) {
        this.item = item;
    }

    public StudentEntity getSid() {
        return sid;
    }

    public void setSid(StudentEntity sid) {
        this.sid = sid;
    }
}
