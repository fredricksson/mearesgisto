package com.mea.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.api.dto.interfaces.IBeliver;
import com.mea.api.dto.interfaces.IBeliverWithPresences;
import com.mea.api.dto.interfaces.IPresenceDTO;
import com.mea.api.dto.response.BeliverWithPresencesDTO;
import com.mea.api.model.Beliver;

public interface BeliverRepository extends JpaRepository<Beliver, Long>{
	
	@Query(value = "select * from beliver where contact = :contact", nativeQuery = true)
	Beliver findBeliverByContact(@Param("contact") String contact) ;

	
	
	//@Query(value = "select  b from beliver b left join fetch b.presences", nativeQuery = false)
	@Query(value = "select  b from beliver b left join fetch b.presences where beliver_id = :id", nativeQuery = false)
	BeliverWithPresencesDTO findOneBeliverWithPresences(@Param("id") Long id);
	
	@Query(value = "select distinct p.beliver_id,  p.id , p.date, p.temperature, r.name as name_register, c.name as name_cult   from presence p  inner join register r on p.beliver_id = :id and p.register_id = r.id inner join cult c on c.id = p.cult_id order by p.date desc", nativeQuery = true)
	Page<IPresenceDTO> findPresences(Pageable pageable, @Param("id") Long id);
	
	@Query(value = "select distinct   b.id , b.name, b.contact, b.parent_contact, b.num_block, b.num_home, b.address   from beliver b order by b.id", nativeQuery = true)
	Page<IBeliver> findBelivers(Pageable pageable);
	
	@Query(value = "select distinct   b.id , b.name, b.contact, b.parent_contact, b.num_block, b.num_home, b.address   from beliver b where b.name like %:value% order by b.id", nativeQuery = true)
	Page<IBeliver> searchBeliver(Pageable pageable, @Param("value") String value);
	
	
	@Query(value = "select  b from beliver b left join fetch b.presences", nativeQuery = false)
	List<BeliverWithPresencesDTO> findBeliverWithPresences();
	

}