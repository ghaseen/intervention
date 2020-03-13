package org.sid.dao;

import org.sid.entities.Reclamation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
	@Query("select r from Reclamation r where (r.nomPrenom like:x)")
	public Page<Reclamation> findByDesignationContains(@Param("x")String mc, Pageable pageable);
	
}
