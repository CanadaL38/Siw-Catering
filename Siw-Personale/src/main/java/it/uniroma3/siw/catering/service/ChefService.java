package it.uniroma3.siw.catering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	ChefRepository cr;

	public void save(Chef chef) {
		cr.save(chef);
	}
}
