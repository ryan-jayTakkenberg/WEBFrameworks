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
        // Create offers
        Offer offer1 = new Offer(0,"Car", Offer.Status.NEW, "A new car", LocalDate.parse("2023-10-11"), 100.0);
        Offer offer2 = new Offer(0,"Bike", Offer.Status.SOLD, "A sold Bike", LocalDate.parse("2023-11-20"), 150.00);
        Offer offer3 = new Offer(0,"Bus", Offer.Status.FOR_SALE, "A sold Bike", LocalDate.parse("2023-11-20"), 800.00);

        // Save offers first to make them managed entities
        offersRepositoryJpa.save(offer1);
        offersRepositoryJpa.save(offer2);
        offersRepositoryJpa.save(offer3);

//        // Create and add bids to each offer
//        Bid bid1 = new Bid(110.0);
//        Bid bid2 = new Bid(120.0);
//        Bid bid3 = new Bid(160.0);
//
//        // Save bids to the repository
//        bidsRepositoryJpa.saveBid(bid1);
//        bidsRepositoryJpa.saveBid(bid2);
//        bidsRepositoryJpa.saveBid(bid3);

    }
}
