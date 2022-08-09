package com.qa.rpgwc.controllers;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponClass;
import com.qa.rpgwc.dtos.WeaponClassDTO;
import com.qa.rpgwc.services.WeaponClassService;

@Service
@RestController
public class WeaponClassController {
	
	private WeaponClassService service;
	
	public WeaponClassController(WeaponClassService service) {
		super();
		this.service = service;
	}
	
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
}
