package app.repository;

import app.Exceptions.ResourceNotFoundException;
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
public class OffersRepositoryJpa implements OffersRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Offer> findAll() {
        TypedQuery<Offer> query =
                this.em.createQuery(
                        "select o from Offer o", Offer.class);
        return query.getResultList();
    }

    @Override
    public Offer findById(long id) {
        return this.em.find(Offer.class, id);
    }

    @Override
    public Offer save(Offer offer) {
        offer = this.em.merge(offer);
        return offer;
    }

    @Override
    public Offer delete(long id) {
        Offer offer = findById(id);
        if (offer != null){
            this.em.remove(offer);
        }
        return null;
    }
}
