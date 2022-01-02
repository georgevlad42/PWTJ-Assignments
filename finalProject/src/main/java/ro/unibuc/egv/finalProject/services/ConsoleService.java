package ro.unibuc.egv.finalProject.services;

import ro.unibuc.egv.finalProject.models.Console;

import java.util.Map;

public interface ConsoleService {

    void addConsole (Console console);
    Map<String, Console> showConsoles();
    void updateConsole (Console console);
    void deleteConsole (Console console);

}
