package com.dps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dps.entities.PrintJob;

@Repository
public interface PrintJobRepository extends JpaRepository<PrintJob, Long> {

}
