package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{

	public List<Ingrediente> findByPiatto(Piatto p);
	public boolean existsByNomeAndOrigine(String nome, String origine);


}
