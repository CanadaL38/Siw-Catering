package it.uniroma3.siw.catering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository br;

	public void save(Buffet buffet) {
		br.save(buffet);
	}
}
