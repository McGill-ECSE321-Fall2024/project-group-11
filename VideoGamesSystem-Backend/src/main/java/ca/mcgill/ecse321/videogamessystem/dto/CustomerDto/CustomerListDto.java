package ca.mcgill.ecse321.videogamessystem.dto.CustomerDto;

import java.util.List;

public class CustomerListDto {

    private List<CustomerSummaryDto> customers;

    public CustomerListDto(List<CustomerSummaryDto> customers) {
        this.customers = customers;
    }

    // Default protected constructor (for potential use by frameworks)
    protected CustomerListDto() {
    }

    // Getter for the list of games
    public List<CustomerSummaryDto> getCustomers() {
        return customers;
    }

    // Setter for the list of games
    public void setGames(List<CustomerSummaryDto> customers) {
        this.customers = customers;
    }

    // Method to get the number of games
    public Integer getNumberOfCustomers() {
        return customers.size();
    }
    
}
