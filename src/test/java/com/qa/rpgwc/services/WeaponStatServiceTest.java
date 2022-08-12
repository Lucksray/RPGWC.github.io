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

import com.qa.rpgwc.Entities.WeaponStat;
import com.qa.rpgwc.dtos.WeaponStatDTO;
import com.qa.rpgwc.exceptions.WeaponStatNotFound;
import com.qa.rpgwc.repo.WeaponStatRepo;

@SpringBootTest
public class WeaponStatServiceTest {

	@Autowired
	private WeaponStatService service;
	
	@MockBean
	private WeaponStatRepo repo;
	
	@Test
	public void addWeaponStat_Test() {
		WeaponStat weapon = new WeaponStat(null,"club", "wood", 1);
		WeaponStatDTO weapDTO = new WeaponStatDTO("club","wood",1);
		
		Mockito.when(repo.save(weapon)).thenReturn(weapon);
		
		Assertions.assertEquals(service.addWeaponStat(weapon), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(weapon);
	}
	
	@Test
	public void getAllWeaponStat_Test() {
		List<WeaponStat> weapons = new ArrayList<>();
		weapons.add(new WeaponStat(1L,"club","wood",1));
		weapons.add(new WeaponStat(2L,"mace","iron",1));
		weapons.add(new WeaponStat(3L,"sword","iron", 2));
		
		List<WeaponStatDTO> weapsDTO = new ArrayList<>();
		weapsDTO.add(new WeaponStatDTO("club","wood",1));
		weapsDTO.add(new WeaponStatDTO("mace","iron",1));
		weapsDTO.add(new WeaponStatDTO("sword","iron",2));
		
		Mockito.when(repo.findAll()).thenReturn(weapons);
		
		Assertions.assertEquals(service.getAllWeaponStats(), weapsDTO);
		
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
	public void updateWeaponStat_Pass() {
		Optional<WeaponStat> oldWeapon = Optional.ofNullable(new WeaponStat(1L,"mace","wood",2));
		WeaponStat newWeapon = new WeaponStat(1L,"sword","iron",2);
		WeaponStatDTO weapDTO = new WeaponStatDTO("sword","iron",2);
		
		Mockito.when(repo.findById(1L)).thenReturn(oldWeapon);
		Mockito.when(repo.save(newWeapon)).thenReturn(newWeapon);
		
		Assertions.assertEquals(service.updateWeaponStat(1L, newWeapon), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newWeapon);
	}
	
	@Test
	public void removeWeaponStat_Test() {
		Mockito.doNothing().when(repo).delete(null);
	}
	
	@Test
	public void getById_Test_Fail() {
		Long id = 1L;
		
		Mockito.when(repo.findById(id)).thenThrow(new WeaponStatNotFound());
	}
	
	@Test
	public void getById_Test_Pass() {
		Long id = 1L;
		Optional<WeaponStat> weapon = Optional.of(new WeaponStat(1L,"sword","iron",2));
		WeaponStatDTO weapDTO = new WeaponStatDTO("sword","iron",2);
		
		Mockito.when(repo.findById(id)).thenReturn(weapon);
		
		Assertions.assertEquals(service.getById(id), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void getByName_Test_Fail() {
		String name = "sword";
		
		Mockito.when(repo.findByName(name)).thenThrow(new WeaponStatNotFound());
	}
	
	@Test
	public void getByNAme_Test_Pass() {
		String name = "sword";
		
		List<WeaponStat> weapons = new ArrayList<>();
		weapons.add(new WeaponStat(1L,"sword","iron",3));
		weapons.add(new WeaponStat(2L,"sword","wood",4));
		
		List<WeaponStatDTO> weapDTO = new ArrayList<>();
		weapDTO.add(new WeaponStatDTO("sword","iron",3));
		weapDTO.add(new WeaponStatDTO("sword","wood",4));
		
		Mockito.when(repo.findByName(name)).thenReturn(weapons);
		
		Assertions.assertEquals(service.getByName(name), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}
	
	@Test
	public void getLatest_Test() {
		WeaponStat weapon = new WeaponStat(1L,"mace","wood",3);
		WeaponStatDTO weapDTO = new WeaponStatDTO("mace","wood",3);
		
		Mockito.when(repo.getLatest()).thenReturn(weapon);
		
		Assertions.assertEquals(service.getLatest(), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).getLatest();
	}
}
