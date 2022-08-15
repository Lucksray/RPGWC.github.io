package com.qa.rpgwc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.rpgwc.Entities.WeaponOrigin;
import com.qa.rpgwc.services.WeaponClassService;
import com.qa.rpgwc.services.WeaponOriginService;
import com.qa.rpgwc.services.WeaponStatService;

@RunWith(MockitoJUnitRunner.class)
public class OverwatchControllerTest {
	
	@Mock
	private WeaponClassService classService;
	
	@Mock
	private WeaponOriginService originService;
	
	@Mock
	private WeaponStatService statService;

	@Mock
	private WeaponClassController classController;
	
	@Mock
	private WeaponOriginController originController = new WeaponOriginController(originService);
	
	@Mock
	private WeaponStatController statController;
	
	@InjectMocks
	private OverwatchController controller;
	
	@Test
	public void readByName_Test() {
		String name = "Bealdung";
		List<WeaponOrigin> origins = new ArrayList<>();
		origins.add(new WeaponOrigin(1L,"Bealdung","James","Unknown"));
		origins.add(new WeaponOrigin(2L,"Bealdung","Heracles","Battle Sword"));
		origins.add(new WeaponOrigin(3L,"Bealdung","DwarfSmith38","Forged in a star"));
		
		Mockito.when(originController.readByName(name)).thenReturn(origins);
		
		Assertions.assertEquals(origins, controller.readByName(name));
		
		Mockito.verify(originController, Mockito.times(1)).readByName(name);
		
	}
}
