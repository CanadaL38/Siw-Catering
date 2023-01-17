package it.uniroma3.siw.catering.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.service.BuffetService;

public class BuffetValidator implements Validator{
	private BuffetService bs;
	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.bs.alreadyExists((Buffet)o)){
			errors.reject("buffet.duplicato");
			}
		Buffet b = (Buffet)o;
		if(b.getPiatti() == null || b.getPiatti().size() < 2) {
			errors.reject("buffet.vuoto");
		}
		
		if(b.getChef()==null) {
			errors.reject("buffet.chef.null");
		}
	}

}
