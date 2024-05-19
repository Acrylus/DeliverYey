package com.IT342.DeliverYey.Service;

import com.IT342.DeliverYey.Entity.DeliveryEntity;
import com.IT342.DeliverYey.Entity.MenuEntity;
import com.IT342.DeliverYey.Entity.StaffEntity;
import com.IT342.DeliverYey.Entity.StudentEntity;
import com.IT342.DeliverYey.Repository.DeliveryRepository;
import com.IT342.DeliverYey.Repository.MenuRepository;
import com.IT342.DeliverYey.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private MenuRepository menuRepository;

    public DeliveryEntity insertDelivery(DeliveryEntity delivery) {
        List<MenuEntity> managedItems = new ArrayList<>();
        for(MenuEntity item : delivery.getItem()){
            MenuEntity managedItem = menuRepository.findById(item.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("Item with menuId " + item.getMenuId() + " does not exist in the menu."));
            managedItems.add(managedItem);
        }

        delivery.setItem(managedItems);

        if (delivery.getItem().isEmpty()){
            throw new NullPointerException("Items cannot be null!");
        } else if(delivery.getSid() == null){
            throw new NullPointerException("Student cannot be null!");
        }

        return deliveryRepository.save(delivery);
    }

    public List<DeliveryEntity> getAllDelivery() {
        return deliveryRepository.findAll();
    }

    public DeliveryEntity updateDelivery(DeliveryEntity newDeliveryDetails) {
        DeliveryEntity edit = new DeliveryEntity();

        List<MenuEntity> managedItems = new ArrayList<>();
        for(MenuEntity item : newDeliveryDetails.getItem()){
            MenuEntity managedItem = menuRepository.findById(item.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("Item with menuId " + item.getMenuId() + " does not exist in the menu."));
            managedItems.add(managedItem);
        }

        if (newDeliveryDetails.getItem().isEmpty()){
            throw new NullPointerException("Items cannot be null!");
        }

        try {
            edit = deliveryRepository.findById(newDeliveryDetails.getDeliveryId()).get();
            edit.setContactInfo(newDeliveryDetails.getContactInfo());
            edit.setItem(newDeliveryDetails.getItem());
            edit.setNote(newDeliveryDetails.getNote());
            edit.setOrderDate(newDeliveryDetails.getOrderDate());
            edit.setPaymentMethod(newDeliveryDetails.getPaymentMethod());
            edit.setStaffName(newDeliveryDetails.getStaffName());
            edit.setStatus(newDeliveryDetails.getStatus());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Delivery " + newDeliveryDetails.getDeliveryId() + " does not exist");
        }

        return deliveryRepository.save(edit);
    }

    public String deleteDelivery(Long deliveryId) {
        if (deliveryRepository.existsById(deliveryId)) {
            deliveryRepository.deleteById(deliveryId);
            return "Delivery with ID " + deliveryId + " has been deleted.";
        } else {
            throw new NoSuchElementException("Delivery with ID " + deliveryId + " not found.");
        }
    }
}
