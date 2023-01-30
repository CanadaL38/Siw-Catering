package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository cr;
	@Autowired
	private BuffetService bs;

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
	@Transactional
	public void aggiungiChef(Chef c) {
		cr.save(c);
	}
	public boolean alreadyExists(Chef chef) {
		return cr.existsByNomeAndCognome(chef.getNome(), chef.getCognome());
	}
	public void editChef(Chef nuovoChef) {
		 this.save(nuovoChef);
	}
	public void deleteChefById(Long chef_id) {
		List<Buffet> buffets = this.bs.findAllByChef(this.findById(chef_id));
		for(Buffet b: buffets)
			bs.deleteBuffet(b.getId());
		
		cr.deleteById(chef_id);
		
	}
}
