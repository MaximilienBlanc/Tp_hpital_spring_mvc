package controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Medecin;

public class MedecinControllerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {	
		return Medecin.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Medecin medecin = (Medecin) target;

		
	}

}
