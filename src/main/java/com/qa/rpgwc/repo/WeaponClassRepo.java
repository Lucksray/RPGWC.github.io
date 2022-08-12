package com.qa.rpgwc.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.rpgwc.Entities.WeaponClass;

@Repository
public interface WeaponClassRepo extends JpaRepository<WeaponClass,Long> {
	
	@Query(value = "SELECT * FROM weapon_class ORDER BY id DESC LIMIT 1", nativeQuery = true)
	WeaponClass getLatest();
	
	@Query(value = "SELECT * FROM weapon_class WHERE name=?1", nativeQuery = true)
	List<WeaponClass> findByName(String name);
	
	@Query(value = "DELETE FROM weapon_class WHERE stat_id=?1 AND name=?2", nativeQuery = true)
	void deleteRelate(Long id, String name);
	
	@Query(value = "SELECT * FROM weapon_class WHERE stat_id=?1",nativeQuery = true)
	Optional<WeaponClass> findRelateId(Long statId);
}
