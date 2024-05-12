package org.example.onlineauctionsystem.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.onlineauctionsystem.Entity.Bid;
import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Entity.User;
import org.example.onlineauctionsystem.Repository.BidRepository;
import org.example.onlineauctionsystem.Repository.ItemRepository;
import org.example.onlineauctionsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;



    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;




    @Transactional
    public Bid placeBid(Bid bid, Long userId) {
        // Retrieve item from database
        Item item = itemRepository.findById(bid.getItem().getItemid())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        // Retrieve user from database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Update current bid amount for the item
        double newBidAmount = bid.getBidAmount();
        double currentBidAmount = item.getCurrentBid() != null ? item.getCurrentBid().doubleValue() : 0;
        item.setCurrentBid(BigDecimal.valueOf(Math.max(newBidAmount, currentBidAmount)));
        itemRepository.save(item);

        // Set user in the bid object
        bid.setUser(user);

        // Set bid time
        bid.setBidTime(LocalDateTime.now());

        // Save the bid to the database
        return bidRepository.save(bid);
    }

    @Transactional
    public List<Object[]> getBidsWithItemDetailsByUserId(Long userId) {
        return bidRepository.findBidsWithItemDetailsByUserId(userId);
    }

    public List<Object[]> findAllBidsWithItemDetails() {
        return bidRepository.findAllBidsWithItemDetails();
    }
}



