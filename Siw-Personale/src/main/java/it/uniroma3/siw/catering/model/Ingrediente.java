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
	private long calorie;
	
	private String descrizione;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private Piatto piatto;
	
	
	/*				COSTRUTTORI				*/
	public Ingrediente() {
		// TODO Auto-generated constructor stub
		this(null,null,null,null);
	}

	public Ingrediente(String nome, Integer calorie, Piatto piatto) {
		this(nome,calorie,null,piatto);
	}
	
	public Ingrediente(String nome, Integer calorie, String descrizione, Piatto piatto) {
		this.nome=nome;
		this.calorie=calorie;
		this.descrizione=descrizione;
		this.piatto=piatto;
		
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

	public long getCalorie() {
		return calorie;
	}

	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
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

	
	
	
}
