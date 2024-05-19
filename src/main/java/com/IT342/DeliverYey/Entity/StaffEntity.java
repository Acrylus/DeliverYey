package com.IT342.DeliverYey.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tblstaff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    private String name;
    private String contactInfo;
    private boolean status;
    private String idNumber;
    private String password;
    private String userType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tblmenulist",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<MenuEntity> menu;

    public StaffEntity() {
    }

    public StaffEntity(int staffId, String name, String contactInfo, boolean status, String idNumber, String password, String userType, List<MenuEntity> menu) {
        this.staffId = staffId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.status = status;
        this.idNumber = idNumber;
        this.password = password;
        this.userType = userType;
        this.menu = menu;
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public List<MenuEntity> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuEntity> menu) {
        this.menu = menu;
    }

    public void addMenu (MenuEntity menu) {
        this.menu.add(menu);
    }
}
