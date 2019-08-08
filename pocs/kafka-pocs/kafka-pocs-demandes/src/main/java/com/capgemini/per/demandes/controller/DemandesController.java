package com.capgemini.per.demandes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.per.models.Demande;
import com.capgemini.per.demandes.models.DemandeEntity;
import com.capgemini.per.demandes.services.DemandesService;

@Controller
public class DemandesController {

	@Autowired
	private DemandesService demandesService;
	
	@GetMapping("/demandes")
    public String index(Model model) {
		model.addAttribute("title", "Demandes");		
		List<DemandeEntity> demandes = demandesService.findAll();	
		model.addAttribute("listDemandes", demandes);		
        return "home";
    }
	
	@GetMapping("/form")
    public String form(Model model) {
		model.addAttribute("title", "Ajout Demande");
		model.addAttribute("demande", new Demande());
        return "form";
    }
	
	@PostMapping("/save")
    public ModelAndView form(@ModelAttribute Demande demande, ModelMap model) {
		model.addAttribute("title", "Ajout Demande");
		demande.setStatut("new");
		demandesService.save(DemandesHelper.buildDemandeEntity(demande));
		return new ModelAndView("redirect:/demandes", model);
    }
}
