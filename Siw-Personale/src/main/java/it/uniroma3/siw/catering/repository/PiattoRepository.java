package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;



public interface PiattoRepository extends CrudRepository<Piatto, Long>{

	List<Piatto> findByBuffet(Buffet buffet);

	boolean existsByNomeAndBuffet(String nome, Buffet buffet);

	List<Piatto> findByIngredienti(Ingrediente i);

}
