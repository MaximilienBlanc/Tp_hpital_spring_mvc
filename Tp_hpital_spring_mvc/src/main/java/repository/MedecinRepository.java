package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Integer> {

}
