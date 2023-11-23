package app;

import app.models.Bid;
import app.models.Offer;
import app.repository.BidsRepositoryJpa;
import app.repository.OffersRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class DataLoader implements CommandLineRunner {

    private final OffersRepositoryJpa offersRepositoryJpa;
    private final BidsRepositoryJpa bidsRepositoryJpa;

    @Autowired
    public DataLoader(OffersRepositoryJpa offersRepositoryJpa, BidsRepositoryJpa bidsRepositoryJpa) {
        this.offersRepositoryJpa = offersRepositoryJpa;
        this.bidsRepositoryJpa = bidsRepositoryJpa;
    }

    @Override
    @Transactional
    public void run(String... args) {


        // Create offersr
        Offer offer1 = new Offer(0L, "Car", Offer.Status.NEW, "A new car", LocalDate.parse("2023-10-11"), 100.0);
        Offer offer2 = new Offer(0L, "Bike", Offer.Status.SOLD, "A sold Bike", LocalDate.parse("2023-11-20"), 150.00);
        Offer offer3 = new Offer(0L, "Bus", Offer.Status.FOR_SALE, "A sold Bike", LocalDate.parse("2023-11-20"), 800.00);

        // Save offers first to make them managed entities
        offersRepositoryJpa.save(offer1);
        offersRepositoryJpa.save(offer2);
        offersRepositoryJpa.save(offer3);

        // Create and add bids to each offer
        Bid bid1 = new Bid(110.0);
        Bid bid2 = new Bid(120.0);
        Bid bid3 = new Bid(160.0);
        Bid bid4 = new Bid(210.0);
        Bid bid5 = new Bid(210.0);

        // Associate bids with offers and save them
        bid1.associateOffer(offer1);
        bid4.associateOffer(offer1);
        bid5.associateOffer(offer2);
        bid2.associateOffer(offer2);
        bid3.associateOffer(offer3);

        bidsRepositoryJpa.save(bid1);
        bidsRepositoryJpa.save(bid2);
        bidsRepositoryJpa.save(bid3);
        bidsRepositoryJpa.save(bid4);
        bidsRepositoryJpa.save(bid5);


    }
}
