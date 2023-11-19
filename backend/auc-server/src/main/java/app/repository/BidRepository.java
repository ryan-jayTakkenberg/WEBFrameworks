package app.repository;

import app.models.Bid;
import app.models.Offer;

import java.util.List;

public interface BidRepository {
    List<Bid> findAllBids();

    Bid findBidById(long id);

    Bid saveBid(Bid bid);
    Bid deleteBid(long id );
}
