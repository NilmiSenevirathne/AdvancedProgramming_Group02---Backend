package org.example.onlineauctionsystem.Controller;

import org.example.onlineauctionsystem.Entity.Bid;
import org.example.onlineauctionsystem.Entity.Item;
import org.example.onlineauctionsystem.Entity.WinnerUsers;
import org.example.onlineauctionsystem.Service.BidService;
import org.example.onlineauctionsystem.Service.WinnerUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" )
@RestController
@RequestMapping
public class WinnerUsersController {

     @Autowired
    private WinnerUsersService winnerUsersService;

     @Autowired
    private BidService bidService;


    @PostMapping("/select")
    public void selectWinners() {
        List<WinnerUsers> winners = new ArrayList<>();
        List<Object[]> bids = bidService.findAllBidsWithItemDetails();

        // Implement logic to select winners based on highest bid amount for each item
        Map<Long, Double> highestBids = new HashMap<>();
        for (Object[] bid : bids) {
            Long itemId = ((Item) bid[1]).getItemid();
            Double bidAmount = ((Bid) bid[0]).getBidAmount();
            if (!highestBids.containsKey(itemId) || bidAmount > highestBids.get(itemId)) {
                highestBids.put(itemId, bidAmount);
            }
        }

        for (Object[] bid : bids) {
            Long itemId = ((Item) bid[1]).getItemid();
            Long userId = ((Bid) bid[0]).getUser().getUserid();
            Double bidAmount = ((Bid) bid[0]).getBidAmount();
            if (bidAmount.equals(highestBids.get(itemId))) {
                WinnerUsers winner = new WinnerUsers();
                winner.setItemId(itemId);
                winner.setUserId(userId);
                winner.setBidAmount(bidAmount);
                winners.add(winner);
            }
        }

        // Save winners in WinnerUsers table
        winners.forEach(winnerUsersService::saveWinnerUser);

        System.out.println("Winners: " + winners);
    }
}


