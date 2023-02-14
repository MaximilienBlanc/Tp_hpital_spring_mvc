package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Medecin;
import model.Patient;
import model.Visite;
import repository.MedecinRepository;
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
	private MedecinRepository medecinRepo;
	

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

		model.addAttribute("patients", patientRepo.findAll());
		model.addAttribute("medecins", medecinRepo.findAll());
		return "visite/form";
	}

	@PostMapping("")
	public String save(@ModelAttribute("visite") @Valid Visite visite, BindingResult result, @RequestParam(required = false) Integer idMedecin, @RequestParam(required = false) Integer idPatient) {
		
		
		if(result.hasErrors()) {
			return "visite/form";
		}
		
		if(idMedecin != null) {
			Medecin medecin = medecinRepo.findById(idMedecin).get();
			visite.setMedecin(medecin);
		} else {
			visite.setMedecin(null);
		}
		
		if(idPatient != null) {
			Patient patient = patientRepo.findById(idPatient).get();
			visite.setPatient(patient);
		} else {
			visite.setPatient(null);
		}
		
		visiteRepo.save(visite);
		
		return "redirect:visite";
	}

	
	@GetMapping("/cancel")
	public String cancel() {

		return "forward:/visite";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		visiteRepo.deleteById(id);

		return "redirect:visite";
	}
	

}
