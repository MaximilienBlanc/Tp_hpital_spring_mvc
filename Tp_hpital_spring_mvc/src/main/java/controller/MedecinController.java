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

import controller.validator.MedecinControllerValidator;
import model.Medecin;
import service.MedecinService;

@Controller
@RequestMapping("/Medecin")
public class MedecinController {
	@Autowired
	private MedecinService medecinSrv;

	@GetMapping("")
	public String list(Model model) {
		List<Medecin> medecin = medecinSrv.findAll();

		model.addAttribute("medecin", medecin);

		return "medecin/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goForm(model, new Medecin());
	}

	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		return goForm(model, medecinSrv.findById(id));
	}

	private String goForm(Model model, Medecin medecin) {
		model.addAttribute("Medecin", medecin);
		
		return "medecin/edit";
	}

	@PostMapping("")
	public String save(@Valid @ModelAttribute Medecin medecin, BindingResult result, Model model) {
		new MedecinControllerValidator().validate(medecin, result);
		
		if(result.hasErrors()) {
			return "medecin/edit";
		}
		
		if (medecin.getId() == null) {
			medecinSrv.create(medecin);
		} else {
			medecinSrv.update(medecin);
		}
		return "redirect:/medecin";
	}

	@PostMapping("/bis")
	public String save(@RequestParam Integer id, @RequestParam String login,@RequestParam String password,@RequestParam int salle ) {
		Medecin medecin = new Medecin();
		medecin.setId(id);
		medecin.setLogin(login);
		medecin.setPassword(password);
		medecin.setSalle(salle);
		
		

		if (medecin.getId() == null) {
			medecinSrv.create(medecin);
		} else {
			medecinSrv.update(medecin);
		}
		return "redirect:/medecin";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		medecinSrv.delete(id);
		return "redirect:/medecin";
	}
	
	
	
}
