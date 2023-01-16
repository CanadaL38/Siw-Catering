package it.uniroma3.siw.catering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	@Autowired
	PiattoRepository pr;

	public void save(Piatto piatto) {
		pr.save(piatto);
	}
}
