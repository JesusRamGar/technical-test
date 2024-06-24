package com.kairosds.infraestructure.database.repository.jpa;

import com.kairosds.infraestructure.database.model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepositoryJpa extends JpaRepository<OfferEntity, Long> {}
