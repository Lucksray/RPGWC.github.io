package com.qa.rpgwc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.rpgwc.Entities.WeaponOrigin;

@Repository
public interface WeaponOriginRepo extends JpaRepository<WeaponOrigin,Long> {
	
	@Query(value = "SELECT * FROM weapon_origin ORDER BY id DESC LIMIT 1", nativeQuery = true)
	WeaponOrigin getLatest();
	
	@Query(value = "SELECT * FROM weapon_origin WHERE name=?1", nativeQuery = true)
	List<WeaponOrigin> findByName(String name);
}
