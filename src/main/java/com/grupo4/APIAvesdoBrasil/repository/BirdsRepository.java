package com.grupo4.APIAvesdoBrasil.repository;

import com.grupo4.APIAvesdoBrasil.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BirdsRepository extends JpaRepository<Bird, Integer> {


}
