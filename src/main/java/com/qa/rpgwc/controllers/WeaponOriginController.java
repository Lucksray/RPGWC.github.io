package com.qa.rpgwc.controllers;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.dtos.WeaponOriginDTO;
import com.qa.rpgwc.dtos.WeaponStatDTO;
import com.qa.rpgwc.services.WeaponOriginService;

@Service
@RestController
public class WeaponOriginController {

	private WeaponOriginService service;
	
	public WeaponOriginController(WeaponOriginService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/origin/getAll")
	public List<WeaponOriginDTO> getAll(){
		return service.getAllOrigins();
	}
	
	public void create(TotalEntity fullWeapon) {
		WeaponOrigin weapon = new WeaponOrigin();
		weapon.setName(fullWeapon.getName());
		weapon.setCreator(fullWeapon.getCreator());
		weapon.setOrigin(fullWeapon.getOrigin());
		
		service.addOrigin(weapon);
	}
	
	public List<WeaponOrigin> readByName(String name){
		return service.readByName(name);
	}
	
	public boolean testSingle(String name) {
		try { 
			WeaponOrigin weapon = this.service.getSingle(name);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Long getSingleId(String name) {
		WeaponOrigin weapon = this.service.getSingle(name);
		return weapon.getId();
	}
	
	public void delete(Long id,String name) {
		this.service.delete(id,name);
	}
	
	public WeaponOriginDTO update(Long id, TotalEntity fullWeapon) {
		WeaponOrigin weapon = new WeaponOrigin();
		weapon.setCreator(fullWeapon.getCreator());
		weapon.setName(fullWeapon.getName());
		weapon.setOrigin(fullWeapon.getOrigin());
		
		WeaponOriginDTO newWeapon = service.updateOrigin(id, weapon);
		return newWeapon;
	}
	
	public WeaponOriginDTO getLatest() {
		WeaponOriginDTO weapon = this.service.getLatest();
		return weapon;
	}
}
