package app.repository;

import app.models.Bid;
import app.models.Offer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
@Primary
public class BidsRepositoryJpa implements BidRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bid> findAllBids() {
        TypedQuery<Bid> query =
                this.em.createQuery(
                        "select o from Bid o", Bid.class);
        return query.getResultList();
    }

    @Override
    public Bid findBidById(long id) {
        return this.em.find(Bid.class, id);
    }

    @Override
    public Bid saveBid(Bid bid) {
        bid = this.em.merge(bid);
        return bid;
    }

    @Override
    public Bid deleteBid(long id) {
        Bid bid = findBidById(id);
        if (bid != null){
            this.em.remove(bid);
        }
        return null;
    }

}
