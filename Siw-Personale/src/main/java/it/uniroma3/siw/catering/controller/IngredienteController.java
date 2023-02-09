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

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService is;
	@Autowired
	private IngredienteValidator iv;

	/* SEZIONE ADMIN */
	/* Accesso alla gestione del buffet */
	@GetMapping("/admin/GestioneIngrediente")
	public String getGestioneIngrediente(Model model) {
		model.addAttribute("ingredienti", this.is.findAll());
		return "admin/Ingrediente/GestioneIngrediente.html";
	}
	@GetMapping("/admin/GestioneIngrediente/add")
	public String getIngredienteForm(Model model) {
		Ingrediente ingrediente=new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("ingredienti", this.is.findAll());
		return "admin/Ingrediente/IngredienteForm";
	}
	@PostMapping("/admin/GestioneIngrediente/add")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		this.iv.validate(ingrediente, bindingResults);
		if(!bindingResults.hasErrors()) {
			this.is.save(ingrediente);
			model.addAttribute("ingrediente", model);
			return "admin/Ingrediente/GestioneIngrediente";
		}
		else
			return "admin/Ingrediente/IngredienteForm.html";
	}
	@GetMapping("/admin/GestioneIngrediente/edit/{ingred_id}")
	public String getEditIngredienteForm(@PathVariable("ingred_id") Long id, Model model) {
		model.addAttribute("ingrediente", this.is.findById(id));
		return "admin/Ingrediente/EditIngrediente.html";
	}
	
	/* metodo che inserisce l'ingrediente aggiornato nella base di dati */
	@PostMapping("/admin/GestioneIngrediente/edit/{ingr_id}")
	public String editIngrediente(@PathVariable("ingr_id") Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Ingrediente nuovoIngrediente = new Ingrediente();
			nuovoIngrediente.setId(id);
			nuovoIngrediente.setNome(ingrediente.getNome());
			nuovoIngrediente.setDescrizione(ingrediente.getDescrizione());
			nuovoIngrediente.setOrigine(ingrediente.getOrigine());
			this.is.save(nuovoIngrediente);
			model.addAttribute("ingredienti", this.is.findAll());
			return "admin/Ingrediente/GestioneIngrediente";
		}
		
		return "admin/Ingrediente/EditIngrediente.html";
	}
	
	/* metodo per eliminare un ingrediente e i piatti che lo contengono */
	@GetMapping("/admin/GestioneIngrediente/delete/{ingred_id}")
	public String deleteIngrediente(@PathVariable Long ingred_id, Model model) {
		this.is.deleteIngrediente(ingred_id);
		model.addAttribute("ingredienti", this.is.findAll());
		return "admin/Ingrediente/GestioneIngrediente";
	}
	
}
