package com.qa.rpgwc.controllers;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponClass;
import com.qa.rpgwc.dtos.WeaponClassDTO;
import com.qa.rpgwc.dtos.WeaponStatDTO;
import com.qa.rpgwc.services.WeaponClassService;

@Service
@RestController
public class WeaponClassController {
	
	private WeaponClassService service;
	
	public WeaponClassController(WeaponClassService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/class/getAll")
	public List<WeaponClassDTO> getAll(){
		return service.getAllClasses();
	}
	
	public void create(TotalEntity fullWeapon) {
		WeaponClass weapon = new WeaponClass();
		weapon.setName(fullWeapon.getName());
		weapon.setClassType(fullWeapon.getClassType());
		weapon.setSubClassType(fullWeapon.getSubClassType());
		
		service.addClass(weapon);
	}
	
	public void delete(Long id,String name) {
		this.service.delete(id,name);
	}
	
	public WeaponClassDTO update(Long id, TotalEntity fullWeapon) {
		WeaponClass weapon = new WeaponClass();
		weapon.setName(fullWeapon.getName());
		
		WeaponClassDTO newWeapon = service.updateClass(id, weapon);
		return newWeapon;
	}
	
	public WeaponClassDTO getLatest() {
		WeaponClassDTO weapon = this.service.getLatest();
		return weapon;
	}
}
