package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository cr;

	@Transactional
	public void save(Chef chef) {
		cr.save(chef);
	}
	public Chef findById(Long id) {
		return cr.findById(id).get();
	}
	public List<Chef> findAll(){
		List<Chef> chefs = new ArrayList<>();
		for(Chef c : cr.findAll()) {
			chefs.add(c);
		}
		return chefs;
	}
	public boolean alreadyExists(Chef chef) {
		return cr.existsByNomeAndCognome(chef.getNome(), chef.getCognome());
	}
}
