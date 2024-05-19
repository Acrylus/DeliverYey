package com.IT342.DeliverYey.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblstudent")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String idNumber;
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private String contactInfo;
    private String password;
    private String userType;
    private boolean isDeleted;

    @OneToOne(mappedBy = "sid", cascade = CascadeType.ALL)
    private DeliveryEntity order;

    public StudentEntity() {
    }

    public StudentEntity(DeliveryEntity order, boolean isDeleted, String userType, String password, String contactInfo, String email, String gender, String lastname, String firstname, String idNumber, int sid) {
        this.order = order;
        this.isDeleted = isDeleted;
        this.userType = userType;
        this.password = password;
        this.contactInfo = contactInfo;
        this.email = email;
        this.gender = gender;
        this.lastname = lastname;
        this.firstname = firstname;
        this.idNumber = idNumber;
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public DeliveryEntity getOrder() {
        return order;
    }

    public void setOrder(DeliveryEntity order) {
        this.order = order;
    }
}
