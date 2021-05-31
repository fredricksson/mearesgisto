package com.mea.api.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.api.model.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long>{
	
	@Query(value = "select * from presence  where date(date) = :date and cult_id = :cult_id and beliver_id = :beliver_id", nativeQuery = true)
	Presence verifyBeliverIsRegisted(@Param("date") Date date, @Param("cult_id") Long cult_id, @Param("beliver_id") Long beliver_id) ;
}
