package com.qa.rpgwc.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Weapon does not exist")
public class WeaponStatNotFound extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8468677263668400891L;
}
