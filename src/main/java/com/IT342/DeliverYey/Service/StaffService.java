package com.IT342.DeliverYey.Service;

import com.IT342.DeliverYey.Entity.MenuEntity;
import com.IT342.DeliverYey.Entity.StaffEntity;
import com.IT342.DeliverYey.Repository.StaffRepository;
import com.IT342.DeliverYey.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StudentRepository studentRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(StaffService.class.getName());

    public StaffService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public StaffEntity insertStaff(StaffEntity staff) {
        if (!isValidContactInfo(staff.getContactInfo())) {
            throw new IllegalArgumentException("Invalid contact info. It should be 11 digits starting with 09.");
        }
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        return staffRepository.save(staff);
    }

    public StaffEntity addMenu(int staffId, MenuEntity menu) {
        StaffEntity staff = new StaffEntity();
        try{
            staff = staffRepository.findById(staffId).get();
            staff.addMenu(menu);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Staff " + staffId + " does not exist");
        }

        return staffRepository.save(staff);
    }

    public List<StaffEntity> getAllStaff() {
        return staffRepository.findAll();
    }

    public StaffEntity updateStaff(StaffEntity newStaffDetails){
        StaffEntity staff = new StaffEntity();
        try{
            staff = staffRepository.findById(newStaffDetails.getStaffId()).get();

            staff.setName(newStaffDetails.getName());
            staff.setContactInfo(newStaffDetails.getContactInfo());
            staff.setStatus(newStaffDetails.getStatus());
            staff.setIdNumber(newStaffDetails.getIdNumber());
            staff.setPassword(newStaffDetails.getPassword());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Staff " + newStaffDetails.getStaffId() + " does not exist");
        }

        return staffRepository.save(staff);
    }

    public String deleteStaff(int staffId) {
        Optional<StaffEntity> optionalStaff = staffRepository.findById(staffId);

        if (optionalStaff.isPresent()) {

            staffRepository.deleteById(staffId);
            return "Staff " + staffId + " is successfully deleted!";
        } else {
            return "Staff " + staffId + " does not exist.";
        }
    }

    private boolean isValidContactInfo(String contactInfo) {
        // Check if contactInfo is 11 digits starting with 09
        return contactInfo != null && Pattern.matches("^09\\d{9}$", contactInfo);
    }
}
