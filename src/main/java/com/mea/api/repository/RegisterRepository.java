package com.mea.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.api.dto.interfaces.IRegister;
import com.mea.api.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
	@Query(value = "SELECT r FROM register r LEFT JOIN FETCH r.roles WHERE r.contact = :contact", nativeQuery = false)
	Optional<Register> findByContact(@Param("contact") String contact);
	
	@Query(value = "SELECT r FROM register r LEFT JOIN FETCH r.roles WHERE r.contact = :contact", nativeQuery = false)
	Register findRegisterByContact(@Param("contact") String contact);
	
	@Query(value = "SELECT r FROM register r LEFT JOIN FETCH r.roles WHERE r.name = :name", nativeQuery = false)
	Optional<Register> findRegisterByName(@Param("name") String name);
	
	@Query(value = "select * from register where contact = :contact and name = :name", nativeQuery = true)
	Optional<Register> verify(@Param("name") String name, @Param("contact") String contact);
	
	@Query(value = "select distinct   r.id , r.name, r.contact  from register r order by r.id", nativeQuery = true)
	Page<IRegister> findRegisters(Pageable pageable);
	
	@Query(value = "select distinct   b.id , b.name, b.contact from register b where b.name like %:value% order by b.id", nativeQuery = true)
	Page<IRegister> searchRegister(Pageable pageable, @Param("value") String value);
	
}
