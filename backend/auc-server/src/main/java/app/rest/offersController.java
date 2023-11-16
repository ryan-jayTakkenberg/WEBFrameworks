package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.Exceptions.ResourceNotFoundException;
import app.models.Offer;
import app.models.Views;
import app.repository.OffersRepository;

import com.fasterxml.jackson.annotation.JsonView;
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

    @GetMapping("")
    public List<Offer> getAllOffers() {
       return offersRepository.findAll();
    }

    @JsonView(Views.Summary.class)
    @GetMapping("/summary")
    public List<Offer> getOffersSummary() {
        return offersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getById(@PathVariable int id ){
        Offer offer = offersRepository.findById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    @PostMapping("")
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
            if (id != updatedOffer.getId()) {
                // Het ID in het pad komt niet overeen met het ID in het verzoek.
                throw new PreConditionFailedException("ID in path does not match ID in request.");
            }

            updatedOffer.setId(id);
            Offer savedOffer = offersRepository.save(updatedOffer);
            return ResponseEntity.ok(savedOffer);
        } else {
            // Het aanbod is niet gevonden.
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offerToDelete = offersRepository.findById(id);
        if (offerToDelete != null) {
            offersRepository.delete(id);
            return ResponseEntity.ok(offerToDelete);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }
}
