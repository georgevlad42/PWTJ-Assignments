package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.egv.finalProject.models.Game;

public interface GameRepository extends JpaRepository <Game, Integer> {

}
