package it.uniroma3.siw.catering.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.BuffetRepository;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.validator.BuffetValidator;


@Controller
public class BuffetController {
	@Autowired
	private BuffetService bs;
	@Autowired
	private ChefService cs;
	@Autowired
	private IngredienteService is;
	@Autowired
	private BuffetValidator bv;
	@Autowired
	private BuffetRepository br;
	/* Sezione USER */

	/* Visualizza un buffet */
	@GetMapping("/user/all_Chefs/chef/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet", buffet);
		return "user/buffet.html";
	}
	@GetMapping("/user/all_Buffets")
	public String getChefs(Model model) {
		model.addAttribute("buffets", bs.findAll());
		return "user/all_Buffets.html";
	}

	/* SEZIONE ADMIN */
	/* Accesso alla gestione del buffet */
	@GetMapping("/admin/GestioneBuffet")
	public String getGestioneBuffet(Model model) {
		model.addAttribute("buffets", this.bs.findAll());
		return "admin/Buffet/GestioneBuffet.html";
	}
	
	/* Accesso alla form per la creazione di un buffet */
	@GetMapping("/admin/GestioneBuffet/add/{id}")
	public String getBuffetForm(@PathVariable("id") Long chef_id, Model model) {
		Buffet nuovoBuffet = new Buffet();
		model.addAttribute("buffet", nuovoBuffet);
		model.addAttribute("chef_id", chef_id);
		return "admin/Buffet/BuffetForm.html";
	}

	/* Aggiunta buffet db */
	@PostMapping("/admin/GestioneBuffet/add/{chef_id}")
	public String addBuffet(@PathVariable("chef_id") Long id, @Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult bindingResults, Model model) {
		    bv.validate(buffet, bindingResults);
		if (!bindingResults.hasErrors()) {
			Chef chef = this.cs.findById(id);
			chef.addBuffet(buffet);
			buffet.setChef(chef);
			this.bs.save(buffet);
			model.addAttribute("buffet", buffet);
			model.addAttribute("buffets", this.bs.findAll());
			return "admin/Buffet/GestioneBuffet";
		}
		return "admin/Buffet/BuffetForm.html";
	}
	@GetMapping("/admin/GestioneBuffet/delete/{id}")
	  public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		 Buffet buffet=bs.findById(id);
		 bs.deleteBuffet(buffet.getId());
		 model.addAttribute("buffets", this.bs.findAll());
		 return "admin/Buffet/GestioneBuffet";
	  }
	
}
