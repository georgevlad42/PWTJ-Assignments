package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Console;
import ro.unibuc.egv.finalProject.repositories.ConsoleRepository;

import java.util.List;

@Service
public class ConsoleService {

    private ConsoleRepository consoleRepository;

    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    public Console getConsoleByID(Long id){
        return consoleRepository.findConsoleByConsoleID(id);
    }

    public List<Console> getConsoles(){
        return consoleRepository.findAll();
    }

    public void updateConsoleQuantity(Console console){
        consoleRepository.save(console);
    }

    public void addConsole(Console console){
        console.getProduct().setStatus("Available");
        consoleRepository.save(console);
    }
}
