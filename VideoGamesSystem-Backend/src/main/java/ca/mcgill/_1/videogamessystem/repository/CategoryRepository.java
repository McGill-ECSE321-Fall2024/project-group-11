package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
    public Category findCategoryById(int idNum);
}
