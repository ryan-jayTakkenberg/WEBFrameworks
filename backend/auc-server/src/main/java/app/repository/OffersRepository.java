package app.repository;

import app.models.Offer;

import java.util.List;

public interface OffersRepository {
     List<Offer> findAll();

     Offer findById(long id);

     Offer save(Offer offer);
     Offer delete(long id );
}
