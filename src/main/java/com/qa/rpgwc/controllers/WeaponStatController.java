package com.qa.rpgwc.controllers;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.Entities.WeaponStat;
import com.qa.rpgwc.dtos.WeaponOriginDTO;
import com.qa.rpgwc.dtos.WeaponStatDTO;
import com.qa.rpgwc.services.WeaponStatService;

@Service
@RestController
public class WeaponStatController {

	private WeaponStatService service;
	
	public WeaponStatController(WeaponStatService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/stat/getAll")
	public List<WeaponStatDTO> getAll(){
		return service.getAllWeaponStats();
	}
	
	public void create(TotalEntity fullWeapon) {
		WeaponStat weapon = new WeaponStat();
		weapon.setName(fullWeapon.getName());
		weapon.setMaterial(fullWeapon.getMaterial());
		weapon.setMaterialAmount(fullWeapon.getMaterialAmount());
		
		service.addWeaponStat(weapon);
	}
	
	public void delete(Long id,String name) {
		this.service.delete(id,name);
	}
	
	public WeaponStatDTO update(Long id, TotalEntity fullWeapon) {
		WeaponStat weapon = new WeaponStat();
		weapon.setName(fullWeapon.getName());
		weapon.setMaterial(fullWeapon.getMaterial());
		weapon.setMaterialAmount(fullWeapon.getMaterialAmount());
		
		WeaponStatDTO newWeapon = service.updateWeaponStat(id, weapon);
		return newWeapon;
	}
	
	public WeaponStatDTO getLatest() {
		WeaponStatDTO weapon = this.service.getLatest();
		return weapon;
	}
}
