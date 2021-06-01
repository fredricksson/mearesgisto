package com.mea.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.api.model.Cult;

public interface CultRepository extends JpaRepository<Cult, Long>{

		@Query(value = "select id from cult where name = :name" , nativeQuery = true)
		Long getId(@Param("name") String name);
}
