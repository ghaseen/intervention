package org.sid.dao;

import org.sid.entities.technicien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicienRepository  extends JpaRepository<technicien, Long> {

}
