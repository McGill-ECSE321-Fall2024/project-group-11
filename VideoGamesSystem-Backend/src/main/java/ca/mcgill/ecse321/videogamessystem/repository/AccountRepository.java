package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
    public Account findAccountById(Long id);
}