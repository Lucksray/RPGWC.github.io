package com.qa.rpgwc.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponOriginDTO implements DTO{
	
	private String name;
	private String creator;
	private String origin;
}
