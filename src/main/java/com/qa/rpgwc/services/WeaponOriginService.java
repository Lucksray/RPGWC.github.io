package com.qa.rpgwc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.dtos.WeaponOriginDTO;
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
	public WeaponOriginDTO updateOrigin(Long statId, WeaponOrigin weapon) {
		Optional<WeaponOrigin> existingOptional = this.repo.findRelateId(statId);
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
	
	public WeaponOrigin getSingle(String name) {
		List<WeaponOrigin> weapons = this.repo.findByName(name);
		if(weapons.size() < 2 ) {
			WeaponOrigin weapon = weapons.get(0);
			return weapon;
		} else if (weapons.size() > 1) {
			System.out.println("Too many arguments");
		}
		return null;
	}
	
	public List<WeaponOrigin> readByName(String name){
		return this.repo.findByName(name);
	}
	
	public WeaponOriginDTO getLatest() {
		WeaponOrigin saved =  this.repo.getLatest();
		return this.mapToDTO(saved);
	}
	
	public void delete(Long id, String name) {
		this.repo.deleteRelate(id,name);
	}
}
