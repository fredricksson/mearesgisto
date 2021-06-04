package com.mea.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.api.model.Beliver;

public interface BeliverRepository extends JpaRepository<Beliver, Long>{
	
	@Query(value = "select * from beliver where contact = :contact or contact = :contact2", nativeQuery = true)
	Beliver findBeliverByContact(@Param("contact") String contact, @Param("contact2") String contact2) ;

	

}
