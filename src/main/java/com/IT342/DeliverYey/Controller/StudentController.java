package com.IT342.DeliverYey.Controller;

import com.IT342.DeliverYey.Entity.StudentEntity;
import com.IT342.DeliverYey.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/student")
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insertStudent")
    public ResponseEntity<StudentEntity> insertStudent(@RequestBody StudentEntity student) {
        try{
            StudentEntity insert = studentService.insertStudent(student);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllStudent")
    public List<StudentEntity> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity newStudentDetails) {
        try{
            StudentEntity update = studentService.updateStudent(newStudentDetails);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteStudent/{sid}")
    public ResponseEntity<String> deleteStudent(@PathVariable int sid) {
        try{
            studentService.deleteStudent(sid);
            return ResponseEntity.ok("Student Deleted!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
