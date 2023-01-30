package it.uniroma3.siw.catering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.service.BuffetService;
@Component
public class BuffetValidator implements Validator {
	@Autowired
	private BuffetService bs;

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.bs.alreadyExists((Buffet) o)) {
			errors.reject("buffet.duplicato");
		}
	}

}
