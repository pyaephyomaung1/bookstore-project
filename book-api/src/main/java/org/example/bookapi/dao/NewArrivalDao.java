package org.example.bookapi.dao;

import org.example.bookapi.entity.NewArrival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewArrivalDao extends JpaRepository<NewArrival, Integer> {
}
