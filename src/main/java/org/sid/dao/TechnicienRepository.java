package org.sid.dao;

import org.sid.entities.Client;
import org.sid.entities.technicien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TechnicienRepository  extends JpaRepository<technicien, Long> {
	@Query("select t from technicien t where (t.nom like:x)")
	public Page<technicien> findByDesignationContains(@Param("x")String mc, Pageable pageable);
	
	@Query("select t from technicien t where (t.username like:x)")
	public technicien ChercherTechnicienusername(@Param("x")String username);
	
}
