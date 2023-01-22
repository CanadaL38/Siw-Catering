package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;
import it.uniroma3.siw.catering.validator.IngredienteValidator;

public class IngredienteController {
	@Autowired
	private IngredienteService is;
	@Autowired
	private PiattoService ps;
	@Autowired
	private IngredienteValidator iv;
	@Autowired
	private IngredienteRepository ir;

	@PostMapping("/admin/GestioneChef/add")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult bindingResults, Model model) {
		iv.validate(ingrediente, bindingResults);
		if (!bindingResults.hasErrors()) {
			ir.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:/admin/Ingrediente/all_Ingredienti";
		}
		return "admin/Ingrediente/IngredienteForm.html";
	}

	@PostMapping("/admin/GestioneBuffet/addIngrediente/{id}")
	public String addIngredienteAPiatto(@PathVariable("id") Long id,
			@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		iv.validate(ingrediente, bindingResults);
		if (!bindingResults.hasErrors()) {
			ps.findById(id).getIngredienti().add(ingrediente);
			ps.addPiatto(ps.findById(id));
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:/admin/GestioneBuffet/GestionePiatto/all_Ingredienti/" + id;
		}
		return "admin/Ingrediente/IngredienteFormPerPiatto.html"; // ?
	}

	@GetMapping("/admin/all_Ingredienti")
	public String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "admin/ingrediente/ingredienteForm.html";
	}

	@GetMapping("/admin/ingredienteForm/{id}")
	public String getIngredienteFormPerPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("piatto_id", id);
		return "admin/Ingrediente/ingredienteFormPerPiatto.html";
	}
	@GetMapping("/admin/GestioneIngrediente/indexIngrediente")
	public String getindexIngrediente(Model model) {
		model.addAttribute("ingredienti", is.findAll());
		return "admin/Ingrediente/indexIngrediente.html";
	}

	 @GetMapping("/admin/GestioneBuffet/GestionePiatto/removeIngrediente/{id1}/{id2}")
	  public String removeIngredienteDaPiatto(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, Model model) {
		  ps.findById(id2).getIngredienti().remove(is.findById(id1));
		  ps.addPiatto(ps.findById(id2));
		  return "redirect:/admin/indexAdmin";
	  }
}
