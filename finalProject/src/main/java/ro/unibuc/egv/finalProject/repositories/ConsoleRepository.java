package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.Console;

@Repository
public interface ConsoleRepository extends JpaRepository <Console, Long> {

}
