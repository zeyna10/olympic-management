package com.cio.olympic.repository;

import com.cio.olympic.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    Optional<Pays> findByNom(String nom);
    Optional<Pays> findByCodeIso(String codeIso);
}