package ca.mcgill.ecse321.videogamessystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificOrderRepository;
import ca.mcgill.ecse321.videogamessystem.service.SpecificGameService;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SpecificGameServiceTest {

    @Mock
    private SpecificGameRepository specificGameRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private SpecificOrderRepository specificOrderRepository;

    @InjectMocks
    private SpecificGameService specificGameService;

    private SpecificGame specificGame;
    private Game game;
    private SpecificOrder specificOrder;

    @BeforeEach
    public void setup() {
        // Assuming Game and SpecificOrder are created without setting an ID directly
        game = mock(Game.class);
        specificOrder = mock(SpecificOrder.class);

        specificGame = new SpecificGame(123, true);
        specificGame.setGame(game);
        specificGame.setSpecificOrder(null);
    }

    @Test
    public void testCreateSpecificGame_Success() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(null);
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        when(specificGameRepository.save(any(SpecificGame.class))).thenReturn(specificGame);

        SpecificGame createdGame = specificGameService.createSpecificGame(123, true, 1L, null);
        assertNotNull(createdGame);
        assertEquals(123, createdGame.getSerialNumber());
        assertTrue(createdGame.getAvailability());
    }

    @Test
    public void testCreateSpecificGame_DuplicateSerialNumber() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(specificGame);

        assertThrows(IllegalArgumentException.class, () -> {
            specificGameService.createSpecificGame(123, true, 1L, null);
        });
    }

    @Test
    public void testAddSpecificGameToOrder_Success() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(specificGame);
        when(specificGameRepository.save(any(SpecificGame.class))).thenReturn(specificGame);
        when(specificGameRepository.findSpecificGameBySpecificOrder(any(SpecificOrder.class)))
            .thenReturn(Collections.singletonList(specificGame));

        var result = specificGameService.addSpecificGameToOrder(123, specificOrder);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateAvailability_Success() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(specificGame);
        when(specificGameRepository.save(any(SpecificGame.class))).thenReturn(specificGame);

        SpecificGame updatedGame = specificGameService.updateAvailability(123, false);
        assertNotNull(updatedGame);
        assertFalse(updatedGame.getAvailability());
    }

    @Test
    public void testRemoveGameFromOrder_Success() {
        // Arrange
        specificGame.setSpecificOrder(specificOrder);  // Associate the game with the order
        when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);
        when(specificGameRepository.findSpecificGameBySpecificOrder(specificOrder))
            .thenReturn(Collections.emptyList());  // Expect empty list after removal
        when(specificGameRepository.save(any(SpecificGame.class))).thenReturn(specificGame);

        // Act
        List<SpecificGame> result = specificGameService.removeGameFromOrder(123, specificOrder);

        // Assert
        assertTrue(result.isEmpty(), "The list should be empty after removing the game from the order.");
        verify(specificGameRepository, times(1)).save(specificGame);  // Ensure save is called
    }

    @Test
    public void testGetSpecificGamesByAvailability() {
        when(specificGameRepository.findSpecificGameByAvailability(anyBoolean()))
            .thenReturn(Collections.singletonList(specificGame));

        var result = specificGameService.getSpecificGamesByAvailability(true);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetSpecificGameBySerialNumber_Found() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(specificGame);

        SpecificGame foundGame = specificGameService.getSpecificGameBySerialNumber(123);
        assertNotNull(foundGame);
        assertEquals(123, foundGame.getSerialNumber());
    }

    @Test
    public void testGetSpecificGameBySerialNumber_NotFound() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> specificGameService.getSpecificGameBySerialNumber(999));
    }

    @Test
    public void testDeleteSpecificGame_Success() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(specificGame);

        SpecificGame deletedGame = specificGameService.deleteSpecificGame(123);
        assertNotNull(deletedGame);
        verify(specificGameRepository, times(1)).delete(specificGame);
    }
}
