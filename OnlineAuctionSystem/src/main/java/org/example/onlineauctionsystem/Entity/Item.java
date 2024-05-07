package org.example.onlineauctionsystem.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long itemid;
    private String itemname;
    private String description;
    private BigDecimal startingPrice;
    private BigDecimal currentBid;
    private LocalDateTime bidEndTime;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;


    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(BigDecimal currentBid) {
        this.currentBid = currentBid;
    }

    public LocalDateTime getBidEndTime() {
        return bidEndTime;
    }

    public void setBidEndTime(LocalDateTime bidEndTime) {
        this.bidEndTime = bidEndTime;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
