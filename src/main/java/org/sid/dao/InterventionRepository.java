package org.sid.dao;

import org.sid.entities.Intervention;
import org.sid.entities.Reclamation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
	@Query("select i from Intervention i where (i.localisation like:x)")
	public Page<Intervention> findByDesignationContains(@Param("x")String mc, Pageable pageable);

	@Query("select i from Intervention i where (i.technicien.id like:x)")
	public Page<Intervention> findByIDtechnicienContains(@Param("x")Long idT, Pageable pageable);
	
	
	
}
