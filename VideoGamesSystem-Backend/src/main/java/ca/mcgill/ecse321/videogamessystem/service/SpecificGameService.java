package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Order;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.OrderRepository;

@Service
public class SpecificGameService {

    private SpecificGameRepository specificGameRepository;
    private GameRepository gameRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SpecificGameService(SpecificGameRepository specificGameRepository, GameRepository gameRepository, OrderRepository orderRepository) {
        this.specificGameRepository = specificGameRepository;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public SpecificGame createSpecificGame(int serialNumber, boolean availability, Long gameId, Integer orderId) {
        if (specificGameRepository.findSpecificGameBySerialNumber(serialNumber) != null) {
            throw new IllegalArgumentException("A specific game with this serial number already exists.");
        }

        Game game = gameRepository.findById(gameId).orElseThrow(() -> 
            new IllegalArgumentException("Game not found."));
        
        Order order = null;
        if (orderId != null) {
            order = orderRepository.findById(orderId).orElse(null);
        }

        SpecificGame specificGame = new SpecificGame(serialNumber, availability);
        specificGame.setGame(game);
        specificGame.setOrder(order);
        return specificGameRepository.save(specificGame);
    }

    @Transactional
    public SpecificGame getSpecificGameBySerialNumber(int serialNumber) {
        SpecificGame specificGame = specificGameRepository.findSpecificGameBySerialNumber(serialNumber);
        if (specificGame == null) {
            throw new IllegalArgumentException("Specific game not found.");
        }
        return specificGame;
    }

    @Transactional
    public List<SpecificGame> getSpecificGamesByAvailability(boolean availability) {
        return specificGameRepository.findSpecificGameByAvailability(availability);
    }

   

    @Transactional
public List<SpecificGame> getSpecificGamesByOrder(Integer orderNb) {
    Order order = orderRepository.findOrderByNumber(orderNb);
    if (order == null) {
        throw new IllegalArgumentException("Order not found.");
    }
    return specificGameRepository.findSpecificGameByorder(order);
}


    @Transactional
    public List<SpecificGame> getSpecificGamesByGame(Long gameId) {
        Game game = gameRepository.findGameById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Specific game not found.");
        }
        return specificGameRepository.findSpecificGameByGame(game);
    }


    @Transactional
    public SpecificGame updateAvailability(int serialNumber, boolean newAvailability) {
        SpecificGame specificGame = specificGameRepository.findSpecificGameBySerialNumber(serialNumber);
        if (specificGame == null) {
            throw new IllegalArgumentException("Specific game not found.");
        }

        specificGame.setAvailability(newAvailability);
        return specificGameRepository.save(specificGame);
    }

   

    @Transactional
    public SpecificGame deleteSpecificGame(int serialNumber) {
        SpecificGame specificGame = specificGameRepository.findSpecificGameBySerialNumber(serialNumber);
        if (specificGame == null) {
            throw new IllegalArgumentException("Specific game not found.");
        }

        specificGameRepository.delete(specificGame);
        return specificGame;
    }

    @Transactional
    public List<SpecificGame> getAllSpecificGames() {
        return toList(specificGameRepository.findAll());
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
}//find specific game by order 
// add specific game to order
// remove specific game to order


