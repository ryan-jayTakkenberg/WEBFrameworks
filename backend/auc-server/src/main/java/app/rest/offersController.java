package app.rest;

import app.models.Offer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class offersController {

    @GetMapping("/test")
    public List<Offer> getTestOffer(){
        return List.of(
                new Offer("Test-Offer-A"),
                new Offer("Test-Offer-B")
        );
    }
}
