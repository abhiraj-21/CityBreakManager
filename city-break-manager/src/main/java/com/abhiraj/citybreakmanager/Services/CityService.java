package com.abhiraj.citybreakmanager.Services;

import com.abhiraj.citybreakmanager.Entities.City;
import com.abhiraj.citybreakmanager.Repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    //Method to get all the cities present in bucket list
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    //Method to get a city by its ID
    public Optional<City> getCityById(Long id){
        return cityRepository.findById(id);
    }

    public City createNewCity(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> getCityByName(String name){
        return cityRepository.findByName(name);
    }

    public City updateCity(Long id, City newCity) {
        City oldCity = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City with ID "+id+" not found"));
        oldCity.setName(newCity.getName());
        oldCity.setCountry(newCity.getCountry());
        oldCity.setDetails(newCity.getDetails());
        return cityRepository.save(oldCity);
    }

    public City deleteById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City with ID "+id+" not found"));
        cityRepository.delete(city);
        return city;
    }
}
