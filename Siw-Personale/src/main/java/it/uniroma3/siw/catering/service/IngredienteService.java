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
public class IngredienteService {
	@Autowired
	private PiattoRepository pr;
	@Autowired
	private IngredienteRepository ir;

	@Transactional
	public void save(Ingrediente ingrediente) {
		ir.save(ingrediente);
	}
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti = new ArrayList<>();
		for(Ingrediente i: ir.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	public List<Ingrediente> findAllbyPiatto(Piatto p){
		List<Ingrediente> ingredienti=new ArrayList<>();
		for(Ingrediente i: ir.findByPiatto(p)) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	public boolean alreadyExists(Ingrediente ingrediente) {
		return ir.existsByNomeAndDescrizioneAndCalorie(ingrediente.getNome(), ingrediente.getDescrizione(), ingrediente.getCalorie());
	}
	
}
