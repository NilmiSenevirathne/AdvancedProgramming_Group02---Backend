package org.example.onlineauctionsystem.Controller;

import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //get item details from the id
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemDetailsById(@PathVariable Long id) {
        Optional<Item> optionalItem = itemService.getItemById(id);
        return optionalItem.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //update item details
    @PutMapping("/updateitems/{id}")
    public ResponseEntity<Item> updateItemDetails(@PathVariable Long id, @RequestBody Item updatedItemDetails) {
        Optional<Item> optionalItem = itemService.getItemById(id);
        if (optionalItem.isPresent()) {
            Item itemToUpdate = optionalItem.get();
            // Update item details with the new values from updatedItemDetails
            itemToUpdate.setItemname(updatedItemDetails.getItemname());
            itemToUpdate.setDescription(updatedItemDetails.getDescription());
            itemToUpdate.setStartingPrice(updatedItemDetails.getStartingPrice());
            itemToUpdate.setBidEndTime(updatedItemDetails.getBidEndTime());
            itemToUpdate.setImageData(updatedItemDetails.getImageData());
            // Save the updated item
            Item updatedItem = itemService.updateItem(itemToUpdate);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //delete item
    @DeleteMapping("/deleteitem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        Optional<Item> optionalItem = itemService.getItemById(id);
        if (optionalItem.isPresent()) {
            itemService.deleteItem(id);
            return ResponseEntity.ok("Item deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
