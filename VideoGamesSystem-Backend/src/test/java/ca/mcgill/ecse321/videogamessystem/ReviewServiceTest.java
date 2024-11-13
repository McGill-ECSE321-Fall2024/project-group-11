package ca.mcgill.ecse321.videogamessystem;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificOrderRepository;
import ca.mcgill.ecse321.videogamessystem.service.SpecificGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SpecificGameServiceTest {

    @Mock
    private SpecificGameRepository specificGameRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private SpecificOrderRepository specificOrderRepository;

    @InjectMocks
    private SpecificGameService specificGameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSpecificGame_Success() {
        int serialNumber = 123;
        boolean availability = true;
        Long gameId = 1L;

        Game game = new Game();
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));
        when(specificGameRepository.findSpecificGameBySerialNumber(serialNumber)).thenReturn(null);
        when(specificGameRepository.save(any(SpecificGame.class))).thenReturn(new SpecificGame());

        SpecificGame createdGame = specificGameService.createSpecificGame(serialNumber, availability, gameId, null);

        assertNotNull(createdGame);
        verify(specificGameRepository, times(1)).save(any(SpecificGame.class));
    }

    @Test
    void testAddSpecificGameToOrder_Success() {
        int specificGameID = 123;
        SpecificOrder order = new SpecificOrder();
        SpecificGame specificGame = new SpecificGame();

        when(specificGameRepository.findSpecificGameBySerialNumber(specificGameID)).thenReturn(specificGame);
        when(specificGameRepository.findSpecificGameBySpecificOrder(order)).thenReturn(Arrays.asList(specificGame));
        when(specificGameRepository.save(specificGame)).thenReturn(specificGame);

        List<SpecificGame> result = specificGameService.addSpecificGameToOrder(specificGameID, order);

        assertNotNull(result);
        assertTrue(result.contains(specificGame));
        verify(specificGameRepository, times(1)).save(specificGame);
    }

    @Test
    void testUpdateAvailability_Success() {
        int serialNumber = 123;
        boolean newAvailability = false;
        SpecificGame specificGame = new SpecificGame();

        when(specificGameRepository.findSpecificGameBySerialNumber(serialNumber)).thenReturn(specificGame);
        when(specificGameRepository.save(specificGame)).thenReturn(specificGame);

        SpecificGame updatedGame = specificGameService.updateAvailability(serialNumber, newAvailability);

        assertNotNull(updatedGame);
        assertEquals(newAvailability, updatedGame.getAvailability());
        verify(specificGameRepository, times(1)).save(specificGame);
    }

    @Test
    void testRemoveGameFromOrder_Success() {
        int specificGameID = 123;
        SpecificGame specificGame = new SpecificGame();
        SpecificOrder order = new SpecificOrder();

        when(specificGameRepository.findSpecificGameBySerialNumber(specificGameID)).thenReturn(specificGame);
        when(specificGameRepository.findSpecificGameBySpecificOrder(order)).thenReturn(Arrays.asList(specificGame));
        when(specificGameRepository.save(specificGame)).thenReturn(specificGame);

        List<SpecificGame> result = specificGameService.removeGameFromOrder(specificGameID, order);

        assertNotNull(result);
        assertTrue(result.contains(specificGame));
        verify(specificGameRepository, times(1)).save(specificGame);
    }
}
