package app;

import app.models.Offer;
import app.repository.OffersRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OffersRepositoryJpa offersRepositoryJpa;

    @Autowired
    public DataLoader(OffersRepositoryJpa offersRepositoryJpa) {
        this.offersRepositoryJpa = offersRepositoryJpa;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Offer offer1 = new Offer( 0,"Car", Offer.Status.NEW, "A new car", LocalDate.parse("2023-10-11"), 100.0);
        Offer offer2 = new Offer(0,"Bike", Offer.Status.SOLD, "A sold Bike", LocalDate.parse("2023-11-20"), 150.00);
        Offer offer3 = new Offer(0,"Bus", Offer.Status.FOR_SALE, "A sold Bike", LocalDate.parse("2023-11-20"), 800.00);

        offersRepositoryJpa.save(offer1);
        offersRepositoryJpa.save(offer2);
        offersRepositoryJpa.save(offer3);
    }
}
