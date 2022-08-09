package com.qa.rpgwc.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalEntity {
	
	private String name;
	private String material;
	private int materialAmount;
	private String creator;
	private String origin;
	private String classType;
	private String subClassType;
		
}
