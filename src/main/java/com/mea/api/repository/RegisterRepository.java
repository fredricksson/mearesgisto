package com.mea.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import com.mea.api.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
	@Query(value = "SELECT r FROM register r LEFT JOIN FETCH r.roles WHERE r.contact = :contact", nativeQuery = false)
	Optional<Register> findByContact(@Param("contact") String contact);
	
	@Query(value = "SELECT r FROM register r LEFT JOIN FETCH r.roles WHERE r.name = :name", nativeQuery = false)
	Optional<Register> findRegisterByName(@Param("name") String name);
	
	@Query(value = "select * from register where contact = :contact and name = :name", nativeQuery = true)
	Optional<Register> verify(@Param("name") String name, @Param("contact") String contact);
}
