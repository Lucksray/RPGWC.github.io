package com.qa.rpgwc.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponStat;
import com.qa.rpgwc.dtos.DTO;

@Service
@RestController
public class OverwatchController {

	private WeaponClassController classController;
	private WeaponOriginController originController;
	private WeaponStatController statController;
	
	public OverwatchController(WeaponClassController classController,WeaponOriginController originController,WeaponStatController statController) {
		super();
		this.classController = classController;
		this.originController = originController;
		this.statController = statController;
	}
	
	@GetMapping("/home.html")
	public String greeting() {
		return "<p>Welcome to the home page</p>";
	}
	
	@PostMapping("/read.html")
	public List<DTO> readAll(@PathParam("controller") String controller) {
		if(controller == "Type") {
			return classController.getAll();
		} else if (controller == "Origin") {
			return originController.getAll();
		} else if (controller == "Stat") {
			return statController.getAll();
		}
	}
	
	public List<WeaponStat> readByName(String name){
		return originController.readByName(name);
	}
	
	@PostMapping("/create.html")
	public TotalEntity create(@PathParam("name") String name,@PathParam("material") String material,@PathParam("materialAmount") int materialAmount,@PathParam("creator") String creator,@PathParam("origin") String origin,@PathParam("classType") String classType,@PathParam("subClassType") String subClassType) {
		TotalEntity fullWeapon = new TotalEntity();
		fullWeapon.setName(name); fullWeapon.setMaterial(material);
		fullWeapon.setMaterialAmount(materialAmount); fullWeapon.setCreator(creator);
		fullWeapon.setOrigin(origin); fullWeapon.setClassType(subClassType);
		fullWeapon.setSubClassType(subClassType);
		
		statController.create(fullWeapon);
		classController.create(fullWeapon);
		originController.create(fullWeapon);
		
		return fullWeapon;
	}
	
	@PostMapping("/delete.html")
	public boolean delete(@PathParam("id") Long id,@PathParam("name") String name) {
		
	}
	
	
}
