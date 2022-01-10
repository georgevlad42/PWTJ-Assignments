package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.Game;

@Repository
public interface GameRepository extends JpaRepository <Game, Long> {

    Game findGameByGameID(Long id);

}
