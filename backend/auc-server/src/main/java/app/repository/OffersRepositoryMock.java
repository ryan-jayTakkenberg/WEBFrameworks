package app.repository;
import app.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class OffersRepositoryMock implements OffersRepository {
    private final List<Offer> offers = new ArrayList<>();

    public OffersRepositoryMock() {
        for (int i = 1; i <= 7; i++) {
            offers.add(Offer.createSampleOffer( i));
        }
    }

    @Override
    public List<Offer> findAll() {
        return offers;
    }
}
