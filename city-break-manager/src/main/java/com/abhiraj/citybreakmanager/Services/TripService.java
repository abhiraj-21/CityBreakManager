package com.abhiraj.citybreakmanager.Services;

import com.abhiraj.citybreakmanager.Entities.Trip;
import com.abhiraj.citybreakmanager.Repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getAllTrips(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id){
        return tripRepository.findById(id);
    }

    public Trip createNewTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Optional<Trip> getTripByCityName(String name){
        return tripRepository.findByCityName(name);
    }

    public Trip updateTrip(Long id, Trip newTrip) {
        Trip oldTrip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City with ID "+id+" not found"));
        oldTrip.setCity(newTrip.getCity());
        oldTrip.setStartDate(newTrip.getStartDate());
        oldTrip.setEndDate(newTrip.getEndDate());
        oldTrip.setRating(newTrip.getRating());
        oldTrip.setPersonalNotes(newTrip.getPersonalNotes());
        return tripRepository.save(oldTrip);
    }

    public Trip deleteById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City with ID "+id+" not found"));
        tripRepository.delete(trip);
        return trip;
    }
}
