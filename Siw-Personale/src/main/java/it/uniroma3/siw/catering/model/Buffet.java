package it.uniroma3.siw.catering.model;

import java.util.ArrayList;

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
public class Buffet {
	/*       	VARIABILI D'ISTANZA			*/
	@Id    
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String descrizione;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private Chef chef;
	@OneToMany(mappedBy = "buffet", fetch = FetchType.EAGER)
	protected List<Piatto> piatti;
	
	/*				COSTRUTTORI				*/
	public Buffet() {
		// TODO Auto-generated constructor stub
		this(null,null);
	}
	public Buffet(String nome) {
		this(nome, null);
	}
	public Buffet(String nome, Chef chef) {
		this.nome=nome;
		this.chef=chef;
		this.piatti=new ArrayList<>();
	}
	
	/*			GETTERS AND SETTERS*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Chef getChef() {
		return chef;
	}
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
				
	public Long getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public List<Piatto> getPiatti() {
		return piatti;
	}
	
	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public void addPiatto(Piatto piatto) {
		this.piatti.add(piatto);
	}
	
	public void removePiatto(Piatto piatto) {
		this.piatti.remove(piatto);
	}
	
}
