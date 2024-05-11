package org.example.onlineauctionsystem.Controller;

import org.example.onlineauctionsystem.Entity.Bid;
import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Service.BidService;
import org.example.onlineauctionsystem.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
@CrossOrigin(origins = "*" )
@RestController
public class BidController {

    @Autowired
    private BidService bidService;

    @Autowired
    private ItemService itemService;



    @GetMapping("/bids/{userId}")
    public ResponseEntity<List<Object[]>> getBidsWithItemDetailsByUserId(@PathVariable Long userId) {
        List<Object[]> bidsWithItemDetails = bidService.getBidsWithItemDetailsByUserId(userId);
        return ResponseEntity.ok(bidsWithItemDetails);
    }


    @PostMapping("/placeBid")
    public ResponseEntity<String> placeBid(@RequestBody Bid bid) {
        try {
            // Ensure the userId and itemId are included in the bid object
            Long userId = bid.getUser().getUserid();
            Long itemId = bid.getItem().getItemid();

            // Validate if userId and itemId are present
            if (userId == null || itemId == null) {
                return ResponseEntity.badRequest().body("User ID and Item ID are required.");
            }

            // Update current bid amount in the item table
            itemService.updateCurrentBidAmount(itemId, bid.getBidAmount());

            // Set bid time
            bid.setBidTime(LocalDateTime.now());

            // Add bid to the bid table
            bidService.placeBid(bid, userId);

            return ResponseEntity.ok("Bid placed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to place bid: " + e.getMessage());
        }
    }

    //fetch bid details
    @GetMapping("/allbids")
    public ResponseEntity<List<Object[]>> getAllBidsWithItemDetails() {
        List<Object[]> bidsWithItemDetails = bidService.getAllBidsWithItemDetails();
        return ResponseEntity.ok(bidsWithItemDetails);
    }

}