package app.repository;

import app.models.Offer;

import java.util.List;

public interface OffersRepository {
     List<Offer> findAll();
}
