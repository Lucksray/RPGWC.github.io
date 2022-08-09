package com.qa.rpgwc.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponStatDTO implements DTO {
	
	private String name;
	private String material;
}
