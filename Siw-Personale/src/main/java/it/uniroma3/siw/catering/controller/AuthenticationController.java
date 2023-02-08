package it.uniroma3.siw.catering.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.catering.model.Credentials;

import it.uniroma3.siw.catering.repository.AdminRepository;
import it.uniroma3.siw.catering.service.CredentialsService;
import it.uniroma3.siw.catering.validator.CredentialsValidator;



@Controller
public class AuthenticationController{
	
	@Autowired
	private CredentialsService credentialsService;
	

	@Autowired
	private CredentialsValidator credentialsValidator;
	

	
	@GetMapping("/logout") 
	public String logout(Model model) {
		return "index";
	}
	
   @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "/admin/Admin_index.html";
        }
        return "index";
    }
	
   
}