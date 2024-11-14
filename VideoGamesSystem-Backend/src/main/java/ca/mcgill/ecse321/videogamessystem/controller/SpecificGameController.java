package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto.SpecificGameRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto.SpecificGameResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.service.SpecificGameService;
import ca.mcgill.ecse321.videogamessystem.service.SpecificOrderService;
import ca.mcgill.ecse321.videogamessystem.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/specificGames")
public class SpecificGameController {

    @Autowired
    private SpecificGameService specificGameService;

    @Autowired
    private GameService gameService;

    @Autowired
    private SpecificOrderService specificOrderService;

    // Create a new specific game
    @PostMapping
    public ResponseEntity<SpecificGameResponseDto> createSpecificGame(@RequestBody SpecificGameRequestDto specificGameRequestDto) {
        SpecificGame specificGame = specificGameService.createSpecificGame(
                specificGameRequestDto.getSerialNumber(),
                specificGameRequestDto.isAvailability(),
                specificGameRequestDto.getGameId(),
                specificGameRequestDto.getSpecificOrderId() // Pass order ID if specified, can be null
        );
        return ResponseEntity.ok(convertToDto(specificGame));
    }

    // Get specific game by serial number
    @GetMapping("/{serialNumber}")
    public ResponseEntity<SpecificGameResponseDto> getSpecificGameBySerialNumber(@PathVariable int serialNumber) {
        SpecificGame specificGame = specificGameService.getSpecificGameBySerialNumber(serialNumber);
        return ResponseEntity.ok(convertToDto(specificGame));
    }

    // Get all specific games
    @GetMapping
    public ResponseEntity<List<SpecificGameResponseDto>> getAllSpecificGames() {
        List<SpecificGameResponseDto> specificGames = specificGameService.getAllSpecificGames()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(specificGames);
    }

    // Update specific game availability
    @PutMapping("/{serialNumber}/availability")
    public ResponseEntity<SpecificGameResponseDto> updateAvailability(
            @PathVariable int serialNumber,
            @RequestParam boolean newAvailability) {
        SpecificGame specificGame = specificGameService.updateAvailability(serialNumber, newAvailability);
        return ResponseEntity.ok(convertToDto(specificGame));
    }

    // Add a specific game to an order
    @PutMapping("/{serialNumber}/addToOrder")
    public ResponseEntity<List<SpecificGameResponseDto>> addSpecificGameToOrder(
            @PathVariable int serialNumber,
            @RequestParam int orderId) {
        SpecificOrder order = specificOrderService.getSpecificOrderById(orderId);
        List<SpecificGame> updatedSpecificGames = specificGameService.addSpecificGameToOrder(serialNumber, order);
        List<SpecificGameResponseDto> responseDtos = updatedSpecificGames.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    // Remove a specific game from an order
    @PutMapping("/{serialNumber}/removeFromOrder")
    public ResponseEntity<List<SpecificGameResponseDto>> removeSpecificGameFromOrder(
            @PathVariable int serialNumber,
            @RequestParam int orderId) {
        SpecificOrder order = specificOrderService.getSpecificOrderById(orderId);
        List<SpecificGame> updatedSpecificGames = specificGameService.removeSpecificGameFromOrder(serialNumber, order);
        List<SpecificGameResponseDto> responseDtos = updatedSpecificGames.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    // Delete a specific game by serial number
    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Void> deleteSpecificGame(@PathVariable int serialNumber) {
        specificGameService.deleteSpecificGame(serialNumber);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert SpecificGame to SpecificGameResponseDto
    private SpecificGameResponseDto convertToDto(SpecificGame specificGame) {
        return new SpecificGameResponseDto(
                specificGame.getAvailability(),
                specificGame.getSerialNumber(),
                specificGame.getGame().getId(),
                specificGame.getGame().getTitle(),
                specificGame.getGame().getDescription(),
                specificGame.getGame().getPrice(),
                specificGame.getGame().getCategory(),
                specificGame.getGame().getConsoleType()
        );
    }
}
