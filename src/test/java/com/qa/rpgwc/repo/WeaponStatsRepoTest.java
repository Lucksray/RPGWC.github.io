package com.qa.rpgwc.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.rpgwc.RpgwcApplication;
import com.qa.rpgwc.Entities.WeaponStat;

@SpringBootTest(classes = {RpgwcApplication.class})
public class WeaponStatsRepoTest {
	
	@Autowired
	private WeaponStatRepo repo;
	
	@BeforeEach
	public void dWipe(){}
	
	@Test
	public void read() {
		WeaponStat expected = new WeaponStat(1L,"Baeldug","Iron",5);
		
		Assertions.assertEquals(this.repo.findById(1L), expected);
	}
	
	@Test
	public void save_Test() {
		WeaponStat weapon = new WeaponStat(1L,"Baeldug","Iron",5);
		
		Assertions.assertEquals(weapon, this.repo.save(weapon));
	}
	
	@Test
	public void findByName_Test() {
		Optional<WeaponStat> expected = Optional.of(new WeaponStat(1L,"Baeldug","Iron",5));
		String name = "Baeldug";
		
		Assertions.assertEquals(expected, this.repo.findByName(name));
	}
	
	@Test
	public void getLatest_Test() {
		Optional<WeaponStat> expected = Optional.ofNullable(new WeaponStat(2L,"Mjolnir","Mithril",10));
		
		Assertions.assertEquals(expected, this.repo.getLatest());
	}
	
	@Test
	public void deleteRelate_Test() {
		Long id = 2L; String name = "Mjolnir";
		repo.deleteRelate(id, name);
		
		assertThat(this.repo.count()).isEqualTo(1);
	}
}
