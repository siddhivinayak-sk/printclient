package com.dps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dps.entities.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

}
