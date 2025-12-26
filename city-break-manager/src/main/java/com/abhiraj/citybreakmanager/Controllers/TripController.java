package com.abhiraj.citybreakmanager.Controllers;

import com.abhiraj.citybreakmanager.Entities.City;
import com.abhiraj.citybreakmanager.Entities.Trip;
import com.abhiraj.citybreakmanager.Services.CityService;
import com.abhiraj.citybreakmanager.Services.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @GetMapping("/trips")
    public ResponseEntity<?> retrieveAllTrips(){
        List<Trip> trips = tripService.getAllTrips();

        if(trips.isEmpty()){
            return ResponseEntity.status(404).body("No trips yet!!");
        }

        return ResponseEntity.ok(trips);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<?> retrieveTripById(@PathVariable Long id){
        Optional<Trip> trip = tripService.getTripById(id);

        if(trip.isEmpty()){
            return ResponseEntity.status(404).body("No trip with TripID "+id);
        }

        return ResponseEntity.ok(trip.get());
    }

    @PostMapping("/trips")
    public ResponseEntity<?> addNewTrip(@RequestBody Trip trip){
        if(tripService.getTripByCityName(trip.getCity().getName()).isPresent()){
            return ResponseEntity.status(409).body("Trip has already done!!");
        }
        Trip createdTrip = tripService.createNewTrip(trip);
        return ResponseEntity.status(201).body(createdTrip);
    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody Trip trip){
        try{
            Trip updatedTrip = tripService.updateTrip(id, trip);
            return ResponseEntity.ok(updatedTrip);
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id){
        try{
            Trip deletedTrip = tripService.deleteById(id);
            return ResponseEntity.ok(deletedTrip);
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
