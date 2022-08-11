package com.qa.rpgwc.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeaponClassDTO implements DTO {

	private String name;
	private String type;
	private String subType;
}
