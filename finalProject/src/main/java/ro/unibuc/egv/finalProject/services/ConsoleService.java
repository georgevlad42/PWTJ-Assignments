package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Console;
import ro.unibuc.egv.finalProject.repositories.ConsoleRepository;

import java.util.List;

@Service
public class ConsoleService {

    private final ConsoleRepository consoleRepository;

    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    //region Getters
    public Console getConsoleByID(Long id){
        return consoleRepository.findConsoleByConsoleID(id);
    }

    public List<Console> getConsoles(){
        return consoleRepository.findAll();
    }
    //endregion

    //region Quantity Update
    public void updateConsoleQuantity(Console console){
        consoleRepository.save(console);
    }
    //endregion

    //region Add Console
    public void addConsole(Console console){
        console.getProduct().setStatus("Available");
        consoleRepository.save(console);
    }
    //endregion

    //region Edit Console
    public void editConsole(Console console){
        if (console.getProduct().getQuantity() > 0) {
            console.getProduct().setStatus("Available");
        } else {
            console.getProduct().setStatus("Unavailable");
        }
        consoleRepository.save(console);
    }
    //endregion

    //region Delete Console
    public void deleteConsole(Long id){
        consoleRepository.deleteById(id);
    }
    //endregion
}
