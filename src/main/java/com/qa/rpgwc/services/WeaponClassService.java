package com.qa.rpgwc.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.qa.rpgwc.Entities.WeaponClass;
import com.qa.rpgwc.Entities.WeaponClassDTO;
import com.qa.rpgwc.exceptions.WeaponClassNotFound;
import com.qa.rpgwc.repo.WeaponClassRepo;

public class WeaponClassService {

	private WeaponClassRepo repo;
	private ModelMapper mapper;
	
	public WeaponClassService(WeaponClassRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private WeaponClassDTO mapToDTO(WeaponClass weapon) {
		return this.mapper.map(weapon,  WeaponClassDTO.class);
	}
	
	public WeaponClassDTO addClass(WeaponClass weapon) {
		WeaponClass saved = this.repo.save(weapon);
		return this.mapToDTO(saved);
	}
	
	public List<WeaponClassDTO> getAllClasses(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//Fix Update, doesn't recognise null ExistingOptional
	public WeaponClassDTO updateAccount(Long id, WeaponClass weapon) {
		Optional<WeaponClass> existingOptional = this.repo.findById(id);
		WeaponClass existing = existingOptional.orElse(new WeaponClass());
		
		existing.setName(weapon.getName());
		existing.setClassType(weapon.getClassType());
		existing.setSubClassType(weapon.getSubClassType());
		
		WeaponClass saved = this.repo.save(existing);
		return this.mapToDTO(saved);
	}
	
	public boolean removeWeapon(long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		if (exists == false) {
			throw new WeaponClassNotFound();
		}
		return !exists;
	}
	
	public WeaponClassDTO getById(Long id) {
		WeaponClass saved = this.repo.findById(id).orElseThrow(WeaponClassNotFound::new);
		return this.mapToDTO(saved);
	}
	
	public List<WeaponClassDTO> getByName(String name) {
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public WeaponClassDTO getLatest() {
		WeaponClass saved =  this.repo.getLatest();
		return this.mapToDTO(saved);
	}
}
