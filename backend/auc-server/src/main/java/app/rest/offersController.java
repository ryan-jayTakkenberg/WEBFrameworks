package app.rest;

import app.models.Offer;
import app.repository.OffersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping("/offers")
public class offersController {

    private final OffersRepository offersRepository;

    @GetMapping("/test")
    public List<Offer> getTestOffer(){
        return List.of(
                new Offer("Test-Offer-A"),
                new Offer("Test-Offer-B")
        );
    }

    @Autowired
    public offersController(OffersRepository offersRepository){
        this.offersRepository = offersRepository;
    }

    @GetMapping("/all")
    public List<Offer> getAllOffers() {
       return offersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getById(@PathVariable int id ){
        Offer offer = offersRepository.findById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/offers")
    public ResponseEntity<Offer> addNewOffer(@RequestBody Offer offer ){
        if (offer == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request for malformed requests
        }
        Offer addedOffer = offersRepository.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedOffer.getId())
                .toUri();

        return ResponseEntity.created(location).body(addedOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable int id, @RequestBody Offer updatedOffer) {
        Offer existingOffer = offersRepository.findById(id);
        if (existingOffer != null) {
            updatedOffer.setId(id);
            Offer savedOffer = offersRepository.save(updatedOffer);
            return ResponseEntity.ok(savedOffer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offerToDelete = offersRepository.findById(id);
        if (offerToDelete != null) {
            offersRepository.delete(id);
            return ResponseEntity.ok(offerToDelete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
