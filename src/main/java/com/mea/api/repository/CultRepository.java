package com.mea.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.api.dto.interfaces.IBeliver;
import com.mea.api.dto.interfaces.ICult;
import com.mea.api.model.Cult;

public interface CultRepository extends JpaRepository<Cult, Long>{

		@Query(value = "select id from cult where name = :name" , nativeQuery = true)
		Long getId(@Param("name") String name);
		
		@Query(value = "select distinct   b.id , b.name  from cult b where b.name like %:value% order by b.id", nativeQuery = true)
		Page<ICult> searchCult(Pageable pageable, @Param("value") String value);
}
