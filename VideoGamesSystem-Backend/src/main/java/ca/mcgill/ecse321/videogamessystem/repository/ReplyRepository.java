package ca.mcgill.ecse321.videogamessystem.repository;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long>{
    public Reply findReplyById(Long id);
}
