package org.example.bookapi.dao;

import org.example.bookapi.entity.BestSeller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestSellerDao extends JpaRepository<BestSeller, Integer> {
}
