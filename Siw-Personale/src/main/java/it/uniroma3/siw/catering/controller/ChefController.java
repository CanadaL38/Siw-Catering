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
	@GetMapping("/user/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = cs.findById(id);
		model.addAttribute("chef", chef);
		return "/user/chef.html";
	}

	@GetMapping("/user/all_Chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = cs.findAll();
		model.addAllAttributes(chefs);
		return "/user/all_Chefs.html";
	}
	/* SEZIONE ADMIN */

	@GetMapping("/admin/GestioneChef")
	public String getChefManagment(Model model) {
		model.addAttribute("chefs", this.cs.findAll());
		return "/admin/Chef/GestioneChef.html";
	}

	@PostMapping("/admin/GestioneChef/add")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		cv.validate(chef, bindingResults);
		if (!bindingResults.hasErrors()) {
			cr.save(chef);
			model.addAttribute("chef", chef);
			return "redirect:/admin/all_Chefs";
		}
		return "admin/Chef/ChefForm.html";
	}
	
	@GetMapping("/admin/GestioneChef/edit")
	public String getindexChef(Model model) {
		model.addAttribute("chefs", cs.findAll());
		return "admin/Chef/indexChefModify.html";
	}
}
