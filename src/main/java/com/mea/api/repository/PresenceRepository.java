package com.mea.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.api.model.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long>{

}
