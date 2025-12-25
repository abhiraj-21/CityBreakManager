package com.abhiraj.citybreakmanager.Repositories;

import com.abhiraj.citybreakmanager.Entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
