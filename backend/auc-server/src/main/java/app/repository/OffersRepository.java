package app.repository;

import app.models.Offer;

import java.util.List;

public interface OffersRepository <E extends Identifiable>  {
     List<E> findAll();

     E findById(long id);

     E save(E entity);
     E delete(long id );
}
