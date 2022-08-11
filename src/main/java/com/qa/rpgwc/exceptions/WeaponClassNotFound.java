package com.qa.rpgwc.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Specific class not found")
public class WeaponClassNotFound extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4212992706218584825L;

}
