package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Long>{
    //find whichlist with id
    public WishlistRepository findWishlistById(Long id);
}
