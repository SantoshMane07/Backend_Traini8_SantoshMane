package com.traini8.trainingcenters.traini8_registry.repositories;

import com.traini8.trainingcenters.traini8_registry.entities.TrainingCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {
    // Method to find training centers by city and state with pagination
    Page<TrainingCenter> findByAddressCityIgnoreCaseAndAddressStateIgnoreCase(String city, String state, Pageable pageable);

    // Method to find training centers by city only with pagination
    Page<TrainingCenter> findByAddressCityIgnoreCase(String city, Pageable pageable);

    // Method to find training centers by state only with pagination
    Page<TrainingCenter> findByAddressStateIgnoreCase(String state, Pageable pageable);
}

