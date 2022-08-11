package com.qa.rpgwc.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponStatDTO{
	
	private String name;
	private String material;
	private int materialAmount;
}
