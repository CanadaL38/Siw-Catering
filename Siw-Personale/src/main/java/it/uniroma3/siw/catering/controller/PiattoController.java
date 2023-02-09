package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
public class PiattoController {
	@Autowired
	private PiattoService ps;
	@Autowired
	private BuffetService bs;
	@Autowired
	private IngredienteService is;
	@Autowired
	private PiattoValidator pv;

	// =======================================================
	// ****CREAZIONE NUOVO PIATTO****//

	/* metodo per accedere alla form per creare nuovo piatto */

	@GetMapping("/admin/GestionePiatto/add/{buffet_id}")
	public String createPiattoForm(@PathVariable Long buffet_id, Model model) {
		Piatto nuovoPiatto = new Piatto();
		model.addAttribute("piatto", nuovoPiatto);
		model.addAttribute("buffet_id", buffet_id);
		model.addAttribute("ingredienti", this.is.findAll());
		return "admin/Piatto/PiattoForm.html";
	}

	@PostMapping("/admin/GestionePiatto/add/{buffet_id}")
	public String addPiatto(@PathVariable("buffet_id") Long id, @Valid @ModelAttribute("piatto") Piatto piatto,
			BindingResult bindingResults, Model model) {
		Buffet buffet = bs.findById(id);
		buffet.addPiatto(piatto);
		piatto.setBuffet(buffet);
		this.pv.validate(piatto, bindingResults);
		if (!bindingResults.hasErrors()) {
			ps.save(piatto);
			model.addAttribute("piatto", this.ps.findAllByBuffet(buffet));
			model.addAttribute("buffet_id", id);
			model.addAttribute("buffets", this.bs.findAll());
			return "admin/Buffet/GestioneBuffet";
		} else {

			return "admin/Piatto/PiattoForm.html";
		}
	}

	@GetMapping("/admin/GestionePiatto/{buffet_id}")
	public String getPiattoManagment(@PathVariable Long buffet_id, Model model) {
		Buffet buffet = this.bs.findById(buffet_id);
		model.addAttribute("piatti", this.ps.findAllByBuffet(buffet));
		model.addAttribute("buffet", buffet);
		return "admin/Piatto/GestionePiatto";
	}

	@GetMapping("/admin/GestionePiatto/delete/{buffet_id}")
	public String deletePiatto(@PathVariable("buffet_id") Long id, Model model) {
		Long buffet_id = this.ps.findById(id).getBuffet().getId();
		this.ps.deletePiatto(id);
		return "admin/GestionePiatto/" + buffet_id;
	}
	@GetMapping("/user/all_Chefs/chef/buffet/piatto/{piatto_id}")
	public String getPiatto(@PathVariable Long piatto_id, Model model) {
		Piatto piatto = this.ps.findById(piatto_id);
		model.addAttribute("piatto", piatto);
		model.addAttribute("buffet_id", piatto_id);
		return "user/piatto.html";
	}

}