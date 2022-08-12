package com.qa.rpgwc.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.rpgwc.RpgwcApplication;
import com.qa.rpgwc.Entities.WeaponOrigin;

@SpringBootTest(classes = {RpgwcApplication.class})
public class WeaponOriginRepoTest {

	@Autowired
	private WeaponOriginRepo repo;
	
	@BeforeEach
	public void dWipe(){}
	
	@Test
	public void read_Test() {
		Optional<WeaponOrigin> expected = Optional.ofNullable(new WeaponOrigin(1L,"Baeldug","Jeremy","Unknown"));
		
		Assertions.assertEquals(expected, this.repo.findById(1L));
	}
	
	@Test
	public void save_Test() {
		WeaponOrigin weapon = new WeaponOrigin(1L,"Baeldug","Jeremy","Unknown");
		
		Assertions.assertEquals(weapon, this.repo.save(weapon));
	}
	
	@Test
	public void findByName_Test() {
		Optional<WeaponOrigin> expected = Optional.of(new WeaponOrigin(1L,"Baeldug","Jeremy","Unknown"));
		String name = "Baeldug";
		
		Assertions.assertEquals(expected, this.repo.findByName(name));
	}
	
	@Test
	public void getLatest_Test() {
		Optional<WeaponOrigin> expected = Optional.ofNullable(new WeaponOrigin(2L,"Mjolnir","Tartigre","Forged in a dying star"));
		
		Assertions.assertEquals(expected, this.repo.getLatest());
	}
	
	@Test
	public void deleteRelate_Test() {
		Long id = 2L; String name = "Mjolnir";
		repo.deleteRelate(id, name);
		
		assertThat(this.repo.count()).isEqualTo(1);
	}
	
	@Test
	public void findRelateId_Test() {
		Optional<WeaponOrigin> expected = Optional.ofNullable(new WeaponOrigin(2L,"Mjolnir","Tartigre","Forged in a dying star"));
		Long id = 2L;
		
		Assertions.assertEquals(expected, this.repo.findRelateId(id));
	}
}
