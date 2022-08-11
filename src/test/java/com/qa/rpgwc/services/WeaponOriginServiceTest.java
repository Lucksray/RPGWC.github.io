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

import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.dtos.WeaponOriginDTO;
import com.qa.rpgwc.exceptions.WeaponOriginNotFound;
import com.qa.rpgwc.repo.WeaponOriginRepo;

@SpringBootTest
public class WeaponOriginServiceTest {

	@Autowired
	private WeaponOriginService service;
	
	@MockBean
	private WeaponOriginRepo repo;
	
	@Test
	public void addOrigin_Test() {
		WeaponOrigin weapon = new WeaponOrigin(null,"club", "Greg", "Greg's first creation");
		WeaponOriginDTO weapDTO = new WeaponOriginDTO("club","Greg","Greg's first creation");
		
		Mockito.when(repo.save(weapon)).thenReturn(weapon);
		
		Assertions.assertEquals(service.addOrigin(weapon), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(weapon);
	}
	
	@Test
	public void getAllOrigins_Test() {
		List<WeaponOrigin> weapons = new ArrayList<>();
		weapons.add(new WeaponOrigin(1L,"club","Greg","Unknown"));
		weapons.add(new WeaponOrigin(2L,"mace","Brace","Unknown"));
		weapons.add(new WeaponOrigin(3L,"sword","Jeremy","Unknown"));
		
		List<WeaponOriginDTO> weapsDTO = new ArrayList<>();
		weapsDTO.add(new WeaponOriginDTO("club","Greg","Unknown"));
		weapsDTO.add(new WeaponOriginDTO("mace","Brace","Unknown"));
		weapsDTO.add(new WeaponOriginDTO("sword","Jeremy","Unknown"));
		
		Mockito.when(repo.findAll()).thenReturn(weapons);
		
		Assertions.assertEquals(service.getAllOrigins(), weapsDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	// Need to improve testing
//	@Test
//	public void updateOrigin_Fail() {
//		Optional<WeaponOrigin> oldWeapon = Optional.of(new WeaponOrigin(1L,"mace","wood",2));
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
	public void updateOrigin_Pass() {
		// Currently Having problems with nullable value
		Optional<WeaponOrigin> oldWeapon = Optional.ofNullable(new WeaponOrigin(1L,"mace","Greg","Unknown"));
		WeaponOrigin newWeapon = new WeaponOrigin(1L,"sword","Brace","Brace's most powerful weapon");
		WeaponOriginDTO weapDTO = new WeaponOriginDTO("sword","Brace","Brace's most powerful weapon");
		
		Mockito.when(repo.findById(1L)).thenReturn(oldWeapon);
		Mockito.when(repo.save(newWeapon)).thenReturn(newWeapon);
		
		Assertions.assertEquals(service.updateOrigin(1L, newWeapon), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newWeapon);
	}
	
	@Test
	public void removeOrigin_Test() {
		Mockito.doNothing().when(repo).delete(null);
	}
	
	@Test
	public void getById_Test_Fail() {
		Long id = 1L;
		
		Mockito.when(repo.findById(id)).thenThrow(new WeaponOriginNotFound());
	}
	
	@Test
	public void getById_Test_Pass() {
		Long id = 1L;
		Optional<WeaponOrigin> weapon = Optional.of(new WeaponOrigin(1L,"sword","Joe","Joe's last creation"));
		WeaponOriginDTO weapDTO = new WeaponOriginDTO("sword","Joe","Joe's last creation");
		
		Mockito.when(repo.findById(id)).thenReturn(weapon);
		
		Assertions.assertEquals(service.getById(id), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void getByName_Test_Fail() {
		String name = "sword";
		
		Mockito.when(repo.findByName(name)).thenThrow(new WeaponOriginNotFound());
	}
	
	@Test
	public void getByName_Test_Pass() {
		String name = "sword";
		
		List<WeaponOrigin> weapons = new ArrayList<>();
		weapons.add(new WeaponOrigin(1L,"sword","Origan","Unknown"));
		weapons.add(new WeaponOrigin(2L,"sword","Jace","Unknown"));
		
		List<WeaponOriginDTO> weapDTO = new ArrayList<>();
		weapDTO.add(new WeaponOriginDTO("sword","Origan","Unknown"));
		weapDTO.add(new WeaponOriginDTO("sword","Jace","Unknown"));
		
		Mockito.when(repo.findByName(name)).thenReturn(weapons);
		
		Assertions.assertEquals(service.getByName(name), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}
	
	@Test
	public void getLatest_Test() {
		WeaponOrigin weapon = new WeaponOrigin(1L,"mace","Greg","A masterpiece");
		WeaponOriginDTO weapDTO = new WeaponOriginDTO("mace","Greg","A masterpiece");
		
		Mockito.when(repo.getLatest()).thenReturn(weapon);
		
		Assertions.assertEquals(service.getLatest(), weapDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).getLatest();
	}
}
