package it.uniroma3.siw.catering.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
@Entity
public class Ingrediente {
	/*       	VARIABILI D'ISTANZA			*/
	@Id    
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String origine;

	private String descrizione;
	
	@ManyToOne
	private Piatto piatto;
	
	
	
	
	/*				COSTRUTTORI				*/
	public Ingrediente() {
		// TODO Auto-generated constructor stub
		this(null,null,null,null,null);
	}

	public Ingrediente(String nome, Piatto piatto, String origine, Long calorie) {
		this(nome,null,piatto,origine,calorie);
	}
	
	public Ingrediente(String nome,String descrizione, Piatto piatto, String origine, Long calorie) {
		this.nome=nome;
		this.descrizione=descrizione;
		this.piatto=piatto;
		this.origine=origine;
		
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

	public Piatto getPiatto() {
		return piatto;
	}

	public void setPiatto(Piatto piatto) {
		this.piatto = piatto;
	}

	
	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}
	
	
	
	
}
