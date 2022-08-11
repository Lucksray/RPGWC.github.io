package com.qa.rpgwc.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rpgwc.Entities.TotalEntity;
import com.qa.rpgwc.Entities.WeaponOrigin;

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
	
	public List<WeaponOrigin> readByName(String name){
		return originController.readByName(name);
	} 
	
	@PostMapping("/create")
	public TotalEntity create(@RequestBody TotalEntity request) {
		TotalEntity fullWeapon = new TotalEntity();
		fullWeapon.setName(request.getName()); fullWeapon.setMaterial(request.getMaterial());
		fullWeapon.setMaterialAmount(request.getMaterialAmount()); fullWeapon.setCreator(request.getCreator());
		fullWeapon.setOrigin(request.getOrigin()); fullWeapon.setClassType(request.getClassType());
		fullWeapon.setSubClassType(request.getSubClassType());
		
		statController.create(fullWeapon);
		classController.create(fullWeapon);
		originController.create(fullWeapon);
		
		return fullWeapon;
	}
	
	@PostMapping("/delete.html")
	public String delete(@PathParam("id") Long id,@PathParam("name") String name) {
		if(originController.testSingle(name) && id == null) {
			id = originController.getSingleId(name);
			originController.delete(id,name);
			classController.delete(id,name);
			statController.delete(id,name);
			return "Your weapon has been deleted";
		} else if (originController.testSingle(name) && id != null) {
			originController.delete(id,  name);
			classController.delete(id,  name);
			statController.delete(id,  name);
			return "Your weapon has been deleted";
		} else {
			return "We couldn't find your weapon...";
		}
	}
	
	@PostMapping("/update.html")
	public TotalEntity update(@PathParam("id") Long id,@PathParam("name") String name,@RequestBody TotalEntity fullWeapon) {
		if(originController.testSingle(name) && id == null) {
			id = originController.getSingleId(name);
			originController.update(id,fullWeapon);
			classController.update(id,fullWeapon);
			statController.update(id,fullWeapon);
			return fullWeapon;
		} else if (originController.testSingle(name) && id != null ) {
			originController.update(id,fullWeapon);
			classController.update(id,fullWeapon);
			statController.update(id,fullWeapon);
			return fullWeapon;
		} else {
			return null;
		}
	}
	
	
}
