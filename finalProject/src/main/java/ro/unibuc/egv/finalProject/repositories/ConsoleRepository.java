package ro.unibuc.egv.finalProject.repositories;

import ro.unibuc.egv.finalProject.models.Console;

import java.util.Map;

public interface ConsoleRepository {

    void save (Console console);
    Map<String, Console> findAll();
    void update (Console console);
    void delete (Console console);

}
