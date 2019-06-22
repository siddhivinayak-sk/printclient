package com.dps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dps.entities.Printer;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long> {
	
	
	public List<Printer> findByName(String name);

}
