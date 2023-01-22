package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository br;
	
	@Transactional
	public void save(Buffet buffet) {
		br.save(buffet);
	}
	@Transactional
	public void addBuffet(Buffet b) {
		br.save(b);
		b.getChef().addBuffet(b);
	}
	public Buffet findById(Long id) {
		return br.findById(id).get();
	}
	public List<Buffet> findAll(){
		List<Buffet> buffets = new ArrayList<Buffet>();
		for(Buffet b : br.findAll()) {
			buffets.add(b);
		}
		return buffets;
	}
	public List<Buffet> findAllByChef(Chef chef) {
		List<Buffet> buffets = new ArrayList<>();
		for(Buffet b: br.findByChef(chef)) {
			buffets.add(b);
		}
		return buffets;
	}
	public void updateBuffet(Buffet buffet) {
		this.addBuffet(buffet);
	}
	public boolean alreadyExists(Buffet b) {
		return br.existsByNomeAndDescrizioneAndChef(b.getNome(), b.getDescrizione(), b.getChef());
	}
	
	
}
