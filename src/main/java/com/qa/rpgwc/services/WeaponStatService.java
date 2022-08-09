package com.qa.rpgwc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.rpgwc.Entities.WeaponStat;
import com.qa.rpgwc.dtos.WeaponStatDTO;
import com.qa.rpgwc.exceptions.WeaponStatNotFound;
import com.qa.rpgwc.repo.WeaponStatRepo;

@Service
public class WeaponStatService {

	private WeaponStatRepo repo;
	private ModelMapper mapper;
	
	public WeaponStatService(WeaponStatRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private WeaponStatDTO mapToDTO(WeaponStat weaponStat) {
		return this.mapper.map(weaponStat, WeaponStatDTO.class);
	}
	
	public WeaponStatDTO addWeaponStat(WeaponStat weapon) {
		WeaponStat saved = this.repo.save(weapon);
		return this.mapToDTO(saved);
	}
	
	public List<WeaponStatDTO> getAllWeaponStats(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public WeaponStatDTO updateWeaponStat(Long id, WeaponStat weapon) {
		Optional<WeaponStat> existingOptional = this.repo.findById(id);
		WeaponStat existing = existingOptional.orElse(new WeaponStat());
		
		existing.setName(weapon.getName());
		existing.setMaterial(weapon.getMaterial());
		existing.setMaterialAmount(weapon.getMaterialAmount());
		
		WeaponStat saved = this.repo.save(existing);
		return this.mapToDTO(saved);
	}
	
	public boolean removeWeaponStat(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		if ( exists == false) {
			throw new WeaponStatNotFound();
		}
		return !exists;
	}
	
	public WeaponStatDTO getById(Long id) {
		WeaponStat saved = this.repo.findById(id).orElseThrow(WeaponStatNotFound::new);
		return this.mapToDTO(saved);
	}
	
	public List<WeaponStatDTO> getByName(String name){
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public WeaponStatDTO getLatest() {
		WeaponStat saved = this.repo.getLatest();
		return this.mapToDTO(saved);
	}
}
