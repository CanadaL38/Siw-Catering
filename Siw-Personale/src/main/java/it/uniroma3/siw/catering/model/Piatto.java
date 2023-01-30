package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
@Entity
public class Piatto {
	/*       	VARIABILI D'ISTANZA			*/
	@Id    
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String descrizione;
	
	
	@ManyToOne
	private Buffet buffet;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	/*				COSTRUTTORI				*/
	
	public Piatto() {
		this(null,null,null);
	}
	public Piatto(String nome, Buffet buffet) {
		this(nome,null,buffet);
	}

	public Piatto(String nome, String descrizione, Buffet buffet) {
		this.nome=nome;
		this.descrizione=descrizione;
		this.buffet=buffet;
		this.ingredienti=new ArrayList<>();
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Buffet getBuffet() {
		return buffet;
	}

	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		this.ingredienti.add(ingrediente);
	}
	
	public void removeIngrediente(Ingrediente ingrediente) {
		this.ingredienti.remove(ingrediente);
	}
	
	
}
