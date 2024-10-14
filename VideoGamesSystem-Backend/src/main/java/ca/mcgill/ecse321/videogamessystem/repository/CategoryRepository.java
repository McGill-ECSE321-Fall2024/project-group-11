package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
    public Category findCategoryById(int idNum);
}
