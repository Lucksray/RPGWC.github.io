package com.qa.rpgwc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.rpgwc.Entities.WeaponClass;
import com.qa.rpgwc.dtos.WeaponClassDTO;
import com.qa.rpgwc.exceptions.WeaponClassNotFound;
import com.qa.rpgwc.repo.WeaponClassRepo;

@SpringBootTest
public class WeaponClassServiceTest {

	@Autowired
	private WeaponClassService service;
	
	@MockBean
	private WeaponClassRepo repo;
	
	@Test
	public void addClass_Test() {
		WeaponClass weapon = new WeaponClass(null,"Jorgen", "club", "");
		WeaponClassDTO weapDTO = new WeaponClassDTO("Jorgen","club","");
		
		Mockito.when(repo.save(weapon)).thenReturn(weapon);
		
		Assertions.assertEquals(service.addClass(weapon), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(weapon);
	}
	
	@Test
	public void getAllClasses_Test() {
		List<WeaponClass> weapons = new ArrayList<>();
		weapons.add(new WeaponClass(1L,"Suprette","club",""));
		weapons.add(new WeaponClass(2L,"Matchelet","mace","spiked mace"));
		weapons.add(new WeaponClass(3L,"Gremory","sword","broadsword"));
		
		List<WeaponClassDTO> weapsDTO = new ArrayList<>();
		weapsDTO.add(new WeaponClassDTO("Suprette","club",""));
		weapsDTO.add(new WeaponClassDTO("Matchalet","mace","spiked mace"));
		weapsDTO.add(new WeaponClassDTO("Gremory","sword","broadsword"));
		
		Mockito.when(repo.findAll()).thenReturn(weapons);
		
		Assertions.assertEquals(service.getAllClasses(), weapsDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	// Need to improve testing
//	@Test
//	public void updateWeaponStat_Fail() {
//		Optional<WeaponStat> oldWeapon = Optional.of(new WeaponStat(1L,"mace","wood",2));
//		WeaponStat newWeapon = new WeaponStat(2L,"sword","iron",2);
//		WeaponStatDTO weapDTO = new WeaponStatDTO("sword","iron",2);
//		
//		Mockito.when(repo.findById(2L)).thenReturn(oldWeapon);
//		Mockito.when(repo.save(newWeapon)).thenReturn(newWeapon);
//		
//		Assertions.assertEquals(service.updateWeaponStat(2L, newWeapon), weapDTO);
//		
//		Mockito.verify(this.repo, Mockito.times(1)).findById(2L);
//		Mockito.verify(this.repo, Mockito.times(1)).save(newWeapon);
//	}
	
	@Test
	public void updateClass_Pass() {
		// getting null value
		Optional<WeaponClass> oldWeapon = Optional.ofNullable(new WeaponClass(1L,"Matchelet","mace","spiked mace"));
		WeaponClass newWeapon = new WeaponClass(1L,"Witchet","sword","greatsword");
		WeaponClassDTO weapDTO = new WeaponClassDTO("Witchet","sword","greatsword");
		
		Mockito.when(repo.findById(1L)).thenReturn(oldWeapon);
		Mockito.when(repo.save(newWeapon)).thenReturn(newWeapon);
		
		Assertions.assertEquals(service.updateClass(1L, newWeapon), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newWeapon);
	}
	
	@Test
	public void removeWeapon_Test() {
		Mockito.doNothing().when(repo).delete(null);
	}
	
	@Test
	public void getById_Test_Fail() {
		Long id = 1L;
		
		Mockito.when(repo.findById(id)).thenThrow(new WeaponClassNotFound());
	}
	
	@Test
	public void getById_Test_Pass() {
		Long id = 1L;
		Optional<WeaponClass> weapon = Optional.of(new WeaponClass(1L,"Winston","sword",""));
		WeaponClassDTO weapDTO = new WeaponClassDTO("Winston","sword","");
		
		Mockito.when(repo.findById(id)).thenReturn(weapon);
		
		Assertions.assertEquals(service.getById(id), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void getByName_Test_Fail() {
		String name = "Winston";
		
		Mockito.when(repo.findByName(name)).thenThrow(new WeaponClassNotFound());
	}
	
	@Test
	public void getByName_Test_Pass() {
		String name = "Winston";
		
		List<WeaponClass> weapons = new ArrayList<>();
		weapons.add(new WeaponClass(1L,"Winston","sword",""));
		weapons.add(new WeaponClass(2L,"Winston","mace","round-mace"));
		
		List<WeaponClassDTO> weapDTO = new ArrayList<>();
		weapDTO.add(new WeaponClassDTO("Winston","sword",""));
		weapDTO.add(new WeaponClassDTO("Winston","mace","round-mace"));
		
		Mockito.when(repo.findByName(name)).thenReturn(weapons);
		
		Assertions.assertEquals(service.getByName(name), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}
	
	@Test
	public void getLatest_Test() {
		WeaponClass weapon = new WeaponClass(1L,"Hemmy","mace","spiked mace");
		WeaponClassDTO weapDTO = new WeaponClassDTO("Hemmy","mace","spiked mace");
		
		Mockito.when(repo.getLatest()).thenReturn(weapon);
		
		Assertions.assertEquals(service.getLatest(), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).getLatest();
	}
}
