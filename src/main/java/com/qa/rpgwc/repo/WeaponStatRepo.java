package com.qa.rpgwc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.rpgwc.Entities.WeaponStat;

@Repository
public interface WeaponStatRepo extends JpaRepository<WeaponStat, Long>{
	
	@Query(value = "SELECT * FROM weapon_stat WHERE name=?1", nativeQuery = true)
	List<WeaponStat> findByName(String name);
	
	@Query(value = "SELECT * FROM weapon_stat ORDER BY id DESC LIMIT 1", nativeQuery = true)
	WeaponStat getLatest();
}
