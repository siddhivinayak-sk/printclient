package com.dps.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dps.entities.PrintRequest;

public interface PrintRequestRepository extends JpaRepository<PrintRequest, Long> {

	public List<PrintRequest> findByStatus(Integer status, Pageable pageable);
}
