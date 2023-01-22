package it.uniroma3.siw.catering.controller;

import java.util.List;

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
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.BuffetValidator;

@Controller
public class BuffetController {
	@Autowired
	private BuffetService bs;
	@Autowired
	private ChefService cs;
	@Autowired
	private BuffetValidator bv;
	/* Sezione USER */

	/* Visualizza un buffet */
	@GetMapping("/user/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet", buffet);
		return "/user/buffet.html";
	}

	/* Visualizza tutti i buffet */
	@GetMapping("/user/all_Buffets")
	public String getBuffets(Model model) {
		List<Buffet> buffets = bs.findAll();
		model.addAttribute(buffets);
		return "/user/all_Buffets.html";
	}

	/* SEZIONE ADMIN */
	/* Accesso alla gestione del buffet */
	@GetMapping("/admin/GestioneBuffet")
	public String getGestioneBuffet(Model model) {
		model.addAttribute("buffets", this.bs.findAll());
		return "/admin/Buffet/GestioneBuffet.html";
	}

	/* Accesso alla form per la creazione di un buffet */
	@GetMapping("/admin/buffet_managment/add/id")
	public String getBuffetForm(@PathVariable("id") Long chef_id, Model model) {
		Buffet nuovoBuffet = new Buffet();
		model.addAttribute("buffet", nuovoBuffet);
		model.addAttribute("chef_id", chef_id);
		return "/admin/Buffet/buffetForm.html";
	}

	/* Aggiunta buffet db */
	@PostMapping("/admin/GestioneBuffet/add/{id}")
	public String addBuffet(@PathVariable("id") Long id, @Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult bindingResults, Model model) {
		bv.validate(buffet, bindingResults);
		if (!bindingResults.hasErrors()) {
			Chef chef = this.cs.findById(id);
			chef.addBuffet(buffet);
			buffet.setChef(chef);
			this.bs.save(buffet);
			model.addAttribute("buffet", model);
			return "redirect:/admin/Buffet/buffetManagment";
		}
		return "/admin/Buffet/buffetForm.html";
	}

	/* Visualizza tutti i buffet per modificarli */
	@GetMapping("/admin/GestioneBuffet/indexBuffet")
	public String getIndexBuffetModify(Model model) {
		model.addAttribute("buffets", bs.findAll());
		return "/admin/Buffet/indexBuffetModify.html";
	}

}
