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
        Offer offer3 = new Offer(0L, "Bus", Offer.Status.FOR_SALE, "A Bus", LocalDate.parse("2023-11-20"), 800.00);
        Offer offer4 = new Offer(0L, "House", Offer.Status.FOR_SALE, "A spacious house", LocalDate.parse("2023-12-01"), 500000.0);
        Offer offer5 = new Offer(0L, "Apartment", Offer.Status.NEW, "A modern apartment", LocalDate.parse("2023-12-10"), 120000.0);
        Offer offer6 = new Offer(0L, "Computer", Offer.Status.FOR_SALE, "High-performance computer", LocalDate.parse("2023-12-15"), 2000.0);
        Offer offer7 = new Offer(0L, "Smartphone", Offer.Status.SOLD, "Latest smartphone model", LocalDate.parse("2023-12-18"), 800.0);
        Offer offer8 = new Offer(0L, "Guitar", Offer.Status.NEW, "Professional guitar", LocalDate.parse("2023-12-20"), 700.0);
        Offer offer9 = new Offer(0L, "Bookshelf", Offer.Status.SOLD, "Wooden bookshelf", LocalDate.parse("2023-12-22"), 50.0);
        Offer offer10 = new Offer(0L, "Fitness Equipment", Offer.Status.EXPIRED, "Home gym equipment", LocalDate.parse("2023-12-25"), 300.0);


        offersRepositoryJpa.save(offer1);
        offersRepositoryJpa.save(offer2);
        offersRepositoryJpa.save(offer3);
        offersRepositoryJpa.save(offer4);
        offersRepositoryJpa.save(offer5);
        offersRepositoryJpa.save(offer6);
        offersRepositoryJpa.save(offer7);
        offersRepositoryJpa.save(offer8);
        offersRepositoryJpa.save(offer9);
        offersRepositoryJpa.save(offer10);

        // Create and add bids to each offer
        Bid bid1 = new Bid(110.0);
        Bid bid2 = new Bid(120.0);
        Bid bid3 = new Bid(160.0);
        Bid bid4 = new Bid(210.0);
        Bid bid5 = new Bid(230.00);
        Bid bid6 = new Bid(102.00);
        Bid bid7 = new Bid(542.00);
        Bid bid8 = new Bid(212.00);
        Bid bid9 = new Bid(61.00);
        Bid bid10 = new Bid(230.0);
        Bid bid11 = new Bid(10000.00);
        Bid bid12 = new Bid(4511.00);
        Bid bid13 = new Bid(32321.00);
        Bid bid14 = new Bid(210.0);
        Bid bid15 = new Bid(5642.00);
        Bid bid16 = new Bid(424.00);

        bid1.associateOffer(offer2);
        bid2.associateOffer(offer2);
        bid3.associateOffer(offer2);
        bid4.associateOffer(offer3);
        bid5.associateOffer(offer3);
        bid6.associateOffer(offer4);
        bid7.associateOffer(offer4);
        bid8.associateOffer(offer6);
        bid9.associateOffer(offer6);
        bid10.associateOffer(offer7);
        bid11.associateOffer(offer7);
        bid12.associateOffer(offer9);
        bid13.associateOffer(offer9);
        bid14.associateOffer(offer10);
        bid15.associateOffer(offer10);
        bid16.associateOffer(offer10);


        // Associate bids with users
        bid1.associateUser(user1);
        bid2.associateUser(user2);
        bid3.associateUser(adminUser);
        bid4.associateUser(user1);
        bid5.associateUser(adminUser);
        bid6.associateUser(adminUser);
        bid7.associateUser(user1);
        bid8.associateUser(user2);
        bid9.associateUser(adminUser);
        bid10.associateUser(user1);
        bid11.associateUser(user2);
        bid12.associateUser(adminUser);
        bid13.associateUser(user1);
        bid14.associateUser(user2);
        bid15.associateUser(adminUser);

        bidsRepositoryJpa.save(bid1);
        bidsRepositoryJpa.save(bid2);
        bidsRepositoryJpa.save(bid3);
        bidsRepositoryJpa.save(bid4);
        bidsRepositoryJpa.save(bid5);
        bidsRepositoryJpa.save(bid6);
        bidsRepositoryJpa.save(bid7);
        bidsRepositoryJpa.save(bid8);
        bidsRepositoryJpa.save(bid9);
        bidsRepositoryJpa.save(bid10);
        bidsRepositoryJpa.save(bid11);
        bidsRepositoryJpa.save(bid12);
        bidsRepositoryJpa.save(bid13);
        bidsRepositoryJpa.save(bid14);
        bidsRepositoryJpa.save(bid15);



    }
}
