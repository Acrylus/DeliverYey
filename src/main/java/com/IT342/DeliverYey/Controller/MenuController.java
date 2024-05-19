package com.IT342.DeliverYey.Controller;

import com.IT342.DeliverYey.Entity.MenuEntity;
import com.IT342.DeliverYey.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "http://localhost:5173")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/insertMenu")
    public ResponseEntity<MenuEntity> insertMenu(@RequestBody MenuEntity menu) {
        try{
            MenuEntity insert = menuService.insertMenu(menu);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllMenu")
    public List<MenuEntity> getAllMenu(){
        return menuService.getAllMenu();
    }

    @PutMapping("/updateMenu")
    public ResponseEntity<MenuEntity> updateMenu(@RequestBody MenuEntity newMenuDetails){
        try{
            MenuEntity update = menuService.updateMenu(newMenuDetails);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMenu/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable int menuId){
        try{
            menuService.deleteMenu(menuId);
            return ResponseEntity.ok("Menu Deleted!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
