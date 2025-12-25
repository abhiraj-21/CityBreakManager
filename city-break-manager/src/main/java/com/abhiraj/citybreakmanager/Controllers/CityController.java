package com.abhiraj.citybreakmanager.Controllers;

import com.abhiraj.citybreakmanager.Entities.City;
import com.abhiraj.citybreakmanager.Services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<?> retrieveAllCities(){
        List<City> cities = cityService.getAllCities();

        if(cities.isEmpty()){
            return ResponseEntity.status(404).body("Bucket list is empty!!");
        }

        return ResponseEntity.ok(cities);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<?> retrieveCityById(@PathVariable Long id){
        Optional<City> city = cityService.getCityById(id);

        if(city.isEmpty()){
            return ResponseEntity.status(404).body("City with ID "+id+" is not in the bucket list!!");
        }

        return ResponseEntity.ok(city.get());
    }

    @PostMapping("/cities")
    public ResponseEntity<?> addNewCity(@RequestBody City city){
        if(cityService.getCityByName(city.getName()).isPresent()){
            return ResponseEntity.status(409).body(city.getName()+" already exists in bucket list!!");
        }
        City createdCity = cityService.createNewCity(city);
        return ResponseEntity.status(201).body(createdCity);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody City city){
        try{
            City updatedCity = cityService.updateCity(id, city);
            return ResponseEntity.ok(updatedCity);
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id){
        try{
            City deletedCity = cityService.deleteById(id);
            return ResponseEntity.ok(deletedCity);
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
