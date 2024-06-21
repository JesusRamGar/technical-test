package com.inditex.hiring.infraestructure.database.repository.jpa;

import com.inditex.hiring.infraestructure.database.model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepositoryJpa extends JpaRepository<OfferEntity, Long> {}
