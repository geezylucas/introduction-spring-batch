package com.geezylucas.introductionspringbatch.infrastructure.adapter.repository;

import com.geezylucas.introductionspringbatch.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
