package org.example.onlineauctionsystem.Service;

import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item itemToUpdate) {
        // Check if the item exists in the database
        if (itemToUpdate.getItemid() == null || !itemRepository.existsById(itemToUpdate.getItemid())) {
            throw new RuntimeException("Item not found");
        }

        // Save the updated item
        return itemRepository.save(itemToUpdate);
    }
}
