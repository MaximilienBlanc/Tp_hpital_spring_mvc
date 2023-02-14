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

import model.Patient;
import service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientsrv;
	

	@GetMapping("")
	public String list(Model model) {
		List<Patient> patients = patientsrv.findAll();

		model.addAttribute("patients", patients);

		return "patient/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goForm(model, new Patient());
	}

	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		return goForm(model, patientsrv.findById(id));
	}

	private String goForm(Model model, Patient patient) {
		model.addAttribute("patient", patient);
		return "patient/edit";
	}

	@PostMapping("")
	public String save(@Valid @ModelAttribute Patient patient, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "patient/edit";
		}
		
		if (patient.getId() == null) {
			patientsrv.create(patient);
		} else {
			patientsrv.update(patient);
		}
		return "redirect:/patient";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		patientsrv.delete(id);
		return "redirect:/patient";
	}	
	

}
