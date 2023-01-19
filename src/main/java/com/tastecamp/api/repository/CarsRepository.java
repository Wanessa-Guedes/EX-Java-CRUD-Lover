package com.tastecamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tastecamp.api.model.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

}
