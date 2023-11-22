package app.repository;
import app.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class OffersRepositoryMock implements OffersRepository<Offer> {
    private final List<Offer> offers = new ArrayList<>();
    public static int offerIdCount = 8;

    public OffersRepositoryMock() {
        for (int i = 1; i <= 7; i++) {
            offers.add(Offer.createSampleOffer(i));
        }

    }

    @Override
    public List<Offer> findAll() {
        return offers;
    }

    @Override
    public Offer findById(long id) {
        for (Offer offer: offers){
            if(offer.getId() == id){
                return offer;
            }

        }
        return null;
    }


    @Override
    public Offer save(Offer offer) {
        if (offer.getId() == 0) {
            offer.setId(offerIdCount++);
            offers.add(offer);
        } else {
            for (int i = 0; i < offers.size(); i++) {
                if (offers.get(i).getId() == offer.getId()) {
                    offers.set(i, offer); // Replace the existing offer with the updated one
                    break;
                }
            }
        }
        return offer;
    }

    @Override
    public Offer delete(long id) {
        for (Offer offer: offers){
            if(offer.getId() == id){
                offers.remove(offer);
                break;
            }

        }
        return null;
    }
}
