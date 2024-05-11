package org.example.onlineauctionsystem.Repository;

import org.example.onlineauctionsystem.Entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository  extends JpaRepository<Bid,Long> {

    @Query("SELECT b, i FROM Bid b JOIN b.item i WHERE b.user.Userid = :userId")
    List<Object[]> findBidsWithItemDetailsByUserId(@Param("userId") Long userId);
}
