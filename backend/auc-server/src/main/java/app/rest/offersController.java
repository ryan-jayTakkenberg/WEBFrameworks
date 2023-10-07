package app.rest;

import app.models.Offer;
import app.repository.OffersRepository;
import app.repository.OffersRepositoryMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class offersController {

    private final OffersRepository offersRepository;

//    @GetMapping("/test")
//    public List<Offer> getTestOffer(){
//        return List.of(
//                new Offer("Test-Offer-A"),
//                new Offer("Test-Offer-B")
//        );
//    }

    @Autowired
    public offersController(OffersRepository offersRepository){
        this.offersRepository = offersRepository;
    }

    @GetMapping("/all")
    public List<Offer> getAllOffers() {
       return offersRepository.findAll();
    }




}
