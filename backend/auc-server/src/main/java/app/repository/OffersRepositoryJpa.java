package app.repository;

import app.Exceptions.ResourceNotFoundException;
import app.models.Offer;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;



@Repository("OFFER.JPA")
@Transactional
@Primary
public class OffersRepositoryJpa extends AbstractEntityRepositoryJpa<Offer> {

    @PersistenceContext
    private EntityManager em;

    public OffersRepositoryJpa(){super(Offer.class);}

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
    @Override
    public List<Offer> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Offer> query = em.createNamedQuery(jpqlName, Offer.class);

        if (jpqlName.equals("Offer_find_by_status") && params.length > 0) {
            query.setParameter("status", Offer.Status.valueOf((String) params[0]));
        } else if (jpqlName.equals("Offer_find_by_title") && params.length > 0) {
            query.setParameter("description", "%" + String.valueOf((String) params[0]) + "%");
        }

        return query.getResultList();
    }
}
