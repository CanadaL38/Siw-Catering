package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;
import it.uniroma3.siw.catering.validator.PiattoValidator;

public class PiattoController {
	@Autowired
	private PiattoService ps;
	@Autowired
	private BuffetService bs;
	@Autowired
	private IngredienteService is;
	@Autowired
	private PiattoValidator pv;

	/*
	 * metodo che mostra la pagina per scegliere il buffet del quale si vogliono
	 * gestire i piatti
	 */
	@GetMapping("/admin/gestione_piatto")
	public String getBuffetChoose(Model model) {
		model.addAttribute("buffets", this.bs.findAll());
		return "/admin/buffet_choose.html";
	}

	/*
	 * metodo che mostra i piatti di un determinato buffet il quale id e' passato
	 * come parametro
	 */
	@GetMapping("/admin/gestione_piatto/{id}")
	public String getPiattoManagment(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.bs.findById(id);
		model.addAttribute("piatti", this.ps.findAllByBuffet(buffet));
		model.addAttribute("buffet", buffet);
		return "/admin/Piatto/PiattoManagment.html";
	}

	// =======================================================
	// ****CREAZIONE NUOVO PIATTO****//

	/* metodo per accedere alla form per creare nuovo piatto */
	@GetMapping("/admin/gestione_piatto/add/{id}")
	public String createPiatto(@PathVariable("id") Long id, Model model) {
		Piatto nuovoPiatto = new Piatto();
		model.addAttribute("piatto", nuovoPiatto);
		model.addAttribute("buffet_id", id);
		model.addAttribute("ingredienti", this.is.findAll());
		return "/admin/Piatto/PiattoForm.html";
	}

	/* metodo che inserisce il piatto nella base di dati */
	@PostMapping("/admin/gestione_piatto/{id}")
	public String addPiatto(@PathVariable("id") Long id, @Valid @ModelAttribute("piatto") Piatto piatto,
			BindingResult bindingResults, Model model) {
		Buffet buffet = bs.findById(id);
		buffet.addPiatto(piatto);
		piatto.setBuffet(buffet);
		this.pv.validate(piatto, bindingResults);
		if (!bindingResults.hasErrors()) {
			ps.save(piatto);
			model.addAttribute("piatto", this.ps.findAllByBuffet(buffet));
			model.addAttribute("buffet_id", id);
			return "redirect:/admin/gestione_piatto/" + id;
		} else {
			model.addAttribute("tuttiGliIngredienti", is.findAll());
			return "/admin/gestione_piatto.html";
		}
	}

	// ============================================================
	// ****CANCELLAZIONE PIATTO****//

	/* metodo che elimina un determinato piatto dalla base di dati */
	@GetMapping("/admin/cancellaPiattoDaBuffet/{id1}/{id2}")
	  public String removePiattoDaBuffet(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
		  bs.findById(id2).getPiatti().remove(ps.findById(id1));
		  bs.addBuffet(bs.findById(id2));
		  return "redirect:/admin/indexAdmin";
	  }

	/* ============================================================ */
	// ****ACCESSO ALLE INFORMAZIONI****//

	/* metodo che mostra i piatti di un determinato buffet */
	@GetMapping("/user/all_buffets/buffet/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", this.ps.findById(id));
		model.addAttribute("buffet_id", this.ps.findById(id).getBuffet().getId());
		return "/user/piatto.html";
	}
}
