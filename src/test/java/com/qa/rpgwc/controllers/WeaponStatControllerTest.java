package com.qa.rpgwc.controllers;

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
import com.qa.rpgwc.Entities.WeaponStat;
import com.qa.rpgwc.dtos.WeaponStatDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class WeaponStatControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private WeaponStatDTO mapToDTO(WeaponStat weapon) {
		return mapper.map(weapon,  WeaponStatDTO.class);
	}
	
	private final Long TEST_ID = 4L;
	private final WeaponStat TEST_ACCOUNT = new WeaponStat(null,"Bealdug","mithril",7);
	
	@Test
	public void create() {
		
		WeaponStat expected = TEST_ACCOUNT;
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
}
