package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.Exceptions.ResourceNotFoundException;
import app.models.Bid;
import app.models.Offer;
import app.models.Views;
import app.repository.BidsRepositoryJpa;
import app.repository.OffersRepository;
import app.repository.OffersRepositoryJpa;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/offers")
public class offersController {

    private final OffersRepositoryJpa offersRepository;
    private final BidsRepositoryJpa bidsRepositoryJpa;


    @GetMapping("/test")
    public List<Offer> getTestOffer(){
        return List.of(
                new Offer("Test-Offer-A"),
                new Offer("Test-Offer-B")
        );
    }




    @Autowired
    public offersController(OffersRepositoryJpa offersRepository, BidsRepositoryJpa bidsRepositoryJpa) {
        this.offersRepository = offersRepository;
        this.bidsRepositoryJpa = bidsRepositoryJpa;

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
        Offer offer = (Offer) offersRepository.findById(id);
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
        Offer addedOffer = (Offer) offersRepository.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedOffer.getId())
                .toUri();

        return ResponseEntity.created(location).body(addedOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable int id, @RequestBody Offer updatedOffer) {
        Offer existingOffer = (Offer) offersRepository.findById(id);
        if (existingOffer != null) {
            if (id != updatedOffer.getId()) {
                // Het ID in het pad komt niet overeen met het ID in het verzoek.
                throw new PreConditionFailedException("ID in path does not match ID in request.");
            }

            updatedOffer.setId(id);
            Offer savedOffer = (Offer) offersRepository.save(updatedOffer);
            return ResponseEntity.ok(savedOffer);
        } else {
            // Het aanbod is niet gevonden.
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offerToDelete = (Offer) offersRepository.findById(id);
        if (offerToDelete != null) {
            offersRepository.delete(id);
            return ResponseEntity.ok(offerToDelete);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    @PostMapping("/{offerId}/bids")
    public ResponseEntity<Bid> addBidToOffer(@PathVariable long offerId, @RequestBody Bid newBid) {
        Offer offer = (Offer) offersRepository.findById(offerId);

        if (offer == null) {
            throw new PreConditionFailedException("Offer not found");
        }

        if (offer.getStatus() == Offer.Status.FOR_SALE) {
            throw new PreConditionFailedException("Offer is not for sale");
        }

        if (newBid.getValue() <= offer.getValueHighestBid()) {
            throw new PreConditionFailedException("Bid is lower or equal to the highest bid on the offer");
        }



        // Set the bid value explicitly
        newBid.setBidValue(newBid.getValue());

        // Associate bid with offer
        newBid.associateOffer(offer);

        // Save the bid to the repository and get the managed instance
        Bid managedBid =  bidsRepositoryJpa.save(newBid);

        return ResponseEntity.status(HttpStatus.CREATED).body(managedBid);

    }

    @GetMapping("/status")
    public List<Offer> getStatus(@RequestParam String status) {
        return offersRepository.findByQuery("Offer_find_by_status", status);
    }

    @GetMapping("/sub-title")
    public List<Offer> getSubTitle(@RequestParam String description) {
        return offersRepository.findByQuery("Offer_find_by_title", description);
    }


}
