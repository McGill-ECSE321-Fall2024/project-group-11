package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificOrderRepository;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;

@Service
public class SpecificGameService {

    private SpecificGameRepository specificGameRepository;
    private GameRepository gameRepository;
    private SpecificOrderRepository specificOrderRepository;

    @Autowired
    public SpecificGameService(SpecificGameRepository specificGameRepository, GameRepository gameRepository, 
            SpecificOrderRepository specificOrderRepository) {
        this.specificGameRepository = specificGameRepository;
        this.gameRepository = gameRepository;
        this.specificOrderRepository = specificOrderRepository;
    }

    @Transactional
    public SpecificGame createSpecificGame(int serialNumber, boolean availability, Long gameId, Integer orderId) {
        if (specificGameRepository.findSpecificGameBySerialNumber(serialNumber) != null) {
            throw new IllegalArgumentException("A specific game with this serial number already exists.");
        }

        Game game = gameRepository.findById(gameId).orElseThrow(() -> 
            new IllegalArgumentException("Game not found."));
        
        SpecificOrder order = null;
        if (orderId != null) {
            order = specificOrderRepository.findById(orderId).orElse(null);
        }

        SpecificGame specificGame = new SpecificGame(serialNumber, availability);
        specificGame.setGame(game);
        specificGame.setSpecificOrder(order);
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
        SpecificOrder order = specificOrderRepository.findOrderByNumber(orderNb);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        return specificGameRepository.findSpecificGameBySpecificOrder(order);
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

    // find specific game by order
    public List<SpecificGame> getSpecificGameByOrder(SpecificOrder order){
        return specificGameRepository.findSpecificGameBySpecificOrder(order);
    }

    // add specific game to order
    public List<SpecificGame> addSpecificGameToOrder(int specificGameID, SpecificOrder order) {
        SpecificGame specificGame = specificGameRepository.findSpecificGameBySerialNumber(specificGameID);
        if (specificGame == null) {
            throw new IllegalArgumentException("specific game not found");
        }
        if (order == null) {
            throw new IllegalArgumentException("order cannot be null");
        }
    
        // Set the specific order for the game
        specificGame.setSpecificOrder(order);
    
        // Save the updated specific game with the new order
        specificGameRepository.save(specificGame);
    
        // Return the list of specific games associated with the order
        return specificGameRepository.findSpecificGameBySpecificOrder(order);
    }

    public List<SpecificGame> removeSpecificGameFromOrder(int specificGameID, SpecificOrder order) {
        // Retrieve the specific game by serial number
        SpecificGame specificCopy = specificGameRepository.findSpecificGameBySerialNumber(specificGameID);
        if (specificCopy == null) {
            throw new IllegalArgumentException("specific game not found");
        }
        if (order == null) {
            throw new IllegalArgumentException("order cannot be null");
        }

        // Check if the specific game is indeed associated with the given order
        if (!order.equals(specificCopy.getSpecificOrder())) {
            throw new IllegalArgumentException("the specific game was not in the order provided.");
        }

        // Unset the order from the specific game and save the change
        specificCopy.setSpecificOrder(null);
        specificGameRepository.save(specificCopy);

        // Return the updated list of games associated with the order (should no longer
        // include this game)
        return specificGameRepository.findSpecificGameBySpecificOrder(order);
    }

}


