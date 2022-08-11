package com.qa.rpgwc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.rpgwc.Entities.WeaponStats;

public interface WeaponStatRepo extends JpaRepository<WeaponStats, Long>{

}
