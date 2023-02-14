package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.PatientException;
import model.Patient;
import repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepo;

	public Patient create(Patient patient) {
		return patientRepo.save(patient);
	}

	public Patient findById(Integer id) {
		return patientRepo.findById(id).orElseThrow(PatientException::new);
	}

	public Patient update(Patient patient) {
		Patient patientEnBase = findById(patient.getId());
		patientEnBase.setNumeroSecuriteSociale(patient.getNumeroSecuriteSociale());
		patientEnBase.setNom(patient.getNom());
		patientEnBase.setPrenom(patient.getPrenom());

		return patientRepo.save(patientEnBase);
	}

	public List<Patient> findAll() {
		return patientRepo.findAll();
	}

	public void delete(Patient patient) {
		patientRepo.delete(patient);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}
	

}
