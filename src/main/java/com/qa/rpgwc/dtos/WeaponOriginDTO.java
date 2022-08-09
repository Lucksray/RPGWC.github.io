package com.qa.rpgwc.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponOriginDTO {
	
	private String name;
	private String creator;
	private String origin;
}
