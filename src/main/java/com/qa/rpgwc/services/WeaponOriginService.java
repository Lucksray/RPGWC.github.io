package com.qa.rpgwc.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.Entities.WeaponOriginDTO;
import com.qa.rpgwc.exceptions.WeaponOriginNotFound;
import com.qa.rpgwc.repo.WeaponOriginRepo;

@Service
public class WeaponOriginService {

	private WeaponOriginRepo repo;
	private ModelMapper mapper;
	
	public WeaponOriginService(WeaponOriginRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private WeaponOriginDTO mapToDTO(WeaponOrigin weapon) {
		return this.mapper.map(weapon,  WeaponOriginDTO.class);
	}
	
	public WeaponOriginDTO addOrigin(WeaponOrigin weapon) {
		WeaponOrigin saved = this.repo.save(weapon);
		return this.mapToDTO(saved);
	}
	
	public List<WeaponOriginDTO> getAllOrigins(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//Fix Update, doesn't recognise null ExistingOptional
	public WeaponOriginDTO updateOrigin(Long id, WeaponOrigin weapon) {
		Optional<WeaponOrigin> existingOptional = this.repo.findById(id);
		WeaponOrigin existing = existingOptional.orElse(new WeaponOrigin());
		
		existing.setName(weapon.getName());
		existing.setCreator(weapon.getCreator());
		existing.setOrigin(weapon.getOrigin());
		
		WeaponOrigin saved = this.repo.save(existing);
		return this.mapToDTO(saved);
	}
	
	public boolean removeOrigin(long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		if (exists == false) {
			throw new WeaponOriginNotFound();
		}
		return !exists;
	}
	
	public WeaponOriginDTO getById(Long id) {
		WeaponOrigin saved = this.repo.findById(id).orElseThrow(WeaponOriginNotFound::new);
		return this.mapToDTO(saved);
	}
	
	public List<WeaponOriginDTO> getByName(String name) {
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public WeaponOriginDTO getLatest() {
		WeaponOrigin saved =  this.repo.getLatest();
		return this.mapToDTO(saved);
	}
}
