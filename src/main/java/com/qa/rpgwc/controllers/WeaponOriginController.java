package com.qa.rpgwc.controllers;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.dtos.WeaponOriginDTO;
import com.qa.rpgwc.services.WeaponOriginService;

@Service
@RestController
public class WeaponOriginController {

	private WeaponOriginService service;
	
	public WeaponOriginController(WeaponOriginService service) {
		super();
		this.service = service;
	}
	
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
}
