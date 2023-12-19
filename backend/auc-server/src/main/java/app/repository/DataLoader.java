package app.repository;

import app.models.Bid;
import app.models.Offer;
import app.models.User;
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

    private final UserRepositoryJpa userRepositoryJpa;

    @Autowired
    public DataLoader(OffersRepositoryJpa offersRepositoryJpa, BidsRepositoryJpa bidsRepositoryJpa, UserRepositoryJpa userRepositoryJpa) {
        this.offersRepositoryJpa = offersRepositoryJpa;
        this.bidsRepositoryJpa = bidsRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    @Transactional
    public void run(String... args) {


        // Initialize Users
        User user1 = new User("User1", "user1@example.com", "password1");
        User user2 = new User("User2", "user2@example.com", "password2");
        User adminUser = new User("AdminUser", "admin@example.com", "adminPassword");
        adminUser.setRole("ADMIN");

        userRepositoryJpa.save(user1);
        userRepositoryJpa.save(user2);
        userRepositoryJpa.save(adminUser);

        // Create offers
        Offer offer1 = new Offer(0L, "Car", Offer.Status.NEW, "A new car", LocalDate.parse("2023-10-11"), 100.0);
        Offer offer2 = new Offer(0L, "Bike", Offer.Status.SOLD, "A sold Bike", LocalDate.parse("2023-11-20"), 150.00);
        Offer offer3 = new Offer(0L, "Bus", Offer.Status.FOR_SALE, "A sold Bus", LocalDate.parse("2023-11-20"), 800.00);

        offersRepositoryJpa.save(offer1);
        offersRepositoryJpa.save(offer2);
        offersRepositoryJpa.save(offer3);

        // Create and add bids to each offer
        Bid bid1 = new Bid(110.0);
        Bid bid2 = new Bid(120.0);
        Bid bid3 = new Bid(160.0);
        Bid bid4 = new Bid(210.0);
        Bid bid5 = new Bid(210.0);

        bid1.associateOffer(offer1);
        bid4.associateOffer(offer1);
        bid5.associateOffer(offer2);
        bid2.associateOffer(offer2);
        bid3.associateOffer(offer3);

        // Associate bids with users
        bid1.associateUser(user1);
        bid2.associateUser(user2);
        bid3.associateUser(adminUser);
        bid4.associateUser(user1);
        bid5.associateUser(adminUser);

        bidsRepositoryJpa.save(bid1);
        bidsRepositoryJpa.save(bid2);
        bidsRepositoryJpa.save(bid3);
        bidsRepositoryJpa.save(bid4);
        bidsRepositoryJpa.save(bid5);


    }
}
