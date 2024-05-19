package com.IT342.DeliverYey.Controller;

import com.IT342.DeliverYey.Entity.MenuEntity;
import com.IT342.DeliverYey.Entity.StaffEntity;
import com.IT342.DeliverYey.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/staff")
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/insertStaff")
    private ResponseEntity<StaffEntity> insertStaff(@RequestBody StaffEntity staff) {
        try{
            StaffEntity insert = staffService.insertStaff(staff);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addMenu/{staffId}")
    public ResponseEntity<StaffEntity> addMenu(@PathVariable int staffId, @RequestBody MenuEntity menu) {
        try{
            StaffEntity addMenu = staffService.addMenu(staffId, menu);
            return new ResponseEntity<>(addMenu, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllStaff")
    public List<StaffEntity> getAllStudent() {
        return staffService.getAllStaff();
    }

    @PutMapping("/updateStaff")
    public ResponseEntity<StaffEntity> updateStaff(@RequestBody StaffEntity newStaffDetails) {
        try{
            StaffEntity update = staffService.updateStaff(newStaffDetails);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteStaff/{staffId}")
    public ResponseEntity<String> deleteStaff(@PathVariable int staffId) {
        try{
            staffService.deleteStaff(staffId);
            return ResponseEntity.ok("Staff Deleted!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
