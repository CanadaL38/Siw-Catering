package it.uniroma3.siw.catering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	IngredienteRepository ir;

	public void save(Ingrediente ingrediente) {
		ir.save(ingrediente);
	}
}
