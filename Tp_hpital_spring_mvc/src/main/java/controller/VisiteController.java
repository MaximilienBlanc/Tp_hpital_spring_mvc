package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Visite;
import repository.PatientRepository;
import repository.VisiteRepository;


@Controller
@RequestMapping("/visite")
public class VisiteController {
	
	@Autowired
	private VisiteRepository visiteRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private MedecineRepository medecinRepo;
	

	@GetMapping("") // ETAPE 1 : RECEPTION DE LA REQUETE
	public String list(Model model) {
		// ETAPE 2 : RECUPERATION DES DONNEES
		List<Visite> visites = visiteRepo.findAll();

		// ETAPE 3 : RENSEIGNER LE MODEL
		model.addAttribute("visites", visites);

		// ETAPE 4 : APPEL DE LA VIEW
		return "visite/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("visite", new Visite());
		model.addAttribute("patients", patientRepo.findAll());
		model.addAttribute("medecins", medecinRepo.findAll());

		return "filiere/form";
	}

	
	@GetMapping("/form")
	public String edit(@RequestParam Integer id, Model model) {
		model.addAttribute("filiere", visiteRepo.findById(id));
		return "visite/form";
	}
/*
	@PostMapping("")
	public String save(@ModelAttribute("filiere") @Valid Filiere filiere, BindingResult result, @RequestParam(required = false) Integer idReferent) {
		new FiliereValidator().validate(filiere, result);
		
		if(result.hasErrors()) {
			return "filiere/form";
		}
		
		if(idReferent != null) {
			Formateur formateur = formateurService.findById(idReferent);
			filiere.setReferent(formateur);
		} else {
			filiere.setReferent(null);
		}
		
		if (filiere.getId() == null) {
			filiereService.create(filiere);
		} else {
			filiereService.update(filiere);
		}
		return "redirect:filiere";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "forward:/filiere";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		filiereService.delete(id);

		return "redirect:filiere";
	}
	
*/
}
