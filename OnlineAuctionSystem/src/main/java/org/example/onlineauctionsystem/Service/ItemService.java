package org.example.onlineauctionsystem.Service;

import jakarta.transaction.Transactional;
import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
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

    @Transactional
    public void updateCurrentBidAmount(Long itemId, double bidAmount) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setCurrentBid(BigDecimal.valueOf(bidAmount));
        itemRepository.save(item);
    }
}
