package com.coffee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
    @Query("SELECT c.name, COUNT(c.name) FROM CoffeeEntity c GROUP BY c.name ORDER BY COUNT(c.name) DESC")
    List<Object[]> findMostOrderedCoffees();
}
