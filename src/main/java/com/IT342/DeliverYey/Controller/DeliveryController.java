package com.IT342.DeliverYey.Controller;

import com.IT342.DeliverYey.Entity.DeliveryEntity;
import com.IT342.DeliverYey.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = "http://localhost:5173")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/insertDelivery")
    public ResponseEntity<DeliveryEntity> insertDelivery(@RequestBody DeliveryEntity delivery) {
        try{
            DeliveryEntity insert = deliveryService.insertDelivery(delivery);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllDelivery")
    public List<DeliveryEntity> getAllDelivery(){
        return deliveryService.getAllDelivery();
    }

    @PutMapping("/updateDelivery")
    public ResponseEntity<DeliveryEntity> updateDelivery(@RequestBody DeliveryEntity newDeliveryDetails){
        try{
            DeliveryEntity update= deliveryService.updateDelivery(newDeliveryDetails);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteDelivery/{deliveryId}")
    public ResponseEntity<String> deleteDelivery(@PathVariable Long deliveryId){
        try{
            deliveryService.deleteDelivery(deliveryId);
            return ResponseEntity.ok("Order Delivery Deleted!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
