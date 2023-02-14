package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.IdException;
import exception.MedecinException;
import model.Medecin;
import repository.MedecinRepository;

@Service
public class MedecinService {

	@Autowired
	private MedecinRepository medecinRepo;


	public Medecin create(Medecin medecin) {
		checkNotNull(medecin);
		if (medecin.getId() != null) {
			throw new IdException();
		}
		checkConstraint(medecin);
		return medecinRepo.save(medecin);
	}

	private void checkConstraint(Medecin medecin) {
		if (medecin.getLogin() == null || medecin.getLogin().isEmpty()) {
			throw new MedecinException("prenom obligatoire");
		}
		if (medecin.getPassword() == null || medecin.getPassword().isEmpty()) {
			throw new MedecinException("nom obligatoire");
		}
	}

	private void checkNotNull(Medecin medecin) {
		if (medecin == null) {
			throw new MedecinException("medecin obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}

	private void checkExist(Medecin medecin) {
		checkId(medecin.getId());
		findById(medecin.getId());
	}

	public Medecin findById(Integer id) {
		checkId(id);
		return medecinRepo.findById(id).orElseThrow(MedecinException::new);
	}

	public Medecin update(Medecin medecin) {
		checkNotNull(medecin);
		checkExist(medecin);
		checkConstraint(medecin);
		Medecin medecinEnBase = findById(medecin.getId());

		medecinEnBase.setLogin(medecin.getLogin());
		medecinEnBase.setPassword(medecin.getPassword());
		medecinEnBase.setSalle(medecin.getSalle());
		
		
		return medecinRepo.save(medecinEnBase);
	}

	public List<Medecin> findAll() {
		return medecinRepo.findAll();
	}

	public void delete(Medecin medecin) {
		checkExist(medecin);
		medecinRepo.delete(medecin);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}

}
