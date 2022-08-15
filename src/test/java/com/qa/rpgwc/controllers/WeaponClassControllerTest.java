package com.qa.rpgwc.controllers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.rpgwc.Entities.WeaponClass;
import com.qa.rpgwc.dtos.WeaponClassDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class WeaponClassControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private WeaponClassDTO mapToDTO(WeaponClass weapon) {
		return mapper.map(weapon,  WeaponClassDTO.class);
	}
	
	private final Long TEST_ID = 4L;
	private final WeaponClass TEST_ACCOUNT = new WeaponClass(null,"Bealdug","Sword","Broadsword");
	
	@Test
	public void create() {
		
		WeaponClass expected = TEST_ACCOUNT;
		expected.setId(TEST_ID);
		
		try {
			mock.perform(post("/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(TEST_ACCOUNT)))
				.andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete_Test() {
		String name = TEST_ACCOUNT.getName();
		
		try {
			doNothing();
		}
	}
	
}
