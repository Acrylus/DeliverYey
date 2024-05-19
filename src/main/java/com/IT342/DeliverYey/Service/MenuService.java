package com.IT342.DeliverYey.Service;

import com.IT342.DeliverYey.Entity.MenuEntity;
import com.IT342.DeliverYey.Entity.StaffEntity;
import com.IT342.DeliverYey.Repository.MenuRepository;
import com.IT342.DeliverYey.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StaffRepository staffRepository;

    public MenuEntity insertMenu(MenuEntity menu) {
        return menuRepository.save(menu);
    }

    public List<MenuEntity> getAllMenu() {
        return menuRepository.findAll();
    }

    public MenuEntity updateMenu(MenuEntity newMenuDetails) {
        MenuEntity menu = new MenuEntity();

        try{
            menu = menuRepository.findById(newMenuDetails.getMenuId()).get();

            menu.setStaffId(newMenuDetails.getStaffId());
            menu.setName(newMenuDetails.getName());
            menu.setDescription(newMenuDetails.getDescription());
            menu.setRate(newMenuDetails.getRate());
            menu.setSize(newMenuDetails.getSize());
            menu.setPrice(newMenuDetails.getPrice());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Menu " + newMenuDetails.getMenuId() + " does not exist");
        }

        return menuRepository.save(menu);
    }

    public String deleteMenu(int menuId) {
        MenuEntity menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new NoSuchElementException("Menu with ID " + menuId + " not found."));

        if (menu != null) {
            StaffEntity staff = staffRepository.findById(menu.getStaffId()).orElseThrow(() -> new NoSuchElementException("Staff with ID " + menu.getStaffId() + " not found."));

            if (staff != null) {
                staff.setMenu(null);
                staffRepository.save(staff);
            }

            menuRepository.deleteById(menuId);
            return "Menu " + menuId + " is successfully deleted!";
        } else {
            return "Menu " + menuId + " does not exist.";
        }
    }

    private boolean isValidContactInfo(String contactInfo) {
        return contactInfo != null && Pattern.matches("^09\\d{9}$", contactInfo);
    }
}
