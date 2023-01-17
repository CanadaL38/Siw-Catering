package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.IngredienteRepository;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	@Autowired
	PiattoRepository pr;
	@Autowired
	IngredienteRepository ir;
	@Transactional
	public void save(Piatto piatto) {
		pr.save(piatto);
	}
	public Piatto findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Piatto> findAllByBuffet(Buffet buffet) {
		List<Piatto> piatti = new ArrayList<>();
		for(Piatto p : pr.findByBuffet(buffet)) {
			piatti.add(p);
		}
		return piatti;
	}
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList<>();
		for(Piatto p : pr.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}
	
	public boolean alredyExists(Piatto piatto) {
		return this.pr.existsByNomeAndBuffet(piatto.getNome(), piatto.getBuffet());
	}
}
