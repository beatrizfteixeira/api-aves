package com.grupo4.APIAvesdoBrasil.repository;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdsRepository extends JpaRepository<Bird, Integer> {
}
