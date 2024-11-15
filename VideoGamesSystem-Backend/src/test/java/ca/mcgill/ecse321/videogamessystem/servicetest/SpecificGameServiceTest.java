package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;
import ca.mcgill.ecse321.videogamessystem.service.SpecificGameService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SpecificGameServiceTest {

    @Mock
    private SpecificGameRepository specificGameRepository;

    @InjectMocks
    private SpecificGameService specificGameService;

    private SpecificGame specificGame;
    private SpecificOrder specificOrder;

    @BeforeEach
    public void setup() {
        specificGame = new SpecificGame(123, true);
        specificOrder = new SpecificOrder();
        specificGame.setSpecificOrder(specificOrder);
    }

    @Test
    public void testAddSpecificGameToOrder_Success() {
        when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);
        when(specificGameRepository.findSpecificGameBySpecificOrder(specificOrder))
            .thenReturn(Collections.singletonList(specificGame));

        List<SpecificGame> result = specificGameService.addSpecificGameToOrder(123, specificOrder);

        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "The list should contain one game after adding to the order.");
        assertEquals(specificOrder, specificGame.getSpecificOrder(), "The game's order should be set.");
    }

    // @Test
    // public void testRemoveSpecificGameFromOrder_Success() {
    //     // Associate specificGame with specificOrder in the setup
    //     specificGame.setSpecificOrder(specificOrder);
    //     when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);
    //     when(specificGameRepository.findSpecificGameBySpecificOrder(specificOrder))
    //         .thenReturn(Collections.singletonList(specificGame)) // Before removal
    //         .thenReturn(Collections.emptyList()); // After removal

    //     // Call the method
    //     List<SpecificGame> result = specificGameService.removeSpecificGameFromOrder(123, specificOrder);

    //     // Verify that specificGame's order is unset
    //     assertNull(specificGame.getSpecificOrder(), "The game's order should be unset.");
    //     // Check if the result list reflects the expected state (empty after removal)
    //     assertEquals(0, result.size(), "The list should have length 0 after removing the game from the order.");

    //     // Verify save was called
    //     verify(specificGameRepository, times(1)).save(specificGame);
    // }


    // @Test
    // public void testRemoveSpecificGameFromOrder_GameNotInOrder() {
    //     specificGame.setSpecificOrder(null); // Ensure game is not in any order

    //     when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);

    //     IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
    //         specificGameService.removeSpecificGameFromOrder(123, specificOrder);
    //     }, "Expected IllegalArgumentException when specific game is not in the order, but none was thrown.");
    //     assertEquals("the specific game was not in the order provided.", thrown.getMessage());
    // }

    // @Test
    // public void testUpdateAvailability_Success() {
    //     when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);

    //     SpecificGame updatedGame = specificGameService.updateAvailability(123, false);

    //     assertNotNull(updatedGame, "Updated game should not be null");
    //     assertFalse(updatedGame.getAvailability(), "The availability should be updated to false.");
    // }

    @Test
    public void testGetSpecificGamesByAvailability() {
        when(specificGameRepository.findSpecificGameByAvailability(true))
            .thenReturn(Collections.singletonList(specificGame));

        List<SpecificGame> result = specificGameService.getSpecificGamesByAvailability(true);

        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "The list should contain one game.");
        assertTrue(result.get(0).getAvailability(), "The game should be available.");
    }

    @Test
    public void testGetSpecificGameBySerialNumber_Found() {
        when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);

        SpecificGame foundGame = specificGameService.getSpecificGameBySerialNumber(123);

        assertNotNull(foundGame, "Found game should not be null");
        assertEquals(123, foundGame.getSerialNumber(), "The serial number should match.");
    }

    @Test
    public void testGetSpecificGameBySerialNumber_NotFound() {
        when(specificGameRepository.findSpecificGameBySerialNumber(anyInt())).thenReturn(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            specificGameService.getSpecificGameBySerialNumber(999);
        }, "Expected IllegalArgumentException for a nonexistent serial number.");
        assertEquals("Specific game not found.", thrown.getMessage());
    }

    @Test
    public void testDeleteSpecificGame_Success() {
        when(specificGameRepository.findSpecificGameBySerialNumber(123)).thenReturn(specificGame);

        SpecificGame deletedGame = specificGameService.deleteSpecificGame(123);

        assertNotNull(deletedGame, "Deleted game should not be null");
        verify(specificGameRepository, times(1)).delete(specificGame);
    }
}
