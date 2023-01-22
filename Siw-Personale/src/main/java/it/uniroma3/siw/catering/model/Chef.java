package it.uniroma3.siw.catering.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Chef {
	/* VARIABILI D'ISTANZA */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String cognome;

	@NotBlank
	private String nazionalità;

	@OneToMany(mappedBy = "chef")
	private List<Buffet> buffets;

	/* COSTRUTTORI */
	public Chef() {
		// TODO Auto-generated constructor stub
		this(null, null, null);
	}

	public Chef(String nome, String cognome) {
		this(nome, cognome, null);
	}

	public Chef(String nome, String cognome, String nazionalità) {
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalità = nazionalità;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalità() {
		return nazionalità;
	}

	public void setNazionalità(String nazionalità) {
		this.nazionalità = nazionalità;
	}

	public List<Buffet> getBuffets() {
		return buffets;
	}

	public void setBuffets(List<Buffet> buffets) {
		this.buffets = buffets;
	}

	public void addBuffet(Buffet buffet) {
		this.buffets.add(buffet);
	}
}
