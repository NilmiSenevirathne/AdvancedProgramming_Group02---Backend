package org.example.onlineauctionsystem.Controller;

import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Repository.ItemRepository;
import org.example.onlineauctionsystem.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    private ItemRepository itemRepository;




    @GetMapping("/getitems")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/additems")
    Item newItem(@RequestBody Item newItem){return itemRepository.save(newItem);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        return itemService.getItemById(id)
                .map(item -> new ResponseEntity<>(item,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
