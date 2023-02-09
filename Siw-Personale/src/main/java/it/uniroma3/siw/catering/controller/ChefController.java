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

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.ChefValidator;

@Controller
public class ChefController {
	@Autowired
	private ChefService cs;
	@Autowired
	private ChefValidator cv;
	@Autowired
	private ChefRepository cr;

	/* SEZIONE USER */
	@GetMapping("/user/all_Chefs/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = cs.findById(id);
		model.addAttribute("chef", chef);
		return "user/chef.html";
	}

	@GetMapping("/user/all_Chefs")
	public String getChefs(Model model) {
		model.addAttribute("chefs",cs.findAll());
		return "user/all_Chefs.html";
	}
	/* SEZIONE ADMIN */

	@GetMapping("/admin/GestioneChef")
	public String getChefManagment(Model model) {
		model.addAttribute("chefs", this.cs.findAll());
		return "admin/Chef/GestioneChef.html";
	}
	
	@GetMapping("/admin/GestioneChef/add")
	public String getChefForm(Model model) {
		Chef nuovoChef = new Chef();
		model.addAttribute("chef", nuovoChef);
		return "admin/Chef/ChefForm.html";
	}
	
	@PostMapping("/admin/GestioneChef/add")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		cv.validate(chef,  bindingResults);
		if(!bindingResults.hasErrors()) {
			this.cs.aggiungiChef(chef);
			model.addAttribute("chef", chef);
			model.addAttribute("chefs", this.cs.findAll());
			return "admin/Chef/GestioneChef.html";
		}
		return "admin/Chef/ChefForm.html";
	}
	
	@GetMapping("/admin/GestioneChef/edit/{id}")
	public String getEditChefForm(@PathVariable("id") Long chef_id, Model model) {
		Chef chef = cs.findById(chef_id);
		model.addAttribute("chef", chef);
		return "admin/Chef/EditChef.html";
	}
	
	
	@PostMapping("/admin/GestioneChef/edit/{chef_id}")
	public String editChef(@PathVariable("chef_id") Long id,@Valid @ModelAttribute("chef") Chef chef, 
			BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Chef nuovoChef = this.cs.findById(id);
			nuovoChef.setId(id);
			nuovoChef.setNome(chef.getNome());
			nuovoChef.setCognome(chef.getCognome());
			nuovoChef.setNazionalita(chef.getNazionalita());
			this.cs.editChef(nuovoChef);
			model.addAttribute("chef", chef);
			model.addAttribute("chefs", this.cs.findAll());
			return "admin/Chef/GestioneChef";
		}
		else
			return "admin/Chef/EditChef.html";
	}
	

	@GetMapping("/admin/GestioneChef/delete/{id}")
	public String deleteChef(@PathVariable("id") Long chef_id, Model model) {
		this.cs.deleteChefById(chef_id);
		model.addAttribute("chefs", this.cs.findAll());
		return "admin/Chef/GestioneChef";
	}
}
