package org.example.onlineauctionsystem.Controller;

import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getitems")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/additem") // Add endpoint mapping for addItem method
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item newItem = itemService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    //update current bid price
    @PutMapping("/updatebid/{id}")
    public ResponseEntity<Item> updateCurrentBid(@PathVariable Long id, @RequestParam BigDecimal currentBid) {
        Item itemToUpdate = itemService.getItemById(id).orElse(null);
        if (itemToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemToUpdate.setCurrentBid(currentBid);
        Item updatedItem = itemService.updateItem(itemToUpdate);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }



}
