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
@Repository("BIDS.JPA")
@Transactional
public class BidsRepositoryJpa extends AbstractEntityRepositoryJpa<Bid> {
    @PersistenceContext
    private EntityManager em;

    public BidsRepositoryJpa() {
        super(Bid.class);
    }

    @Override
    public List<Bid> findAll() {
        TypedQuery<Bid> query =
                this.em.createQuery(
                        "select o from Bid o", Bid.class);
        return query.getResultList();
    }

    @Override
    public Bid findById(long id) {
        return this.em.find(Bid.class, id);
    }


    @Override
    public Bid save(Bid bid) {
        bid = this.em.merge(bid);
        return bid;
    }



    @Override
    public Bid delete(long id) {
        Bid bid = findById(id);
        if (bid != null){
            this.em.remove(bid);
        }
        return null;
    }

    @Override
    public List<Bid> findByQuery(String jpqlName, Object... params) {
        return null;
    }

}
