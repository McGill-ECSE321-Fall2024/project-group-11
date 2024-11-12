package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Console;
import ca.mcgill.ecse321.videogamessystem.model.Console.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.repository.ConsoleRepository;

@Service
public class ConsoleService {

    private ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    @Transactional
    public Console createConsole(ConsoleType consoleType) { // all good just need the good version
        if (consoleType == null) {
            throw new IllegalArgumentException("Console type cannot be null.");
        }

        if (consoleRepository.findConsoleByConsoleType(consoleType) != null) {
            throw new IllegalArgumentException("Console with this type already exists.");
        }

        Console console = new Console(consoleType);
        return consoleRepository.save(console);
    }

    @Transactional
    public Console getConsoleById(Long id) {
        Console console = consoleRepository.findConsoleById(id);
        if (console == null) {
            throw new IllegalArgumentException("Console not found.");
        }
        return console;
    }

    @Transactional
    public List<Console> getConsolesByConsoleType(ConsoleType consoleType) {
        if (consoleType == null) {
            throw new IllegalArgumentException("Console type cannot be null.");
        }

        List<Console> consoles = consoleRepository.findConsoleByConsoleType(consoleType);
        if (consoles == null || consoles.isEmpty()) {
            throw new IllegalArgumentException("No consoles of specified type found.");
        }
        return consoles;
    }

/* 
    @Transactional
    public Console getConsoleByGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("game not found");
        }

        Game game = gameRepository.findConsoleByGame(game);
        if (game == null) {
            throw new IllegalArgumentException("Console of specified type not found.");
        }
        return console;
    }
*/
// we want get console type and not console
    @Transactional
    public Console updateConsoleType(Long id, ConsoleType newConsoleType) {
        Console console = consoleRepository.findConsoleById(id);
        if (console == null) {
            throw new IllegalArgumentException("Console not found.");
        }

        if (newConsoleType == null) {
            throw new IllegalArgumentException("Console type cannot be null.");
        }

        if (consoleRepository.findConsoleByConsoleType(newConsoleType) != null) {
            throw new IllegalArgumentException("A console with this type already exists.");
        }

        console.setConsoleType(newConsoleType);
        return consoleRepository.save(console);
    }

    @Transactional
    public Console deleteConsole(Long id) {
        Console console = consoleRepository.findConsoleById(id);
        if (console == null) {
            throw new IllegalArgumentException("Console not found.");
        }

        consoleRepository.delete(console);
        return console;
    }

    @Transactional
    public List<Console> getAllConsoles() {
        return toList(consoleRepository.findAll());
    }

    /**
     * Converts an {@code Iterable} to a {@code List}.
     * @param iterable the {@code Iterable} to convert
     * @param <T>      the type of elements in the iterable
     * @return a {@code List} containing the elements of the {@code Iterable}
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    //get consoletype by game : returns the consoletyoe  sur game 
}

